package com.cgmaniac;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class SnakeTest {
    @Test
    public void getSnakeTestInitialPosition() {
        // Prepare
        Snake snake = new Snake(new Position(100, 100), 20);

        // Act
        var snakeBodyAndHead = snake.getSnake();

        // Assertion
        assertEquals(snakeBodyAndHead.getFirst(), new Cell(new Position(100, 100), 20));
        assertEquals(snakeBodyAndHead.getLast(), new Cell(new Position(60, 100), 20));
    }

    @Test
    public void getSnakeMoveOneTest() {
        // Prepare
        Snake snake = new Snake(new Position(100, 100), 20);

        // Act
        var snakeBodyAndHead = snake.move();

        // Assertion
        assertEquals(snakeBodyAndHead.getFirst(), new Cell(new Position(120, 100), 20));
        assertEquals(snakeBodyAndHead.getLast(), new Cell(new Position(80, 100), 20));
    }

    @Test
    public void getSnakeTurnDownMoveOneTest() {
        // Prepare
        Snake snake = new Snake(new Position(100, 100), 20);

        // Act
        snake.setDirection(Direction.DOWN);
        var snakeBodyAndHead = snake.move();

        // Assertion
        assertEquals(snakeBodyAndHead.getFirst(), new Cell(new Position(100, 120), 20));
        assertEquals(snakeBodyAndHead.getLast(), new Cell(new Position(80, 100), 20));
    }
    @Test
    public void getSnakeTurnUpMoveOneTest() {
        // Prepare
        Snake snake = new Snake(new Position(100, 100), 20);

        // Act
        snake.setDirection(Direction.UP);
        var snakeBodyAndHead = snake.move();

        // Assertion
        assertEquals(snakeBodyAndHead.getFirst(), new Cell(new Position(100, 80), 20));
        assertEquals(snakeBodyAndHead.getLast(), new Cell(new Position(80, 100), 20));
    }

    @Test
    public void getSnakeTurnLeftMoveOneTest() {
        // Prepare
        Snake snake = new Snake(new Position(100, 100), 20);

        // Act
        snake.setDirection(Direction.LEFT);
        var snakeBodyAndHead = snake.move();

        // Assertion
        assertEquals(snakeBodyAndHead.getFirst(), new Cell(new Position(80, 100), 20));
        assertEquals(snakeBodyAndHead.getLast(), new Cell(new Position(80, 100), 20));
    }
    @Test
    public void getSnakeCrossBorder() {
        //Prepare 
        Snake snake = new Snake(new Position(Game.WIDTH-20,100),20);
        System.out.println(Game.WIDTH);
        System.out.println(Game.HEIGHT);

        //Act
        snake.setDirection(Direction.RIGHT);
        var snakeBodyAndHead = snake.move();
        System.out.println(snake.getSnake());

        //Assertion
        assertEquals(new Cell(new Position(0, 100), 20),snakeBodyAndHead.getFirst() );
        assertEquals( new Cell(new Position(Game.WIDTH-40, 100), 20),snakeBodyAndHead.getLast());
    }
}
