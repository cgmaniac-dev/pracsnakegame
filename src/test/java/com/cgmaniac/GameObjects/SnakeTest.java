package com.cgmaniac.GameObjects;

import static org.junit.jupiter.api.Assertions.*;

import java.util.LinkedList;

import org.junit.jupiter.api.Test;

import com.cgmaniac.Cell;
import com.cgmaniac.Direction;
import com.cgmaniac.Game;
import com.cgmaniac.Position;

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
        snake.setCanMove(true);
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
        snake.setCanMove(true);
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
        snake.setCanMove(true);
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
        snake.setCanMove(true);
        var snakeBodyAndHead = snake.move();

        // Assertion
        assertEquals(snakeBodyAndHead.getFirst(), new Cell(new Position(80, 100), 20));
        assertEquals(snakeBodyAndHead.getLast(), new Cell(new Position(80, 100), 20));
    }
    @Test
    public void getSnakeCrossBorderRight() {
        //Prepare 
        Snake snake = new Snake(new Position(Game.WIDTH-20,100),20);
        System.out.println(Game.WIDTH);
        System.out.println(Game.HEIGHT);

        //Act
        snake.setDirection(Direction.RIGHT);
        snake.setCanMove(true);
        var snakeBodyAndHead = snake.move();
        System.out.println(snake.getSnake());

        //Assertion
        assertEquals(new Cell(new Position(0, 100), 20),snakeBodyAndHead.getFirst() );
        assertEquals( new Cell(new Position(Game.WIDTH-40, 100), 20),snakeBodyAndHead.getLast());
    }

    @Test
    public void getSnakeCrossBorderDown() {
        //Prepare 
        Snake snake = new Snake(new Position(100,Game.HEIGHT-20),20);
        System.out.println(Game.WIDTH);
        System.out.println(Game.HEIGHT);

        //Act
        snake.setDirection(Direction.DOWN);
        snake.setCanMove(true);
        var snakeBodyAndHead = snake.move();
        System.out.println(snake.getSnake());

        //Assertion
        assertEquals(new Cell(new Position(100, 0), 20),snakeBodyAndHead.getFirst() );
        assertEquals( new Cell(new Position(80, Game.HEIGHT-20), 20),snakeBodyAndHead.getLast());
    }

    @Test
    public void getSnakeCrossBorderLeft() {
        //Prepare 
        Snake snake = new Snake(new Position(40,100),20);
        System.out.println(Game.WIDTH);
        System.out.println(Game.HEIGHT);

        //Act
        snake.setDirection(Direction.LEFT);
        snake.setCanMove(true);
        var snakeBodyAndHead = snake.move();
        snakeBodyAndHead = snake.move();
        snakeBodyAndHead = snake.move();
        System.out.println(snake.getSnake());

        //Assertion
        assertEquals(new Cell(new Position(980, 100), 20),snakeBodyAndHead.getFirst() );
        assertEquals( new Cell(new Position(20, 100), 20),snakeBodyAndHead.getLast());
    }

    @Test
    public void getSnakeCrossBorderUp() {
        //Prepare 
        Snake snake = new Snake(new Position(40,0),20);
        System.out.println(Game.WIDTH);
        System.out.println(Game.HEIGHT);

        //Act
        snake.setDirection(Direction.UP);
        snake.setCanMove(true);
        var snakeBodyAndHead = snake.move();
        System.out.println(snake.getSnake());

        //Assertion
        assertEquals(new Cell(new Position(40, Game.HEIGHT-20), 20),snakeBodyAndHead.getFirst() );
        assertEquals( new Cell(new Position(20, 0), 20),snakeBodyAndHead.getLast());
    }

    @Test
    public void getSnakeGrow() {
        //Prepare 
        Snake snake = new Snake(new Position(100, 100), 20);

        // Act
        snake.setCanMove(true);
        snake.grow();

        LinkedList<Cell> snakeBodyAndHead = snake.move();

        // Assertion
        assertEquals(snakeBodyAndHead.getFirst(), new Cell(new Position(120, 100), 20));
        assertEquals(snakeBodyAndHead.getLast(), new Cell(new Position(60, 100), 20));
    }

    @Test
    public void getSnakeCollisionToItSelf() {
        //Prepare 
        Snake snake = new Snake(new Position(100, 100), 20);
        // Act
        snake.setCanMove(true);

        snake.grow();
        snake.move();
        Boolean collision =  snake.checkSelfCollision();
        assertFalse(collision);

        snake.grow();
        snake.move();
        collision =  snake.checkSelfCollision();
        assertFalse(collision);

        snake.setDirection(Direction.DOWN);
        snake.move();
        collision =  snake.checkSelfCollision();
        assertFalse(collision);

        snake.setDirection(Direction.LEFT);
        snake.move();
        collision =  snake.checkSelfCollision();
        assertFalse(collision);

        snake.setDirection(Direction.UP);
        snake.move();
        collision =  snake.checkSelfCollision();
        assertTrue(collision);

    }
    
}
