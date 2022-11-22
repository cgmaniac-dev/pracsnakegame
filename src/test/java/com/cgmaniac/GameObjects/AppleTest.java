package com.cgmaniac.GameObjects;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.cgmaniac.Cell;
import com.cgmaniac.Position;

public class AppleTest {
    @Test
    public void snakeCollisionTest(){
        Snake snake = new Snake(new Position(100, 100), 20);
        Apple apple =new Apple(20);
        apple.spawnFood(snake);

        assertFalse(snake.getSnake().contains(apple.getBody()));
    }
}
