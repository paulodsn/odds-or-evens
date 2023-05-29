package server;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class SocketHandler {
  Socket socket;
  ServerSocket serverSocket;
  Scanner scanner;
  PrintStream printStream;

  public void init() throws IOException {
    this.serverSocket = new ServerSocket(5432);
    System.out.println("[Application] Started server ");

    System.out.println("[Application] Waiting connection ");
    socket = serverSocket.accept();
    System.out.println("[Application] Connected ");

  }

  public String getMessage() throws IOException {
    this.scanner = new Scanner(socket.getInputStream());
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
}
