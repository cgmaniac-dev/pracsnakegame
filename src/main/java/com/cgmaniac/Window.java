package com.cgmaniac;
import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

public class Window extends Canvas {
    public Window(int width, int height, String title, Game game) {
        JFrame frame = new JFrame(title);

        frame.setSize(new Dimension(width,height));

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.add(game);
        game.setSize(new Dimension(width,width));
        frame.setVisible(true);
        game.start();
    }
}
