package server;

import java.io.IOException;

import server.router.RouterFactory;

public class HandleRequest extends Thread {
  SocketHandler socketHandler;
  RouterFactory routerFactory;

  public HandleRequest(SocketHandler socketHandler, RouterFactory routerFactory) {
    this.socketHandler = socketHandler;
    this.routerFactory = routerFactory;
  }

  @Override
  public void run() {
    while (true) {
      String request;
      try {
        request = socketHandler.getMessage();
        this.routerFactory.handle(request, socketHandler);
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }
}
