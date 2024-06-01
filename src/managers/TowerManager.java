package managers;
import scenes.Playing;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.tools.ToolProvider;

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
        towerImgs = new BufferedImage[3];
        towerImgs[0] = atlas.getSubimage(3 * 32, 0, 32, 32);
        towerImgs[1] = atlas.getSubimage(3 * 32, 2 * 32, 32, 32);
        towerImgs[2] = atlas.getSubimage(7 * 32, 2 * 32, 32, 32);
    }

    public void addTower(Tower selectedTower, int xPos, int yPos) {
        towers.add(new Tower(xPos, yPos, towerAmount++, selectedTower.getTowerType()));
    }

    public void update() {
        
    }

    public void draw(Graphics g) {
        for(Tower t : towers)
            g.drawImage(towerImgs[t.getTowerType()], t.getX(), t.getY(), null);
        //g.drawImage(towerImgs[DART_MONKEY], tower.getX(), tower.getY(), null);
    }

    public BufferedImage[] getTowerImgs() {
        return towerImgs;
    }



}
