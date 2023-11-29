package server.router;

import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import server.Player;
import server.SocketHandler;
import server.service.Arena;
import server.service.Lobby;

import static org.mockito.Mockito.*;

public class AnswerTest {
    @Mock
    private SocketHandler mockSocketHandler;

    @Mock
    private Lobby mockLobby;

    @Mock
    private Arena mockArena;

    @Test
    public void testHandle() throws Exception {
        MockitoAnnotations.openMocks(this);

        Answer answerRouter = new Answer();
        answerRouter.lobby = mockLobby;

        String payload = "player123;2";

        when(mockLobby.findArenaByPlayerId("player123")).thenReturn(mockArena);
        when(mockArena.canStartGame()).thenReturn(true);
        when(mockArena.getId()).thenReturn("123");

        Player mockWinner = mock(Player.class);
        when(mockArena.getResult()).thenReturn(mockWinner);
        when(mockWinner.getChoice()).thenReturn("P");
        when(mockWinner.getId()).thenReturn("player123");

        answerRouter.handle(payload, mockSocketHandler);

        verify(mockSocketHandler).sendMessage("player123;O resultado foi: Par voce venceu!");
        verify(mockSocketHandler).sendMessage("player123;FINISH");
        verify(mockLobby).removeArena(anyString());
    }
}
