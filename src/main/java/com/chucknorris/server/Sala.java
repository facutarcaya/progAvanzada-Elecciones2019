package com.chucknorris.server;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.chucknorris.game.Game;

public class Sala {
	public List<String> players;
	public Map<String,ClientLobbyThread> threadsMap;
	public boolean playing;
	public String name;
	public Game juego;
	
	public Sala(String name) {
		this.players = new ArrayList<String>();
		this.threadsMap = new HashMap<String, ClientLobbyThread>();
		this.playing = false;
		this.name = name;
		this.juego = null;
	}
}