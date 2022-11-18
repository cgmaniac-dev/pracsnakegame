package com.cgmaniac;

import java.awt.Color;
import java.awt.Graphics;
import java.util.LinkedList;

public class Snake {

    private LinkedList<Cell> snake;
    private Position velocity;
    private int size;
    private Direction direction;
    
    public Snake(Position position,int size) {
        this.size = size;
        this.snake = new LinkedList<>();
        this.snake.addLast(new Cell(position, size));
        this.snake.addLast(new Cell(new Position(position.getX()-size, position.getY()),size));
        this.snake.addLast(new Cell(new Position(position.getX()-size*2, position.getY()), size));
        this.velocity = new Position(size, 0);
        this.direction = Direction.RIGHT;
    }

    public LinkedList<Cell> getSnake() {
        return snake;
    }

    public LinkedList<Cell> move(){

        switch (direction) {
            case UP:
            velocity = new Position(0, -size);
            break;
            case DOWN:
            velocity = new Position(0, size);
            break;
            case LEFT:
            velocity = new Position(-size, 0);
            break;
            case RIGHT:
            velocity = new Position(size, 0);
            break;
        }
        
        snake.removeLast();
        var newPosition = new Position(
            getHead().getPosition().getX()+velocity.getX(),
            getHead().getPosition().getY()+velocity.getY());

        var newCell = new Cell(newPosition,size);
        snake.addFirst(newCell);
        handleCrossBorder();
        return this.snake;
    }

    private void handleCrossBorder() {
        int x = getHead().getPosition().getX();
        int y = getHead().getPosition().getY();

        if(x>=Game.WIDTH && direction.equals(Direction.RIGHT)){
            x=0;
            snake.removeFirst();
            var newCell = new Cell(new Position(x, y),size);
            snake.addFirst(newCell);
        }

        if(y>Game.HEIGHT && direction.equals(Direction.DOWN)){
            y=0;
            snake.removeFirst();
            var newCell = new Cell(new Position(x, y),size);
            snake.addFirst(newCell);
        }

        if(x<0 && direction.equals(Direction.RIGHT)){
            x=Game.WIDTH-size;
            snake.removeFirst();
            var newCell = new Cell(new Position(x, y),size);
            snake.addFirst(newCell);
        }

        if(y<0 && direction.equals(Direction.RIGHT)){
            y=Game.HEIGHT-size;
            snake.removeFirst();
            var newCell = new Cell(new Position(x, y),size);
            snake.addFirst(newCell);
        }

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
            g.setColor(Color.BLACK);

            g.drawRect(
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

    public void setDirection(Direction direction) {
        this.direction = direction;
    }
    
}
