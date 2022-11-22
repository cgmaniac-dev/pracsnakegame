package com.cgmaniac.GameObjects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.BasicStroke;
import java.util.LinkedList;

import com.cgmaniac.Cell;
import com.cgmaniac.Direction;
import com.cgmaniac.Game;
import com.cgmaniac.Position;

public class Snake extends GameObject{

    private LinkedList<Cell> snake;
    private Position velocity;
    private int size;
    private Direction direction;
    
    private boolean canMove=false;
    
    
    public Snake(Position position,int size) {
        this.size = size;
        this.snake = new LinkedList<>();
        this.snake.addLast(new Cell(position, size));
        this.snake.addLast(new Cell(new Position(position.getX()-size, position.getY()),size));
        this.snake.addLast(new Cell(new Position(position.getX()-size*2, position.getY()), size));
        this.velocity = new Position(size, 0);
        this.direction = Direction.RIGHT;
    }
    
    public void setCanMove(boolean canMove) {
        this.canMove = canMove;
    }

    public LinkedList<Cell> getSnake() {
        return snake;
    }
    
    public LinkedList<Cell> move(){
        if(canMove){
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
        }

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

        if(y>=Game.HEIGHT && direction.equals(Direction.DOWN)){
            y=0;
            snake.removeFirst();
            var newCell = new Cell(new Position(x, y),size);
            snake.addFirst(newCell);
        }

        if(x<0 && direction.equals(Direction.LEFT)){
            x=Game.WIDTH-size;
            snake.removeFirst();
            var newCell = new Cell(new Position(x, y),size);
            snake.addFirst(newCell);
        }

        if(y<0 && direction.equals(Direction.UP)){
            y=Game.HEIGHT-size;
            snake.removeFirst();
            var newCell = new Cell(new Position(x, y),size);
            snake.addFirst(newCell);
        }

    }

    public void render(Graphics g){
        Graphics2D g2d = (Graphics2D)g;
        for (var snakeP : snake) {
            g2d.setStroke(new BasicStroke(5f));
            if(snakeP.equals(getHead())){
                g2d.setColor(Color.GREEN);

            }else{
                g2d.setColor(Color.YELLOW);            
            }
            g2d.fillRect(
                snakeP.getPosition().getX(), 
                snakeP.getPosition().getY(), 
                snakeP.getSize(), 
                snakeP.getSize()
            );
            g2d.setColor(Color.BLACK);
            
            g2d.drawRect(
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

    public Cell getTail(){
        return snake.getLast();
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }
    
    public Direction getDirection() {
        return direction;
    }

    public Direction getTailDirection() {
        int x1,x2,x3,y1,y2,y3;
        x1 = getTail().getPosition().getX();
        x2 = getSnake().get(getSnake().size()-2).getPosition().getX();
        x3 = getSnake().get(getSnake().size()-3).getPosition().getX(); 
        
        y1 = getTail().getPosition().getY();
        y2 = getSnake().get(getSnake().size()-2).getPosition().getY();
        y3 = getSnake().get(getSnake().size()-3).getPosition().getY(); 
        
        System.out.println("x1="+x1+", y1="+y1);
        System.out.println("x2="+x2+", y2="+y2);
        System.out.println("x3="+x3+", y3="+y3);

        if((y1==y2 && y2==y3)||(y1==y2 && x2==x3)){
            if(x1<x2||(x2<x1&&x2==x3)){
                return Direction.RIGHT;   
            }else{
                return Direction.LEFT;   
            }
        }

        if((x1==x2 && x2==x3)||(x1==x2 && y2==y3)){
            if(y1>y2){
                return Direction.UP;   
            }else{
                return Direction.DOWN;   
            }
        }
        return Direction.UP;
    }

    public  LinkedList<Cell> grow() {
        var x = getTail().getPosition().getX();
        var y = getTail().getPosition().getY();

        Cell newTail = new Cell(new Position(x+20, y), size);
        snake.addLast(newTail);
        return this.snake;
    }
}
