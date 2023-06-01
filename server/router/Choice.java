package server.router;

import java.io.IOException;
import java.util.Random;

import server.Player;
import server.SocketHandler;
import server.service.Arena;
import server.service.Lobby;

public class Choice implements Router {
  Lobby lobby = Lobby.getInstance();
  Integer maximumTime = 20000;

  @Override
  public void handle(String payload, SocketHandler response) throws IOException {
    String[] parts = payload.split(";");
    String playerId = parts[0];
    String choice = parts[1];

    Arena arena = lobby.findArenaByPlayerId(playerId);
    arena.setChoice(playerId, choice);

    Integer currentTime = 0;
    while (!arena.canDischargeChoice() || arena.getStatus() != "closed") {
      try {
        response.sendMessage(playerId + ";Esperando o outro jogador escolher");
        Thread.sleep(10000);
        currentTime += 10000;

        if (currentTime.equals(maximumTime)) {
          String avaiableChoice = arena.getAvailableChoice();
          Random random = new Random();
          int randomNumber = random.nextInt(5) + 1;
          Player boot = new Player("Bot", avaiableChoice, String.valueOf(randomNumber));
          arena.allocateSlot(boot);

          arena.sendToPlayerInArena("START");
        }

      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }

  }
}
