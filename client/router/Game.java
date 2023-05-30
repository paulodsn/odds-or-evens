package client.router;

import java.util.Scanner;
import client.SocketHandler;

public class Game implements Router {
  SocketHandler socketHandler = SocketHandler.getInstance();
  Scanner scanner = new Scanner(System.in);

  @Override
  public void display() {
    this.welcome();
  }

  public void header() {
    System.out.println("---------------------");
    System.out.println("PAR OU IMPAR");
    System.out.println("---------------------");
  }

  public void welcome() {
    this.header();
    System.out.print("Digite seu nome: ");

  }

  @Override
  public void handleChoice() {
    String name = scanner.nextLine();

    while (true) {
      socketHandler.sendMessage("/welcome " + name);

      String message = socketHandler.receiveMessage();
      System.out.println(message);
    }
  }

}
