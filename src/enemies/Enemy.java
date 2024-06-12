package enemies;

import java.awt.Rectangle;
import java.io.Serializable;

import managers.EnemyManager;

import static helpz.Constants.Direction.*;

public abstract class Enemy implements Serializable{
    private static final long serialVersionUID = 1L;
    protected float x, y;
    protected Rectangle bounds;
    protected int health;
    protected int maxHealth;
    protected int ID;
    protected int enemyType;
    protected int lastDir;
    protected boolean alive = true;
    protected int slowEnemyLimit = 120;
    protected int slowEnemy = slowEnemyLimit;

    public Enemy(float x, float y, int ID, int enemyType) {
        this.x = x;
        this.y = y;
        this.ID = ID;
        this.enemyType = enemyType;
        this.bounds = new Rectangle((int) x, (int) y, 32, 32);
        lastDir = -1;
        setStartHealth();
    }

    private void setStartHealth() {
        health = helpz.Constants.Enemies.GetStartHealth(enemyType);
        maxHealth = health;
    }

    public void damage(int dmg, EnemyManager enemyManager) {
        this.health -= dmg;
        if (health <= 0) {
            alive = false;
            enemyManager.rewardPlayer(enemyType);
        }
    }

    public void kill() {
        this.health = 0;
        alive = false;
    }

    public void slow() {
        slowEnemy = 0;
    }

    public void move(float speed, int dir) {
        lastDir = dir;
        
        if(slowEnemy < slowEnemyLimit){
            slowEnemy++;
            speed *= 0.5f;
        }

        switch (dir) {
        case LEFT:
            this.x -= speed;
            break;
        case UP:
            this.y -= speed;
            break;
        case RIGHT:
            this.x += speed;
            break;
        case DOWN:
            this.y += speed;
            break;
        }

        updateHitbox();
    }

    private void updateHitbox() {
        bounds.x = (int) x;
        bounds.y = (int) y;
    }

    public void setPos(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public float getX(){
        return x;
    }

    public float getHealthBarFloat() {
        return health / (float) maxHealth;
    }

    public float getY(){
        return y;
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public int getHealth() {
        return health;
    }

    public int getID() {
        return ID;
    }

    public int getEnemyType() {
        return enemyType;
    }

    public int getLastDir(){
        return lastDir;
    }

    public boolean isAlive() {
        return alive;
    }

    public boolean isSlowed() {
        return (slowEnemy < slowEnemyLimit);
    }
}
