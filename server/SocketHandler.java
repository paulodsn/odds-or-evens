package server;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class SocketHandler {
  Socket socket;
  Scanner scanner;
  PrintStream printStream;
  SeverSocketHandler serverSocketHandler = SeverSocketHandler.getInstance();

  public void waitConnection() throws IOException {
    this.socket = this.serverSocketHandler.getSocket();
    System.out.println("[Application] Connected ");

    this.scanner = new Scanner(socket.getInputStream());
  }

  public String getMessage() throws IOException {
    return this.scanner.nextLine();
  }

  public void sendMessage(String message) throws IOException {
    printStream = new PrintStream(socket.getOutputStream());
    printStream.println(message);
  }

  public void stop() throws IOException {
    this.socket.close();
  }

  public Socket getSocket() {
    return this.socket;
  }
}
