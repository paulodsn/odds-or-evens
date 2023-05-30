package server;

import server.router.RouterFactory;

public class Main {

  public static void main(String[] args) {
    try {
      RouterFactory routerFactory = new RouterFactory();
      SocketHandler socketHandler = SocketHandler.getInstance();
      socketHandler.listen();

      while (true) {
        socketHandler.waitConnection();
        Thread handleRequest = new HandleRequest(socketHandler, routerFactory);
        handleRequest.start();
      }

    } catch (Exception e) {
      System.out.println(e);
    }
  }
}