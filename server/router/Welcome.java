package server.router;

import java.io.IOException;

import server.Player;
import server.SocketHandler;
import server.service.Arena;
import server.service.Gym;

public class Welcome implements Router {
  SocketHandler socketHandler = SocketHandler.getInstance();
  Gym gym = Gym.getInstance();

  @Override
  public void handle(String name) throws IOException {
    Player player = new Player(name);
    Arena arena = gym.getArena();

    arena.allocateSlot(player);

    if (arena.getStatus() == "closed") {
      arena.sendAll("[Server]: Jogo iniciando");
    } else {
      socketHandler.SendMessage("[Server]: Esperando outro jogador");
    }
  }
}
