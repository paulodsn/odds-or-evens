package server.router;

import java.io.IOException;

public interface Router {
  void handle(String payload) throws IOException;

}
