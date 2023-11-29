package server.service;

import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class LobbyTest {
    @Test
    public void testGetArena() {
        Arena closedArena = Mockito.mock(Arena.class);
        when(closedArena.getStatus()).thenReturn("closed");

        Arena openArena = Mockito.mock(Arena.class);
        when(openArena.getStatus()).thenReturn("open");

        Lobby lobby = Lobby.getInstance();
        lobby.arenas.clear();
        lobby.arenas.add(closedArena);
        lobby.arenas.add(openArena);


        Arena avaiableArena = lobby.getArena();

        assertEquals(avaiableArena, openArena);
        assertNotEquals(avaiableArena, closedArena);
        assertEquals("open", avaiableArena.getStatus());
    }

    @Test
    public void testGetNewArena() {
        Arena closedArena = Mockito.mock(Arena.class);
        when(closedArena.getStatus()).thenReturn("closed");

        Lobby lobby = Lobby.getInstance();
        lobby.arenas.clear();
        lobby.arenas.add(closedArena);


        Arena avaiableArena = lobby.getArena();

        assertNotEquals(avaiableArena, closedArena);
        assertEquals("open", avaiableArena.getStatus());
    }

    @Test
    public void testFindArenaByPlayerId() {
        Arena arena = Mockito.mock(Arena.class);
        when(arena.hasPlayer("player123")).thenReturn(true);

        Lobby lobby = Lobby.getInstance();
        lobby.arenas.clear();
        lobby.arenas.add(arena);

        Arena foundArena = lobby.findArenaByPlayerId("player123");

        assertNotNull(foundArena);
        assertEquals(arena, foundArena);
    }

    @Test
    public void testRemoveArena() {
        Arena arenaToRemove = Mockito.mock(Arena.class);
        when(arenaToRemove.getId()).thenReturn("arena123");


        Lobby lobby = Lobby.getInstance();
        lobby.arenas.clear();
        lobby.arenas.add(arenaToRemove);

        lobby.removeArena("arena123");

        assertEquals(1, lobby.arenas.size());
        assertNotEquals(arenaToRemove, lobby.arenas.get(0));
    }
}
