package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SeverSocketHandler {
  private static SeverSocketHandler instance;

  ServerSocket serverSocket;

  private SeverSocketHandler() {
  }

  public static synchronized SeverSocketHandler getInstance() {
    if (instance == null) {
      instance = new SeverSocketHandler();
    }

    return instance;
  }

  public void listen() throws IOException {
    System.out.println("[Application] listening ");
    this.serverSocket = new ServerSocket(5432);
  }

  public void close() throws IOException {
    this.serverSocket.close();
  }

  public Socket getSocket() throws IOException {
    return this.serverSocket.accept();
  }
}