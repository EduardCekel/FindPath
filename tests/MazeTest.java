package tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import maze.Field;
import org.junit.Test;


public class MazeTest {
    
    @Test
    public void testField()
    {
        Field field_ = new Field(0);
        String aMaze_ = ".....\n.S.#.\n...#.\n...#X\n.....";
        field_.setDefaultMaze(aMaze_);
        // Validate field
        assertTrue(field_.verifyMaze());
        assertEquals("d,d,r,u,u,u,r,r,d,d,d", field_.findPath());
        aMaze_ = ".....\n.S...\n...#.\n...#X\n..A..";
        field_.setDefaultMaze(aMaze_);
        assertFalse(field_.verifyMaze());
        aMaze_ = "...#....\n.S.#....\n...#....\n...#X...\n...#....";
        field_.setDefaultMaze(aMaze_);
        assertEquals("ERROR! Path doesn't exist!", field_.findPath());
    }
}
