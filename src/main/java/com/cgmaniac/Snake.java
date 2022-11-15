package com.cgmaniac;

import java.awt.Color;
import java.awt.Graphics;
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
        this.snake.addLast(new Cell(new Position(position.getX()-size, position.getY()),size));
        this.snake.addLast(new Cell(new Position(position.getX()-size*2, position.getY()), size));
    }

    public LinkedList<Cell> getSnake() {
        return snake;
    }

    public void render(Graphics g){
        g.setColor(Color.GREEN);
        for (var snakeP : snake) {
            g.fillRect(snakeP.getPosition().getX(), snakeP.getPosition().getY(), snakeP.getSize(), snakeP.getSize());
        }
    }

    public void ticks(){

    }
    
}
