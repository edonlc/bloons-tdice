package scenes;

import static helpz.Constants.Tiles.GRASS_TILE;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import helpz.LoadSave;
import main.Game;
import managers.EnemyManager;
import managers.TowerManager;
import objects.PathPoint;
import objects.Tower;
import ui.GameBar;
import static helpz.Constants.Tiles.GRASS_TILE;


public class Playing extends GameScene implements SceneMethods{

    private int[][] lvl;
    private GameBar bottomBar;
    private int mouseX, mouseY;
    private EnemyManager enemyManager;
    private TowerManager towerManager;
    private PathPoint start, end;
    private Tower selectedTower;

    public Playing(Game game) {
        super(game);

        loadDefaultLevel();
        bottomBar = new GameBar(0, 640, 640, 160, this);

        enemyManager = new EnemyManager(this, start, end);
        towerManager = new TowerManager(this);

    }


    private void loadDefaultLevel() {
        lvl = LoadSave.getLevelData("new_level");
        ArrayList<PathPoint> points = LoadSave.getLevelPathPoints("new_level");
        start = points.get(0);
        end = points.get(1);
    }

    public void setLevel(int[][] lvl) {
        this.lvl = lvl;
    }

    public void update(){
        enemyManager.update();
        towerManager.update();
    }

    public void setSelectedTower(Tower selectedTower) {
        this.selectedTower = selectedTower;
    }

    public void render(Graphics g){
        drawLevel(g);

        bottomBar.draw(g);
        enemyManager.draw(g);
        towerManager.draw(g);
        drawSelectedTower(g);
    }    

    private void drawSelectedTower(Graphics g) {
        if (selectedTower != null)
            g.drawImage(towerManager.getTowerImgs()[selectedTower.getTowerType()], mouseX, mouseY, null);
    }


    private void drawLevel(Graphics g) {
        for (int y = 0; y < lvl.length; y++) {
            for (int x = 0; x < lvl[y].length; x++) {
                int id = lvl[y][x];
                g.drawImage(getSprite(id), x * 32, y * 32, null);
            }
        }
    }

    public int getTileType(int x, int y) {

        int xCord = x / 32;
        int yCord = y /32;

        if (xCord < 0 || xCord > 19)
            return 0;
        if (yCord < 0 || yCord > 19)
            return 0;

        int id = lvl[y/32][x/32];
        return game.getTileManager().getTile(id).getTileType();
    }

    private BufferedImage getSprite(int spriteId) {
        return getGame().getTileManager().getSprite(spriteId);
    }

    @Override
    public void mouseClicked(int x, int y) {
        if(y >= 640) 
            bottomBar.mouseClicked(x, y);
        else {
            if (selectedTower != null) {
                if(isTileGrass(mouseX, mouseY)) {
                    if(getTowerAt(mouseX, mouseY) == null) {
                        towerManager.addTower(selectedTower, mouseX, mouseY);
                        selectedTower = null;
                    }
                }

            } else {
                Tower t = getTowerAt(mouseX, mouseY);
                bottomBar.displayTower(t);
            }
        }
    }
    

    private Tower getTowerAt(int x, int y) {
        return towerManager.getTowerAt(x, y);
    }


    private boolean isTileGrass(int x, int y) {
        int id = lvl[y / 32][x / 32];
        int tileType = game.getTileManager().getTile(id).getTileType();
        return tileType == GRASS_TILE;
    }


    @Override
    public void mouseMoved(int x, int y) {
        if(y >= 640) {
            bottomBar.mouseMoved(x, y);
        } else {
            mouseX = (x / 32) * 32;
            mouseY = (y / 32) * 32;
        }
        
    }

    @Override
    public void mousePressed(int x, int y) {
        if(y >= 640) {
            bottomBar.mousePressed(x, y);
        }
    }

    @Override
    public void mouseReleased(int x, int y) {
        bottomBar.mouseReleased(x, y);
        
    }


    @Override
    public void mouseDragged(int x, int y) {
    }

    public TowerManager getTowerManager() {
        return towerManager;
    }




}
