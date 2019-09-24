package com.chucknorris.mapa;

import java.util.ArrayList;
import com.chucknorris.commons.Position;
import com.chucknorris.player.Player;

public class EndNode extends Node {

	public EndNode(Position pos) {
		super( null , pos);
	}
	
	@Override
	public void applyRewards(Player p) {
		//por ahora nada
	}
	
	public void setStart(Node n) {
		this.next = new ArrayList<Node>();
		this.next.add(n);
	}

	@Override
	public String getType() {
		return "END";
	}

}
