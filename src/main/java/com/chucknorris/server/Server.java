package com.chucknorris.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

import com.chucknorris.Command;
import com.chucknorris.client.GameInformation;
import com.chucknorris.commons.Dice;
import com.chucknorris.commons.Position;
import com.chucknorris.game.Game;
import com.chucknorris.gamemap.GameMap;
import com.chucknorris.gamemap.initiallizer.file.reader.csv.MapFileCSVReader;
import com.chucknorris.gamemap.nodes.Node;
import com.chucknorris.player.Cristina;
import com.chucknorris.player.DelCanio;
import com.chucknorris.player.Espert;
import com.chucknorris.player.Macri;
import com.chucknorris.player.Player;
import com.google.gson.Gson;

public class Server {
	private static ServerSocket serverSocket = null;
	private static Socket clientSocket = null;
	private static final int portNumber = 22222;
	private static Socket clientMinigameSocket;
	private static final int portNumberMinigame = 22223;
	private static ServerSocket serverSocketMinigame=null;
	static Semaphore semaphore2 = new Semaphore(4);

	public static void main(String args[]) throws Exception {

		
		try {
			serverSocket = new ServerSocket(portNumber);
			serverSocketMinigame = new ServerSocket(portNumberMinigame);
		} catch (IOException e) {
			System.out.println(e);
		}
		List<ClientThread> threads = new ArrayList<ClientThread>();
		List<ClientMinigameThread> threadsMinigame = new ArrayList<ClientMinigameThread>();
		
		GameMap mapa1;
		MapFileCSVReader mapFileCSVReader = new MapFileCSVReader("newMap1.txt");
		mapa1 = mapFileCSVReader.buildGameMap();
		Node ini = mapa1.getMap().get(new Position(0, 0));
		Espert p1 = new Espert(150, 100, 300);
		Cristina p2 = new Cristina(150, 100, 300);
		Macri p3 = new Macri(150, 100, 300);
		DelCanio p4 = new DelCanio(150, 100, 300);
		p1.setNodeLocation(ini);
		p2.setNodeLocation(ini);
		p3.setNodeLocation(ini);
		p4.setNodeLocation(ini);
		List<Player> listaP = new ArrayList<Player>();
		listaP.add(p1);
		listaP.add(p2);
		listaP.add(p3);
		listaP.add(p4);
		Game juego01 = new Game(listaP, mapa1, 20);
		GameInformation info = new GameInformation(listaP, mapa1, juego01.getPrecioDolar());
		Gson gson = new Gson();
		String infoSerialized = gson.toJson(info);
		Command startGame = new Command("StartGame", infoSerialized);
		Command habilitarBoton = new Command("TirarDado", "");
		for (int i = 0; i < 4; i++) {
			try {
				System.out.println("Esperando nueva conexion");
				clientSocket = serverSocket.accept();
				clientMinigameSocket = serverSocketMinigame.accept();
				System.out.println("Se conecto alguien");
				threads.add(new ClientThread(clientSocket, threads, juego01));
				threadsMinigame.add(new ClientMinigameThread(clientMinigameSocket, threadsMinigame));
			} catch (IOException e) {
				System.out.println(e);
			}
		}
		for (int i = 0; i < threads.size(); i++) {
			
			threads.get(i).start();
			threadsMinigame.get(i).start();
			threads.get(i).send(startGame, i);
		}
		threads.get(0).send(habilitarBoton, 0);

	}
}
