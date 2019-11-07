package com.chucknorris.server.command.response;

import com.chucknorris.commons.Position;

import java.util.Queue;

public class GameResponse extends ServerResponse {
	public int movementsLeft;
	public int diceResult;
	public Queue<Position> positionPathQueue;
	public String gameId;
	public boolean launchMiniGame;
	//public boolean salida;
}