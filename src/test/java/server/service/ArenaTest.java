package server.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import server.Player;
import server.SocketHandler;
import java.io.IOException;
import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;

public class ArenaTest {
    @Mock
    private SocketHandler socketHandler1;

    @Mock
    private SocketHandler socketHandler2;

    @InjectMocks
    private Arena arena;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        arena = new Arena();
    }

    @Test
    public void testArenaConstructor() {
        Arena arena = new Arena();
        assertNotNull(arena.getId());
    }

    @Test
    public void testAllocateSlot() {
        Player player1 = new Player("Paulo", socketHandler1);
        Player player2 = new Player("Yuri", socketHandler2);

        arena.allocateSlot(player1);
        assertEquals("open", arena.getStatus());
        assertTrue(arena.hasPlayer(player1.getId()));
        assertFalse(arena.canDischargeChoice());
        assertFalse(arena.canStartGame());

        arena.allocateSlot(player2);
        assertEquals("closed", arena.getStatus());
        assertTrue(arena.hasPlayer(player2.getId()));
        assertFalse(arena.canDischargeChoice());
        assertFalse(arena.canStartGame());
    }

    @Test
    public void testCanStartGame() {
        Player player1 = new Player("Paulo", socketHandler1);
        Player player2 = new Player("Yuri", socketHandler2);

        arena.allocateSlot(player1);
        assertEquals("open", arena.getStatus());
        assertTrue(arena.hasPlayer(player1.getId()));
        assertFalse(arena.canDischargeChoice());
        assertFalse(arena.canStartGame());

        arena.allocateSlot(player2);
        assertEquals("closed", arena.getStatus());
        assertTrue(arena.hasPlayer(player2.getId()));
        assertFalse(arena.canDischargeChoice());
        assertFalse(arena.canStartGame());

        player2.setChoice("P");
        player2.setAnswer("2");

        player1.setChoice("I");
        player1.setAnswer("2");

        assertTrue(arena.canStartGame());
    }

    @Test
    public void testSendToPlayerInArena() throws IOException {
        Player player1 = new Player("Paulo", socketHandler1);
        Player player2 = new Player("Yuri", socketHandler2);

        arena.allocateSlot(player1);
        arena.allocateSlot(player2);

        arena.sendToPlayerInArena("Ola!");

        verify(socketHandler1).sendMessage(player1.getId() + ";Ola!");
        verify(socketHandler2).sendMessage(player2.getId() + ";Ola!");
    }

    @Test
    public void testResult() {
        Player player1 = new Player("Paulo", socketHandler1);
        Player player2 = new Player("Yuri", socketHandler2);

        arena.allocateSlot(player1);
        arena.setChoice(player1.getId(), "I");
        arena.setAnswer(player1.getId(), "2");

        arena.allocateSlot(player2);
        arena.setChoice(player2.getId(), "P");
        arena.setAnswer(player2.getId(), "2");

        assertEquals(arena.getResult(), player2);
    }

    @Test
    public void testGetAvailableChoice() {
        Player player1 = new Player("Paulo", socketHandler1);

        arena.allocateSlot(player1);
        arena.setChoice(player1.getId(), "P");
        arena.setAnswer(player1.getId(), "2");

        assertEquals(arena.getAvailableChoice(), "I");
    }
}
