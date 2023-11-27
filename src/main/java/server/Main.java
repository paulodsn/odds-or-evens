package server;

import server.router.RouterFactory;

public class Main {

  public static void main(String[] args) {
    try {
      RouterFactory routerFactory = new RouterFactory();
      SeverSocketHandler serverSocketHandler = SeverSocketHandler.getInstance();
      serverSocketHandler.listen();

      while (true) {
        SocketHandler socketHandler = new SocketHandler();
        socketHandler.waitConnection();

        Thread handleRequest = new HandleRequest(socketHandler, routerFactory);
        handleRequest.start();
      }

    } catch (Exception e) {
      System.out.println(e);
    }
  }
}