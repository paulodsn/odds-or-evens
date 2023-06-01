package server.router;

import java.io.IOException;

import server.SocketHandler;
import server.Player;
import server.service.Arena;
import server.service.Lobby;

public class Welcome implements Router {
  Lobby lobby = Lobby.getInstance();

  @Override
  public void handle(String name, SocketHandler response) throws IOException {
    Player player = new Player(name, response);
    Arena arena = lobby.getArena();

    arena.allocateSlot(player);

    if (arena.getStatus() == "closed") {
      String choice = player.getChoice().equals("P") ? "Par" : "Impar";
      response.sendMessage(player.getId() + ";Você escolheu " + choice);
      arena.sendToPlayerInArena("START");
    } else {
      response.sendMessage(player.getId() + ";CHOOSE");
    }
  }
}
