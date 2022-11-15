package com.cgmaniac;

import static org.junit.jupiter.api.Assertions.*;

import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.Test;

public class SnakeTest {
    @Test
    public void getSnakeTest(){
        //Prepare
        Snake snake = new Snake(new Position(0, 0),20);

        //Act
        var snakeBodyAndHead = snake.getSnake();

        //Assertion
        assertEquals(snakeBodyAndHead.getFirst(), new Cell(new Position(0, 0), 20));
        assertEquals(snakeBodyAndHead.getLast(), new Cell(new Position(-40, 0), 20));
    } 
}
