package objects;

import java.awt.geom.Point2D;

public class Projectile {
    private Point2D.Float pos;
    private int id, projectileType, dmg;
    private boolean active = true;
    private float xSpeed, ySpeed, rotation;

    public Projectile(float x, float y, int id, int projectileType, int dmg, float rotation, float xSpeed, float ySpeed) {
        pos = new Point2D.Float(x, y);
        this.dmg = dmg;
        this.rotation = rotation;
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
        this.id = id;
        this.projectileType = projectileType;
    }

    public void move() {
        pos.x += xSpeed;
        pos.y += ySpeed;
    } 

    public void setPos(Point2D.Float pos) {
        this.pos = pos;
    }

    public Point2D.Float getPos(){
        return pos;
    }

    public int getId() {
        return id;
    }

    public int getProjectileType() {
        return projectileType;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isActive() {
        return active;
    }

    public int getDmg() {
        return dmg;
    }

    public float getRotation() {
        return rotation;
    }
}
