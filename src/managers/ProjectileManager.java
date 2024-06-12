package managers;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import enemies.Enemy;
import helpz.LoadSave;
import objects.Projectile;
import objects.Tower;
import static helpz.Constants.Projectiles.*;
import static helpz.Constants.Towers.*;
import scenes.Playing;

public class ProjectileManager {
    private Playing playing;
    private ArrayList<Projectile> projectiles = new ArrayList<>();
    private BufferedImage[] projectileImgs;
    private int projectileId = 0;

    public ProjectileManager(Playing playing) {
        this.playing = playing;
        importImgs();
    }

    private void importImgs() {
        BufferedImage atlas = LoadSave.getSpriteAtlas();
        projectileImgs = new BufferedImage[6];

        projectileImgs[0] = atlas.getSubimage(3 * 32, 32, 32, 32);
        projectileImgs[1] = atlas.getSubimage(4 * 32, 32, 32, 32);
        projectileImgs[2] = atlas.getSubimage(5 * 32, 32, 32, 32);
        projectileImgs[3] = atlas.getSubimage(6*32, 0, 32, 32);
        projectileImgs[4] = atlas.getSubimage(6*32, 32, 32, 32);
        projectileImgs[5] = atlas.getSubimage(6*32, 2*32, 32, 32);
    }

    public void newProjectile(Tower t, Enemy e) {
        int type = getProjectileType(t);

        int xDist = (int) (t.getX() - e.getX());
        int yDist = (int) (t.getY() - e.getY());
        int totalDist = Math.abs(xDist) + Math.abs(yDist);

        float xPer = (float) Math.abs(xDist) / totalDist;

        float xSpeed = xPer * helpz.Constants.Projectiles.getSpeed(type);
        float ySpeed = helpz.Constants.Projectiles.getSpeed(type) - xSpeed;

        if(t.getX() > e.getX())
            xSpeed *= -1;
        if(t.getY() > e.getY())
            ySpeed *= -1;

        float arcValue = (float) Math.atan(yDist / (float) xDist);
        float rotate = (float) Math.toDegrees(arcValue);

        if (xDist < 0)
            rotate+= 180;

        projectiles.add(new Projectile(t.getX() + 16, t.getY() + 16, projectileId++, type, t.getDmg(), rotate, xSpeed, ySpeed));
    }

    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        for (Projectile p : projectiles)
            if (p.isActive()) {
                g2d.translate(p.getPos().x, p.getPos().y);
                g2d.rotate(Math.toRadians(90));
                g2d.drawImage(projectileImgs[p.getProjectileType()], -16, -16, null);
                g2d.rotate(Math.toRadians(90));
                g2d.translate(-p.getPos().x, -p.getPos().y);
            }

    }

    public void update(){
        for (Projectile p : projectiles)
            if (p.isActive()) {
                p.move();
                if(isProjectileHittingEnemy(p)) {
                    p.setActive(false);
                } else {

                }
            }
    }

    private boolean isProjectileHittingEnemy(Projectile p) {
        for (Enemy e : playing.getEnemyManager().getEnemies()) {
            if (e.isAlive())
                if(e.getBounds().contains(p.getPos())) {
                    e.damage(p.getDmg(), playing.getEnemyManager()); 
                    if(p.getProjectileType() == ICE || p.getProjectileType() == ICE_T2 || p.getProjectileType() == ICE_T3)
                        e.slow();
                    return true;
            }
        }
        return false;
    }

    private int getProjectileType(Tower t){
        switch (t.getTowerType()) {
        case DART_MONKEY:
            return DART;
        case DART_MONKEY_LV2:
            return DART_T2;
        case DART_MONKEY_LV3:
            return DART_T3;
        case ICE_MONKEY:
            return ICE;
        case ICE_MONKEY_LV2:
            return ICE_T2;
        case ICE_MONKEY_LV3:
            return ICE_T3;
        case TACK_SHOOTER:
            return DART_T3;
        }
        return 0;
    }

}
