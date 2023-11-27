package server.service;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class ArenaTest {
    @Test
    public void testArenaConstructor() {
        Arena arena = new Arena();
        assertNotNull(arena.getId());
    }
}
