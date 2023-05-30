package server.service;

import java.util.ArrayList;
import java.util.List;

public class Gym {
  private static Gym instance;
  List<Arena> arenas = new ArrayList<Arena>();

  private Gym() {
    arenas.add(new Arena());
  }

  public static synchronized Gym getInstance() {
    if (instance == null) {
      instance = new Gym();
    }

    return instance;
  }

  public Arena getArena() {
    Integer lastArenaIndex = arenas.size() == 0 ? 0 : arenas.size() - 1;
    Arena lastArena = arenas.get(lastArenaIndex);

    if (lastArena.status == "close") {
      Arena arena = new Arena();
      arenas.add(new Arena());

      return arena;
    }

    return lastArena;
  }
}
