package com.chucknorris.node;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.chucknorris.commons.Position;
import com.chucknorris.gamemap.nodes.InvPesosMinusNode;
import com.chucknorris.gamemap.nodes.Node;
import com.chucknorris.player.Player;

public class InvPesosMinusNodeTest {
	Player p;
	Node nodeTest;

	@Before
	public void executedBeforeEach() {
		p = new Player("Cristi", 100, 150);
		nodeTest = new InvPesosMinusNode(null, new Position(4, 4));
	}

	@Test
	public void invPesosMinusRewardTest() {
		nodeTest.applyReward(p, null, null);
		assertEquals(p.printWithPesos(), p.getCharacter() + " " + "90.0");
	}

	@Test
	public void invPesosMinusTypeTest() {
		assertEquals("IPM", nodeTest.getType());
	}
}