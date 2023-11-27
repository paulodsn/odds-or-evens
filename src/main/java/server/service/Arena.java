package server.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import server.SocketHandler;
import server.Player;

public class Arena {
  private String id;
  private String status = "open";
  private Integer slots = 2;

  private Map<String, Player> players = new HashMap<>();

  public Arena() {
    this.id = UUID.randomUUID().toString();
  }

  public void allocateSlot(Player player) {
    if (players.size() < this.slots) {
      if (players.size() == 1) {
        String avaiableChoice = this.getAvailableChoice();
        player.setChoice(avaiableChoice);
      }

      players.put(player.getId(), player);

      if (players.size() == this.slots) {
        this.status = "closed";
      }
    }
  }

  public void sendToPlayerInArena(String message) {
    for (Player player : players.values()) {
      SocketHandler connection = player.getConnection();

      try {
        if (connection != null) {
          connection.sendMessage(player.getId() + ";" + message);
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  public boolean canDischargeChoice() {
    boolean response = true;
    for (Player player : players.values()) {
      if (player.getChoice() == null) {
        response = false;
      }
    }

    return response;
  }

  public boolean canStartGame() {
    boolean response = true;
    for (Player player : players.values()) {
      if (player.getAnswer() == null) {
        response = false;
      }
    }

    return response;
  }

  public String getAvailableChoice() {
    String availableChoice = "P";

    for (Player player : players.values()) {
      String playerAnswer = player.getChoice();

      if (availableChoice.equals(playerAnswer)) {
        availableChoice = "I";
      }
    }

    return availableChoice;
  }

  public Player getResult() {
    Player winner = null;
    Integer sum = 0;

    for (Player player : players.values()) {
      sum += Integer.parseInt(player.getAnswer());
    }

    String result = sum % 2 == 0 ? "P" : "I";
    for (Player player : players.values()) {
      if (player.getChoice().equals(result)) {
        winner = player;
      }
    }

    return winner;
  }

  public boolean hasPlayer(String playerId) {
    return players.containsKey(playerId);
  }

  public void setAnswer(String playerId, String answer) {
    Player player = players.get(playerId);
    player.setAnswer(answer);
  }

  public void setChoice(String playerId, String choice) {
    Player player = players.get(playerId);
    player.setChoice(choice);
  }

  public String getStatus() {
    return this.status;
  }

  public String getId() {
    return this.id;
  }
}
