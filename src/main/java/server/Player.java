package server;

import java.util.UUID;

public class Player {
  private String id;
  private String name;
  private String answer;
  private String choice;

  private SocketHandler connection;

  public Player(String name, SocketHandler connection) {
    this.id = UUID.randomUUID().toString();

    this.name = name;
    this.connection = connection;
  }

  public Player(String name, String choice, String answer) {
    this.id = UUID.randomUUID().toString();

    this.name = name;
    this.choice = choice;
    this.answer = answer;
  }

  public String getChoice() {
    return choice;
  }

  public void setChoice(String choice) {
    this.choice = choice;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getId() {
    return this.id;
  }

  public void setAnswer(String answer) {
    this.answer = answer;
  }

  public String getAnswer() {
    return this.answer;
  }

  public SocketHandler getConnection() {
    return connection;
  }

  public void setConnection(SocketHandler connection) {
    this.connection = connection;
  }
}
