package server.router;

import java.io.IOException;

import server.SocketHandler;

public interface Router {
  void handle(String payload, SocketHandler response) throws IOException;

}
