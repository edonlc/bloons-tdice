package managers;
import scenes.Playing;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.tools.ToolProvider;

import enemies.Enemy;
import helpz.LoadSave;
import objects.Tower;

import static helpz.Constants.Towers.*;

public class TowerManager {

    private Playing playing;
    private BufferedImage[] towerImgs;
    private ArrayList<Tower> towers = new ArrayList<>();
    private int towerAmount = 0;

    public TowerManager (Playing playing) {
        this.playing = playing;

        loadTowerImgs();
    }


    private void loadTowerImgs() {
        BufferedImage atlas = LoadSave.getSpriteAtlas();
        towerImgs = new BufferedImage[7];
        towerImgs[0] = atlas.getSubimage(3 * 32, 0, 32, 32);
        towerImgs[1] = atlas.getSubimage(4 * 32, 0, 32, 32);
        towerImgs[2] = atlas.getSubimage(5 * 32, 0, 32, 32);
        towerImgs[3] = atlas.getSubimage(3 * 32, 2 * 32, 32, 32);
        towerImgs[4] = atlas.getSubimage(4 * 32, 2 * 32, 32, 32);
        towerImgs[5] = atlas.getSubimage(5 * 32, 2 * 32, 32, 32);
        towerImgs[6] = atlas.getSubimage(3 * 32, 0, 32, 32);
    }

    public void addTower(Tower selectedTower, int xPos, int yPos) {
        towers.add(new Tower(xPos, yPos, towerAmount++, selectedTower.getTowerType()));
    }

    public void update() {
        for (Tower t : towers) {
            t.update();
            attackIfClose(t);
        }
    }

    private void attackIfClose(Tower t) {
        for (Enemy e : playing.getEnemyManager().getEnemies()) {
            if (e.isAlive())
                if (isEnemyInRange(t,e)) {
                    if (t.isCooldownOver()) {
                        playing.shootEnemy(t, e);
                        t.resetCooldown();
                    }
                } else {
                    
                }
        }
    
    }

    private boolean isEnemyInRange(Tower t, Enemy e) {
        int range = helpz.Utilz.getPitagoras(t.getX(), t.getY(), e.getX(), e.getY());

        return range < t.getRange();
    }

    public void draw(Graphics g) {
        for(Tower t : towers)
            g.drawImage(towerImgs[t.getTowerType()], t.getX(), t.getY(), null);
        //g.drawImage(towerImgs[DART_MONKEY], tower.getX(), tower.getY(), null);
    }

    public Tower getTowerAt(int x, int y) {
        for (Tower t : towers)
            if(t.getX() == x)
                if(t.getY() == y)
                    return t;
        return null;
    }

    public BufferedImage[] getTowerImgs() {
        return towerImgs;
    }



}
