package server;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class SocketHandlerTest {
    private SocketHandler socketHandler;
    private SeverSocketHandler mockedSeverSocketHandler;
    private ByteArrayOutputStream byteArrayOutputStream;

    @Before
    public void setUp() throws IOException {
        socketHandler = new SocketHandler();

        mockedSeverSocketHandler = mock(SeverSocketHandler.class);
        Socket mockedSocket = mock(Socket.class);
        byteArrayOutputStream = new ByteArrayOutputStream();

        when(mockedSeverSocketHandler.getSocket()).thenReturn(mockedSocket);
        when(mockedSocket.getInputStream()).thenReturn(new ByteArrayInputStream("Hello, World!".getBytes()));
        when(mockedSocket.getOutputStream()).thenReturn(new PrintStream(byteArrayOutputStream));

        socketHandler.serverSocketHandler = mockedSeverSocketHandler;
    }

    @After
    public void tearDown() throws IOException {
        socketHandler.stop();
    }

    @Test
    public void testWaitConnection() throws IOException {
        socketHandler.waitConnection();
        verify(mockedSeverSocketHandler).getSocket();
    }

    @Test
    public void testGetMessage() throws IOException {
        socketHandler.waitConnection();

        String message = socketHandler.getMessage();

        assertEquals("Hello, World!", message);
    }

    @Test
    public void testSendMessage() throws IOException {
        socketHandler.waitConnection();
        socketHandler.sendMessage("Test Message");

        assertEquals("Test Message\n", byteArrayOutputStream.toString());
    }
}
