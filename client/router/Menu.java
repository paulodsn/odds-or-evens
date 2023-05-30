package client.router;

import java.util.Scanner;

public class Menu implements Router {

  @Override
  public void display() {
    System.out.flush();

    System.out.println("---------------------");
    System.out.println("PAR OU IMPAR - MENU");
    System.out.println("---------------------\n");
    System.out.println("1. Iniciar jogo");
    System.out.println("2. Sobre o jogo");
    System.out.println("3. Exit");
    System.out.print("\nDigite a opção escolhida: ");
  }

  @Override
  public void handleChoice() {
    Integer choice = getChoice();

    switch (choice) {
      case 1: {
        RouterFactory router = new RouterFactory();
        router.handle(RouterType.GAME);

        break;
      }
      case 2: {
        RouterFactory router = new RouterFactory();
        router.handle(RouterType.ABOUT_GAME);

        break;
      }
      case 3: {
        System.out.println("WIP");
        break;
      }
      default: {
        System.out.println("Opção incorreta.");
      }
    }
  }

  public Integer getChoice() {
    Scanner scanner = new Scanner(System.in);
    Integer choice = scanner.nextInt();

    return choice;
  }
}
