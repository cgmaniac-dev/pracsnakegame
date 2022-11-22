package com.cgmaniac.GameObjects;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import com.cgmaniac.Cell;
import com.cgmaniac.Game;
import com.cgmaniac.Position;

public class Apple extends AbstractFood{

    private int size;
    private Cell body;
    Random r = new Random();

    public Apple(int size) {
        this.size = size;
        this.setPoints(5);
    }
    
    @Override
    public void render(Graphics g) {
        g.setColor(new Color(255,30,3));
        g.fillOval(body.getPosition().getX()+2, body.getPosition().getY()+2, size-4, size-4);
    }
    
    @Override
    public void ticks() {
        
    }
    
    public void spawnFood(Snake snake) {

        int y = r.nextInt(Game.HEIGHT-size+1);
        int x = r.nextInt(Game.WIDTH-size+1);
        body =new Cell(new Position(x, y), size);

        while(snake.getSnake().contains(body)||(x%20!=0||y%20!=0)){
            y = r.nextInt(Game.HEIGHT-size+1);
            x = r.nextInt(Game.WIDTH-size+1);
            body =new Cell(new Position(x, y), size);
        }
        System.out.println();
    }

    public Cell getBody() {
        return this.body;
    }
    
}
