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

      // System.out.println("[Server]: Vamos jogar um jogo chamado \"Par ou Impar\"");

      // while (!message.equals("exit")) {
      // System.out.println("[Server]: Qual o seu nome?");

      // String msg = inputScanner.nextLine();
      // socketHandler.sendMessage(msg);

      // message = socketHandler.receiveMessage();
      // System.out.println("[Server]: " + message);
      // }

      // System.out.println("Encerrando conexão.");
      // socketHandler.stop();
    } catch (Exception e) {
      System.out.println(e);
    }
  }
}
