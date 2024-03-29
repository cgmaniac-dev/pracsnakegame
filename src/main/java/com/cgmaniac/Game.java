package com.cgmaniac;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Font;
import java.awt.image.BufferStrategy;
import java.util.LinkedList;

import com.cgmaniac.GameObjects.Apple;
import com.cgmaniac.GameObjects.Snake;

import java.awt.event.*;

public class Game extends Canvas implements Runnable,KeyListener{
    public static final int WIDTH = 1000,HEIGHT = 740;
    private Thread thread;
    private boolean isRunning;
    private double secondsPerFrame = 1.0/5.0;
    private Snake snake;
    private int key =39;
    LinkedList<Integer> keys = new LinkedList<>();
    private Apple food;
    private int points;
    private boolean isGrow;
     

    public Game() {
        new Window(WIDTH, HEIGHT+37, "Snake game", this);
        addKeyListener(this);
    }
    
    public synchronized void start() {
        thread = new Thread(this);
        thread.start();
        isRunning = true;
        snake = new Snake(new Position(100, 100),20);
        food = new Apple(20);
        food.spawnFood(snake);
        System.out.println(this.getSize());
        points =0;
    }

    public synchronized void stop(){
        try {
            thread.join();
            isRunning=false;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        int frames = 0;
        double frameCounter = 0;

        double lastTime = System.nanoTime()/1000000000.0;
        double unProcessedTime = 0;

        while(isRunning){
            boolean render = false;
            double startTime = System.nanoTime()/1000000000.0;
            double passedTime = startTime - lastTime;

            lastTime = startTime;
            unProcessedTime +=passedTime;
            frameCounter += passedTime;

            while(unProcessedTime>secondsPerFrame){
                render = true;
                unProcessedTime -=secondsPerFrame;

                ticks();
                if(frameCounter>=1.0){
                    System.out.println("FPS:"+frames);
                    frames = 0;
                    frameCounter=0;
                }
            }

            if(render){
                render();
                frames++;
            }else{
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    // TODO: handle exception
                }
            }

        }

    }

    private void render() {
        BufferStrategy bs = this.getBufferStrategy(); 

        if(bs == null){
            this.createBufferStrategy(2);
            return;
        }

        Graphics g = bs.getDrawGraphics();
        Graphics2D g2d = (Graphics2D)g;
        snake.setCanMove(true);
        g2d.setRenderingHint(
         RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2d.setColor(Color.black);
        // g.clearRect(0, 0, WIDTH, HEIGHT);
        g2d.fillRect(0, 0, this.getWidth(), this.getHeight());
        g2d.setColor(Color.BLUE);
        Font font = new Font("Serif", Font.BOLD, 36);
        g2d.setFont(font);
        g2d.drawString(String.valueOf(points), 40, 40);

        // g.fillRect(0, this.getHeight()-40, 40, 40);
        // Things to render start here
        food.render(g2d);
        snake.render(g2d);

        // Things to render ends here
        g2d.dispose();
        bs.show();
    }

    private void ticks() {
        if(isGrow){
            snake.grow();
            isGrow=false;
        }

        if (keys.size()>0)key = keys.removeLast();
        if(key==40&&!snake.getDirection().equals(Direction.UP)){
            snake.setDirection(Direction.DOWN);
        }else if (key==38&& !snake.getDirection().equals(Direction.DOWN)){
            snake.setDirection(Direction.UP);
        }else if (key==39&& !snake.getDirection().equals(Direction.LEFT)){
            snake.setDirection(Direction.RIGHT);
        } else if (key==37&& !snake.getDirection().equals(Direction.RIGHT)){
            snake.setDirection(Direction.LEFT);
        }

        food.ticks();
        snake.ticks();
        if(snake.getHead().equals(food.getBody())){
            points +=food.getPoints();
            food.spawnFood(snake);
            isGrow=true;
        }
    }

    public static void main(String[] args) {
        new Game();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // TODO Auto-generated method stub

        int localkey = e.getKeyCode();
        keys.addFirst(localkey);
        // System.out.println(localkey);
    }

    @Override
    public void keyReleased(KeyEvent arg0) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void keyTyped(KeyEvent arg0) {
        // TODO Auto-generated method stub
        
    }

    
}
