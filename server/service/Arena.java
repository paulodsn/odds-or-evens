package server.service;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import server.SocketHandler;
import server.Player;

public class Arena {
  List<Player> players = new ArrayList<Player>();
  List<Socket> connections = new ArrayList<Socket>();

  String status = "open";
  Integer slots = 2;

  public void allocateSlot(Player player) {
    if (players.size() < this.slots) {
      SocketHandler socketHandler = SocketHandler.getInstance();
      connections.add(socketHandler.getSocket());
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

  public void sendAll(String message) {
    for (Socket socket : connections) {
      PrintStream printStream;
      try {
        printStream = new PrintStream(socket.getOutputStream());
        printStream.println(message);
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }
}
