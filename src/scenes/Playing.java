package scenes;

import static helpz.Constants.Tiles.GRASS_TILE;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import enemies.Enemy;
import helpz.LoadSave;
import main.Game;
import managers.EnemyManager;
import managers.TowerManager;
import managers.WaveManager;
import managers.ProjectileManager;
import objects.PathPoint;
import objects.Tower;
import ui.GameBar;
import static helpz.SaveWave.createWaveFile;


public class Playing extends GameScene implements SceneMethods{

    private int[][] lvl;
    private GameBar bottomBar;
    private int mouseX, mouseY;
    private EnemyManager enemyManager;
    private TowerManager towerManager;
    private WaveManager waveManager;
    private ProjectileManager projectileManager;
    private PathPoint start, end;
    private Tower selectedTower;
    private int goldTick = 0;
    private boolean gamePaused = false;

    public Playing(Game game) {
        super(game);

        loadDefaultLevel();
        bottomBar = new GameBar(0, 640, 640, 160, this);

        enemyManager = new EnemyManager(this, start, end);
        towerManager = new TowerManager(this);
        waveManager = new WaveManager(this);
        projectileManager = new ProjectileManager(this);
        //createWaveFile("7.wave");
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
        
        if(!gamePaused) {
        
        waveManager.update();
        
        goldTick++;
        if(goldTick % (60 * 3) == 0)
            bottomBar.addBanana(1);

        if(isAllEnemiesDead()) {
            if(isThereMoreWaves()) {
                waveManager.startWaveTimer();
                if(isWaveTimerOver()){
                    waveManager.increaseWaveIndex();
                    enemyManager.getEnemies().clear();
                    waveManager.resetEnemyIndex();
                }
            }
        }

        if(isTimeForNewEnemy()) {
            spawnEnemy();
        }

        enemyManager.update();
        towerManager.update();
        projectileManager.update();
        }
        
        
    }


    private boolean isWaveTimerOver() {

        return waveManager.isWaveTimerOver();
    }


    private boolean isThereMoreWaves() {
        return waveManager.isThereMoreWaves();
    }


    private boolean isAllEnemiesDead() {

        if(waveManager.isThereMoreEnemies()) {
            return false;
        }
        for(Enemy e : enemyManager.getEnemies())
        if(e.isAlive())
            return false;
        return true;
    }


    private void spawnEnemy() {
        enemyManager.spawnEnemy(waveManager.getNextEnemy());
    }

    private boolean isTimeForNewEnemy() {
        if(waveManager.isTimeForNewEnemy()) {
            if(waveManager.isThereMoreEnemies())
                return true;
        }
        return false;
    }

    public void setSelectedTower(Tower selectedTower) {
        this.selectedTower = selectedTower;
    }

    public void render(Graphics g){
        drawLevel(g);

        bottomBar.draw(g);
        enemyManager.draw(g);
        towerManager.draw(g);
        projectileManager.draw(g);
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

                        removeBanana(selectedTower.getTowerType());

                        selectedTower = null;

                    }
                }

            } else {
                Tower t = getTowerAt(mouseX, mouseY);
                bottomBar.displayTower(t);
            }
        }
    }
    

    private void removeBanana(int towerType) {
        bottomBar.payForTower(towerType);
    }

    public void upgradeTower(Tower displayedTower) {
		towerManager.upgradeTower(displayedTower);

	}

	public void removeTower(Tower displayedTower) {
		towerManager.removeTower(displayedTower);
	}

    private Tower getTowerAt(int x, int y) {
        return towerManager.getTowerAt(x, y);
    }


    private boolean isTileGrass(int x, int y) {
        int id = lvl[y / 32][x / 32];
        int tileType = game.getTileManager().getTile(id).getTileType();
        return tileType == GRASS_TILE;
    }

    public void shootEnemy(Tower t, Enemy e) {
        projectileManager.newProjectile(t, e);
    }


    public void setGamePaused(boolean gamePaused) {
        this.gamePaused = gamePaused;
    }

    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            selectedTower = null;
        }
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

    public void rewardPlayer(int enemyType) {
        bottomBar.addBanana(helpz.Constants.Enemies.GetReward(enemyType));
    }

    public TowerManager getTowerManager() {
        return towerManager;
    }

    public EnemyManager getEnemyManager() {
        return enemyManager;
    }

    public WaveManager getWaveManager() {
        return waveManager;
    }

    public boolean isGamePaused() {
        return gamePaused;
    }


    public void removeOneLife() {
        bottomBar.removeOneLife();
    }


}
