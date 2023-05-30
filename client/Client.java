package client;

import client.router.RouterFactory;
import client.router.RouterType;

public class Client {

  public static void main(String[] args) {

    try {
      SocketHandler socketHandler = SocketHandler.getInstance();
      socketHandler.init();

      RouterFactory router = new RouterFactory();
      router.handle(RouterType.MENU);
    } catch (Exception e) {
      System.out.println(e);
    }
  }
}
