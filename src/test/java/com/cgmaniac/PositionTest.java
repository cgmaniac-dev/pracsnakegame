package com.cgmaniac;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class PositionTest {
    @Test
    public void testObjectAreEqual(){
        Position position1 = new Position(1, 1);
        Position position2 = new Position(1, 1);


        // assertion
        assertTrue(position1.equals(position2));
        assertEquals(position1,position2);
    }
    
    @Test
    public void testObjectAreNotEqual(){
        Position position1 = new Position(3, 1);
        Position position2 = new Position(1, 1);
        
        // assertion
        assertFalse(position1==position2);
        assertFalse(position1.equals(position2));
        assertNotEquals(position1,position2);
    }
}
