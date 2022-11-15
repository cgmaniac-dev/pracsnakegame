package com.cgmaniac;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class CellTest {
    @Test
    public void testObjectAreEqual(){
        Position position1 = new Position(1, 1);
        Position position2 = new Position(1, 1);

        Cell cell = new Cell(position1, 20);
        Cell cell2 = new Cell(position2, 20);


        // assertion
        assertTrue(cell.equals(cell2));
        assertEquals(cell,cell2);
    }
    
    @Test
    public void testObjectAreNotEqual(){
        Position position1 = new Position(3, 1);
        Position position2 = new Position(1, 1);
        
        Cell cell = new Cell(position1, 20);
        Cell cell2 = new Cell(position2, 20);
        // assertion
        assertFalse(cell==cell2);
        assertFalse(cell.equals(cell2));
        assertNotEquals(cell,cell2);
    }
}
