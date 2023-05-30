package server.service;

import java.util.ArrayList;
import java.util.List;

import server.Player;

public class Arena {
  Integer slots = 2;
  List<Player> players = new ArrayList<Player>();
  String status = "open";

  public void allocateSlot(Player player) {
    if (players.size() < this.slots) {
      players.add(player);

      System.out.println(players.size());
      if (players.size() == this.slots) {
        this.status = "closed";
      }
    }
  }

  public String getStatus() {
    return this.status;
  }
}
