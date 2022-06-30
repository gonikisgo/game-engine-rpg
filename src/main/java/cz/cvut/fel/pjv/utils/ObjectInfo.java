package cz.cvut.fel.pjv.utils;

/*
class for storing game object's info in .json file
 */

public class ObjectInfo {
    int x, y, health;
    String options;

    public ObjectInfo(int x, int y, int health, String options) {
        this.x = x;
        this.y = y;
        this.health = health;
        this.options = options;
    }

    public ObjectInfo(int x, int y, int health) {
        this.x = x;
        this.y = y;
        this.health = health;
    }

    public ObjectInfo(int x, int y) {
        this.x = x;
        this.y = y;
        health = -1;
    }

    public ObjectInfo(int x) {
        this.x = x;
        y = -1;
        health = -1;
    }

    public ObjectInfo(String options) {
        this.options = options;
        x = -1;
        y = -1;
        health = -1;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getHealth() {
        return health;
    }

    public String getOptions() {
        return options;
    }
}
