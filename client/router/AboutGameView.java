package client.router;

import java.util.Scanner;

public class AboutGameView implements Router {

  @Override
  public void display() {
    System.out.println("---------------------");
    System.out.println("SOBRE O JOGO");
    System.out.println("---------------------\n");
    System.out.println(
        "Um diz \"par\", e o outro, \"ímpar\", mantendo as mãos atrás, fechadas.\n" +
            "Depois, os dois trazem uma das mãos para a frente ao mesmo tempo,\n" +
            "apresentando zero, um, dois, três, quatro ou cinco dedos. Somam-se \n" +
            "o número de dedos colocados pelos dois. Se a soma é um número par, \n" +
            "ganha quem disse \"par\".");
    System.out.print("\nDigite 1 para voltar: ");
  }

  @Override
  public void handleChoice() {
    Integer choice = getChoice();

    switch (choice) {
      case 1:
        Router menuRouter = RouterFactory.getView(RouterType.MENU);
        menuRouter.display();
        menuRouter.handleChoice();

        break;
    }
  }

  public Integer getChoice() {
    Scanner scanner = new Scanner(System.in);
    Integer choice = scanner.nextInt();

    return choice;
  }
}
