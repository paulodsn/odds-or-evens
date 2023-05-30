package server;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class SocketHandler {
  private static SocketHandler instance;

  Socket socket;
  Scanner scanner;
  ServerSocket serverSocket;
  PrintStream printStream;

  private SocketHandler() {
  }

  public static synchronized SocketHandler getInstance() {
    if (instance == null) {
      instance = new SocketHandler();
    }

    return instance;
  }

  public void listen() throws IOException {
    System.out.println("[Application] listening ");
    this.serverSocket = new ServerSocket(5432);
  }

  public void waitConnection() throws IOException {
    socket = serverSocket.accept();
    System.out.println("[Application] Connected ");

    this.scanner = new Scanner(socket.getInputStream());
  }

  public String getMessage() throws IOException {
    return this.scanner.nextLine();
  }

  public void SendMessage(String message) throws IOException {
    printStream = new PrintStream(socket.getOutputStream());
    printStream.println(message);
  }

  public void stop() throws IOException {
    this.socket.close();
    this.serverSocket.close();
  }

  public Socket getSocket() {
    return this.socket;
  }
}
