package com.cgmaniac;

import java.util.LinkedList;

public class Snake {

    private LinkedList<Cell> snake;
    private Position position;
    private int size;

    public Snake(Position position,int size) {
        this.position = position;
        this.size = size;
        this.snake = new LinkedList<>();
        this.snake.addLast(new Cell(position, size));
        this.snake.addLast(new Cell(new Position(-20, 0),size));
        this.snake.addLast(new Cell(new Position(-40, 0), size));
    }

    public LinkedList<Cell> getSnake() {
        return snake;
    }
    
}
