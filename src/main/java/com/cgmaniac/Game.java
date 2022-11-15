package com.cgmaniac;
import java.awt.Canvas;

public class Game extends Canvas implements Runnable{
    public static final int WIDTH = 1000,HEIGHT = WIDTH/12*9;
    private Thread thread;
    private boolean isRunning;
    private double secondsPerFrame = 1.0/60.0;

    public Game() {
        new Window(WIDTH, HEIGHT, "Snake game", this);
    }

    public synchronized void start() {
        thread = new Thread(this);
        thread.start();
        isRunning = true;
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
            double startTime = 0;
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

    }

    private void ticks() {

    }

    public static void main(String[] args) {
        new Game();
    }
}
