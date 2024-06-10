package events;

import java.util.ArrayList;

import enemies.Enemy;

public class Wave {

    private ArrayList<Enemy> enemies;

    public Wave(ArrayList<Enemy> enemies) {
        this.enemies = enemies;
    }

    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }
}
