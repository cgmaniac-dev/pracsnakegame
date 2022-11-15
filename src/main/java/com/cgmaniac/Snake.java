package com.cgmaniac;

import java.awt.Color;
import java.awt.Graphics;
import java.util.LinkedList;

public class Snake {

    private LinkedList<Cell> snake;
    private Position position;
    private Position velocity;
    private int size;

    public Snake(Position position,int size) {
        this.position = position;
        this.size = size;
        this.snake = new LinkedList<>();
        this.snake.addLast(new Cell(position, size));
        this.snake.addLast(new Cell(new Position(position.getX()-size, position.getY()),size));
        this.snake.addLast(new Cell(new Position(position.getX()-size*2, position.getY()), size));
        this.velocity = new Position(size, 0);
    }

    public LinkedList<Cell> getSnake() {
        return snake;
    }

    public LinkedList<Cell> move(){
        snake.removeLast();
        var newPosition = new Position(
            getHead().getPosition().getX()+velocity.getX(),
            getHead().getPosition().getY()+velocity.getY());

        var newCell = new Cell(newPosition,size);
        snake.addFirst(newCell);
        return this.snake;
    }

    public void render(Graphics g){
        for (var snakeP : snake) {
            if(snakeP.equals(getHead())){
                g.setColor(Color.GREEN);

            }else{
                g.setColor(Color.YELLOW);            
            }
            g.fillRect(
                snakeP.getPosition().getX(), 
                snakeP.getPosition().getY(), 
                snakeP.getSize(), 
                snakeP.getSize()
            );
        }
    }

    public void ticks(){
        move();
    }

    public Cell getHead(){
        return snake.getFirst();        
    }
    
}
