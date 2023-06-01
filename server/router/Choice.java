package server.router;

import java.io.IOException;

import server.SocketHandler;
import server.service.Arena;
import server.service.Lobby;

public class Choice implements Router {
  Lobby lobby = Lobby.getInstance();

  @Override
  public void handle(String payload, SocketHandler response) throws IOException {
    String[] parts = payload.split(";");
    String playerId = parts[0];
    String choice = parts[1];

    Arena arena = lobby.findArenaByPlayerId(playerId);
    arena.setChoice(playerId, choice);

    while (!arena.canDischargeChoice() || arena.getStatus() != "closed") {
      try {
        response.sendMessage(playerId + ";Esperando o outro jogador escolher");
        Thread.sleep(5000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }

  }
}
