package server;

public class Main {

  public static void main(String[] args) {
    String message = "";

    try {
      SocketHandler socketHandler = new SocketHandler();
      socketHandler.init();

      while (!message.equals("exit")) {
        message = socketHandler.getMessage();
        System.out.println(message);
        socketHandler.SendMessage("Bem vindo " + message);
      }

    } catch (Exception e) {
      System.out.println("Error.");
    }
  }
}