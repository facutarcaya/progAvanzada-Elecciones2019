package com.chucknorris.player;

import java.util.List;

import com.chucknorris.rewards.GameContext;
import com.chucknorris.rewards.substractor.pesos.PesosPercentagePlayerSubstractor;

public class AlexCaniggia extends Player {
	public AlexCaniggia(double pesos,double dolares) {
		super("Alex Caniggia", pesos, dolares);
	}
	
	public void applyPowerup(List<Player> players, GameContext gameContext) {
		double pesosP;
		double dolaresP;
		
		for(Player player : players) {
			player.substractCurrency(new PesosPercentagePlayerSubstractor(50));
			dolaresP = player.getDolares();
			pesosP = player.getPesos();
			player.substractCurrency(new PesosPercentagePlayerSubstractor(100));
			//player.substractCurrency(new DolaresPercentagePlayerSubstractor(100));
			player.addDolar(pesosP);
			player.addPesos(dolaresP);
		}
	}
	
	public String getPowerupDescription() {
		return "Resta un 50% de los pesos de todos los jugadores y los cambia con d�lares\nSOY HYPER GROSO";
	}
	
}
