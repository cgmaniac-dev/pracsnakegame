package com.cgmaniac.GameObjects;
import java.awt.Graphics;
import com.cgmaniac.Position;

public abstract class GameObject {
    private Position position;

    public Position getPosition() {
        return position;
    }
    public void setPosition(Position position) {
        this.position = position;
    }
    
    public abstract void render(Graphics g); 
    public abstract void ticks();
}
