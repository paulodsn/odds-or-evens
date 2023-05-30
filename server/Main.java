package server;

import server.router.RouterFactory;

public class Main {

  public static void main(String[] args) {
    try {
      RouterFactory routerFactory = new RouterFactory();
      SocketHandler socketHandler = SocketHandler.getInstance();
      socketHandler.init();

      while (true) {
        String request = socketHandler.getMessage();
        routerFactory.handle(request);
      }

    } catch (Exception e) {
      System.out.println(e);
    }
  }

}