package server;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import server.router.RouterFactory;

import java.io.IOException;
import static org.mockito.Mockito.*;

public class HandleRequestTest {
    @Mock
    private SocketHandler socketHandler;

    @Mock
    RouterFactory routerFactory;

    @InjectMocks
    private HandleRequest handleRequest;

    @Before
    public void setup() {
        MockitoAnnotations.openMocks(this);
        handleRequest = new HandleRequest(socketHandler, routerFactory);
    }

    @Test
    public void testRun() throws IOException {
        when(socketHandler.getMessage()).thenReturn("Test");

        handleRequest.execute();
        verify(routerFactory).handle("Test", socketHandler);
    }
}
