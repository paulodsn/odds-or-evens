package client;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class SocketHandler {
  Socket socket;
  PrintStream printStream;
  Scanner outputScanner;

  public void init() throws UnknownHostException, IOException {
    System.out.println("[Server]: Conectado ao servidor...");
    socket = new Socket("localhost", 5432);
    System.out.println("[Server]: Conectado");

    this.printStream = new PrintStream(socket.getOutputStream());
    this.outputScanner = new Scanner(socket.getInputStream());
  }

  public void sendMessage(String message) {
    printStream.println(message);
  }

  public String receiveMessage() {
    return outputScanner.nextLine();
  }

  public void stop() throws IOException {
    this.socket.close();
  }
}
