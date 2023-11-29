package server;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

public class PlayerTest {
    @Mock
    private SocketHandler socketHandler;

    @InjectMocks
    private Player player;

    @Before
    public void setup() {
        MockitoAnnotations.openMocks(this);
        player = new Player("Paulo", socketHandler);
    }

    @Test
    public void testPlayerInitialization() {
        assertEquals("Paulo", player.getName());
        assertNotNull(player.getId());
        assertEquals(socketHandler, player.getConnection());
    }

    @Test
    public void testSetAndGetChoice() throws IOException {
        player.setChoice("P");
        assertEquals("P", player.getChoice());
    }

    @Test
    public void testSetAndGetAnswer() {
        player.setAnswer("5");
        assertEquals("5", player.getAnswer());
    }

    @Test
    public void testSetName() {
        player.setName("Paulo");
        assertEquals("Paulo", player.getName());
    }

    @Test
    public void testSetAndGetConnection() {
        SocketHandler newSocketHandler = mock(SocketHandler.class);
        player.setConnection(newSocketHandler);
        assertEquals(newSocketHandler, player.getConnection());
    }
}
