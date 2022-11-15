package com.cgmaniac;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

public class Game extends Canvas implements Runnable{
    public static final int WIDTH = 1000,HEIGHT = WIDTH/12*9;
    private Thread thread;
    private boolean isRunning;
    private double secondsPerFrame = 1.0/3.0;
    private Snake snake;

    public Game() {
        new Window(WIDTH, HEIGHT, "Snake game", this);
    }

    public synchronized void start() {
        thread = new Thread(this);
        thread.start();
        isRunning = true;
        snake = new Snake(new Position(100, 100),20);
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

        g.setColor(Color.black);
        g.fillRect(0, 0, WIDTH, HEIGHT);
        // Things to render start here
        snake.render(g);

        // Things to render ends here
        g.dispose();
        bs.show();
    }

    private void ticks() {
        snake.ticks();
    }

    public static void main(String[] args) {
        new Game();
    }
}
