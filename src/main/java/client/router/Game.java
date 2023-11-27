package client.router;

import java.util.Scanner;
import client.SocketHandler;
import client.util.Response;

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
    socketHandler.sendMessage("/welcome;" + name);

    while (true) {
      String responseMessage = socketHandler.receiveMessage();
      Response response = new Response(responseMessage);

      String message = response.getMessage();

      if (message.equals("CHOOSE")) {
        System.out.print("Qual a sua escolha? (P)ar ou (I)mpar? ");
        String choice = scanner.nextLine();

        Boolean isValidChoice;
        String chooseToSend;

        do {
          isValidChoice = choice.equals("P") || choice.equals("I");

          if (isValidChoice) {
            chooseToSend = choice;
            socketHandler.sendMessage("/choice;" + response.getId() + ";" + chooseToSend);
          } else {
            System.out.println("Opção não encontrada, digite novamente: ");
            chooseToSend = scanner.nextLine();
          }
        } while (!isValidChoice);

      } else if (response.getMessage().equals("START")) {
        System.out.print("Digite um número de 1 a 5: ");
        String number = scanner.nextLine();

        socketHandler.sendMessage("/answer;" + response.getId() + ";" + number);
      } else if (response.getMessage().equals("FINISH")) {
        System.out.println(" ----------------- \n" +
            "Jogo finalizado. \n" +
            "Digite qualquer tecla para volta para o menu. ");

        scanner.nextLine();
        RouterFactory router = new RouterFactory();
        router.handle(RouterType.MENU);
      } else {
        System.out.println(message);
      }

    }
  }
}
