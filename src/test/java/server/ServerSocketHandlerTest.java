package server;
import org.junit.Before;
import org.junit.Test;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class ServerSocketHandlerTest {

    private  SeverSocketHandler severSocketHandler;

    @Before
    public void setup() {
        severSocketHandler = SeverSocketHandler.getInstance();
    }

    @Test
    public void testGetInstance () {
        assertNotNull(severSocketHandler);
        assertSame(severSocketHandler, SeverSocketHandler.getInstance());
    }

    @Test
    public void testSocketInstance() throws IOException {
        ServerSocket mockedServerSocket = mock(ServerSocket.class);
        when(mockedServerSocket.accept()).thenReturn(mock(Socket.class));

        severSocketHandler.serverSocket = mockedServerSocket;
        severSocketHandler.listen();

        assertNotNull(severSocketHandler.serverSocket);
    }

    @Test
    public void testGetSocket() throws IOException {
        ServerSocket mockedServerSocket = mock(ServerSocket.class);
        Socket mockedSocket = mock(Socket.class);
        when(mockedServerSocket.accept()).thenReturn(mockedSocket);

        severSocketHandler.serverSocket = mockedServerSocket;
        Socket socket = severSocketHandler.getSocket();

        verify(mockedServerSocket, times(1)).accept();
        assertNotNull(socket);
    }

    @Test
    public void testClose() throws IOException {
        ServerSocket mockedServerSocket = mock(ServerSocket.class);
        Socket mockedSocket = mock(Socket.class);
        when(mockedServerSocket.accept()).thenReturn(mockedSocket);

        severSocketHandler.serverSocket = mockedServerSocket;
        severSocketHandler.close();

        verify(mockedServerSocket, times(1)).close();
    }

}
