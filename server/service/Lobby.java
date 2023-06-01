package server.service;

import java.util.ArrayList;
import java.util.List;

public class Lobby {
  private static Lobby instance;
  List<Arena> arenas = new ArrayList<Arena>();

  private Lobby() {
    arenas.add(new Arena());
  }

  public static synchronized Lobby getInstance() {
    if (instance == null) {
      instance = new Lobby();
    }

    return instance;
  }

  public Arena getArena() {
    Integer lastArenaIndex = arenas.size() == 0 ? 0 : arenas.size() - 1;
    Arena lastArena = arenas.get(lastArenaIndex);

    if (lastArena.getStatus() == "closed") {
      Arena arena = new Arena();
      arenas.add(new Arena());

      return arena;
    }

    return lastArena;
  }

  public Arena findArenaByPlayerId(String playerId) {
    Arena foundArena = null;
    for (Arena arena : arenas) {
      if (arena.hasPlayer(playerId)) {
        foundArena = arena;
      }
    }

    return foundArena;
  }

  public void removeArena(String arenaId) {
    arenas.removeIf(arena -> arena.getId().equals(arenaId));
    arenas.add(new Arena());
  }
}
