package server.router;

import java.io.IOException;

import server.Player;
import server.SocketHandler;
import server.service.Arena;
import server.service.Lobby;

public class Answer implements Router {
    Lobby lobby = Lobby.getInstance();

    @Override
    public void handle(String payload, SocketHandler response) throws IOException {
        String[] parts = payload.split(";");
        String playerId = parts[0];
        String number = parts[1];

        Arena arena = lobby.findArenaByPlayerId(playerId);
        arena.setAnswer(playerId, number);

        while (!arena.canStartGame()) {
            try {
                response.sendMessage(playerId + ";Esperando a resposta do outro jogador");
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        Player winner = arena.getResult();
        String result = winner.getChoice().equals("P") ? "Par" : "Impar";

        if (winner.getId().equals(playerId)) {
            response.sendMessage(winner.getId() + ";O resultado foi: " + result + " voce venceu!");
        } else {
            response.sendMessage(winner.getId() + ";O resultado foi: " + result + " voce perdeu!");
        }

        response.sendMessage(playerId + ";FINISH");
        lobby.removeArena(arena.getId());
    }
}
