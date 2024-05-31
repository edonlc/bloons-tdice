package scenes;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import helpz.LoadSave;
import main.Game;
import managers.EnemyManager;
import objects.PathPoint;
import ui.GameBar;


public class Playing extends GameScene implements SceneMethods{

    private int[][] lvl;
    private GameBar bottomBar;
    private int mouseX, mouseY;
    private EnemyManager enemyManager;
    private PathPoint start, end;

    public Playing(Game game) {
        super(game);

        loadDefaultLevel();
        bottomBar = new GameBar(0, 640, 640, 160, this);

        enemyManager = new EnemyManager(this, start, end);

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
    }

    public void render(Graphics g){
        drawLevel(g);

        bottomBar.draw(g);
        enemyManager.draw(g);
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

}
