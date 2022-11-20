package com.cgmaniac.GameObjects;

public abstract class AbstractFood extends GameObject{
    private int points;
    private int lifeTime;
    
    public int getPoints() {
        return points;
    }
    public void setPoints(int points) {
        this.points = points;
    }
    public int getLifeTime() {
        return lifeTime;
    }
    public void setLifeTime(int lifeTime) {
        this.lifeTime = lifeTime;
    }
}
