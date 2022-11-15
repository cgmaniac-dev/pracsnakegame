package com.cgmaniac;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.Test;

public class SnakeTest {
    @Test
    public void getSnakeTestInitialPosition(){
        //Prepare
        Snake snake = new Snake(new Position(100, 100),20);

        //Act
        var snakeBodyAndHead = snake.getSnake();

        //Assertion
        assertEquals(snakeBodyAndHead.getFirst(), new Cell(new Position(100, 100), 20));
        assertEquals(snakeBodyAndHead.getLast(), new Cell(new Position(60, 100), 20));
    } 

    @Test
    public void getSnakeMoveOneTest(){
        //Prepare 
        Snake snake = new Snake(new Position(100,100),20);

        //Act
        var snakeBodyAndHead = snake.move();

        //Assertion
        assertEquals(snakeBodyAndHead.getFirst(), new Cell(new Position(120, 100), 20));
        assertEquals(snakeBodyAndHead.getLast(), new Cell(new Position(80, 100), 20));
    }
}
