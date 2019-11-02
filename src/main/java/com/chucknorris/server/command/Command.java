package com.chucknorris.server.command;

import com.chucknorris.server.command.dto.CommandData;
import com.chucknorris.server.command.dto.CommandDto;
import com.chucknorris.server.command.response.ServerResponse;
import com.chucknorris.server.command.serializer.Serializer;
import com.chucknorris.server.command.serializer.SerializerImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.util.logging.Logger;

public abstract class Command<T extends ServerResponse> {
	protected static final Logger LOGGER = Logger.getLogger(Command.class.getName());

	private Serializer<T> serializer = new SerializerImpl<>();
	//TODO Cuando cerramos el ¿reader?
	private BufferedReader reader;
	private InputStream inputStream;

	protected abstract ServerResponse execute(CommandData commandData) throws Throwable;

	public final String process(CommandDto commandDto) {
		try {
			ServerResponse response = handleCommand(commandDto);

			return serializer.serialize((T) response);
		} catch (Throwable e) {
			e.printStackTrace();

			//TODO fallback
			return "";
		}
	}

	protected String readLine() {
		if (reader == null) {
			reader = new BufferedReader(new InputStreamReader(inputStream));
		}

		try {
			return reader.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}

		//Todo return fallback string
		return "";
	}

	private ServerResponse handleCommand(CommandDto commandDto) throws Throwable {
		inputStream = commandDto.socket.getInputStream();

		CommandData commandData = new CommandData();
		commandData.commandDto = commandDto;

		return execute(commandData);
	}

	protected String getNow() {
		return LocalDateTime.now().toString();
	}
}
