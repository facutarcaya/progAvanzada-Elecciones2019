package com.chucknorris.node.test;

import com.chucknorris.commons.Position;
import com.chucknorris.mapa.Node;
import com.chucknorris.mapa.RedNode;
import com.chucknorris.mapa.WhiteNode;
import com.chucknorris.mapa.YellowNode;
import com.chucknorris.player.Player;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class NodeTest {

    Player p;

    @Before
    public void executedBeforeEach() {
        p = new Player("Milei", 0);
    }


    @Test
    public void nodeTest() {
        Node node1 = new YellowNode(null, new Position(3, 3));
        Node node2 = new RedNode(null, new Position(3, 4));
        ArrayList<Node> lista = new ArrayList<Node>();
        lista.add(node1);
        lista.add(node2);
        Node nodeTest = new RedNode(lista, new Position(4, 4));

        assertEquals(nodeTest.getType(), "RED");
        assertEquals(nodeTest.nextNodes(), lista);
    }

    @Test
    public void nodeRedTest() {
        Node nodeTest = new RedNode(null, new Position(4, 4));

        nodeTest.applyRewards(p);
        assertEquals(p.getCoins(), 3);
    }

    @Test
    public void nodeYellowTest() {
        Node nodeTest = new YellowNode(null, new Position(4, 4));

        nodeTest.applyRewards(p);
        assertEquals(p.getCoins(), 10);
    }

    @Test
    public void nodeWhiteTest() {
        Node nodeTest = new WhiteNode(null, new Position(4, 4));

        nodeTest.applyRewards(p);
        assertEquals(p.getCoins(), 0);
    }
}