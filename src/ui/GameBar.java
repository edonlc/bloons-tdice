package ui;

import static main.GameStates.GAMEOVER;
import static main.GameStates.MENU;
import static main.GameStates.SetGameState;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

import helpz.LoadSave;
import helpz.Constants.Towers;
import objects.Tower;
import scenes.Playing;

public class GameBar extends Bar{
    private Playing playing;
    private MyButton bMenu, bPause, sellTower, upgradeTower;
    private MyButton[] towerButtons;
    private Tower selectedTower, displayedTower;
    private DecimalFormat formatter;
    private int banana = 1000;
    private BufferedImage bananaImg, heartImg;
    private boolean showTowerPrice;
    private int towerPriceType;
    private int lives = 10;

    public GameBar(int x, int y, int width, int height, Playing playing) {
        super(x, y, width, height);
        this.playing = playing;
        formatter = new DecimalFormat("0.0");
        
        initButtons();
        loadBananaImg();
        loadHeartImg();
    }

    public void resetEverything() {
		lives = 10;
		towerPriceType = 0;
		showTowerPrice = false;
		banana = 100;
		selectedTower = null;
		displayedTower = null;
	}
    
    private void initButtons(){
        bMenu = new MyButton("Menu", 2, 645, 100, 30);
        bPause = new MyButton("Pause", 2, 682, 100, 30);

        towerButtons = new MyButton[3];
        int w = 50;
        int h = 50;
        int xStart = 110;
        int yStart = 650;
        int xOffSet = (int) (w * 1.1f);

        for(int i = 0; i < towerButtons.length; i++){
            towerButtons[i] = new MyButton("", xStart + xOffSet * i, yStart, w, h, i);

        sellTower = new MyButton("Sell", 420, 703, 80, 25);
        upgradeTower = new MyButton("Upgrade", 540, 703, 80, 25);
        }
    }

    // Carrega imagens
    private void loadBananaImg(){
        bananaImg = LoadSave.getSpriteAtlas().getSubimage(32*8, 32*2, 32, 32);
    }


    private void loadHeartImg(){
        heartImg = LoadSave.getSpriteAtlas().getSubimage(32*9, 32*1, 32, 32);
    }

    //Metódos de desenhar
    public void draw(Graphics g) {

        //background
        g.setColor(new Color(102, 111, 63));
        g.fillRect(x, y, width, height);

        //botões
        drawButtons(g);

        // display das torres
        drawDisplayedTower(g);

        // info das waves
        drawWaveInfo(g);

        // dinheiro
        drawBananaAmount(g);

        // preço das torres
        if (showTowerPrice)
            drawTowerPrice(g);

        // PAUSE
        if (playing.isGamePaused()) {
            g.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
            g.setColor(Color.getHSBColor(25, 65, 70));
            g.drawString("Game is Paused!", 117, 790);
        }

        // VIDAS
        drawLivesAmount(g);

    }

    private void drawButtons(Graphics g) {
        bMenu.draw(g);
        bPause.draw(g);

        for(MyButton b : towerButtons){

            g.setColor(Color.getHSBColor(40, 60, 82));
            g.fillRect(b.x, b.y, b.width, b.height);
            g.drawImage(playing.getTowerManager().getTowerImgs()[b.getId()], b.x, b.y, b.width, b.height, null);
            drawButtonFeedback(g, b);

        }
    }

    private void drawBananaAmount(Graphics g) {
        g.setColor(Color.getHSBColor(25, 62, 70));
        if (bananaImg != null) {
            g.drawImage(bananaImg, 110, 694, 43, 43, null);
        }
        g.drawString("$" +banana, 160, 725);
    }

    private void drawTowerPrice(Graphics g) {
        g.setColor(Color.getHSBColor(37, 62, 79));
        g.fillRect(280, 650, 120, 50);
        g.setColor(Color.black);
        g.drawRect(280, 650, 120, 50);


        g.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
        g.drawString("" + getTowerPriceName(), 285, 665);
        g.drawString("Bananas: $" + getTowerPricePrice(), 285, 685);

        // mensagem - sem dinheiro suficiente
        if(isTowerCostHigherBanana()) {
            g.setColor(Color.getHSBColor(25, 65, 70));
            g.drawString("NO BANANAS!", 285, 725);
        }

    }

    private void drawLivesAmount(Graphics g) {
        g.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
        g.setColor(Color.getHSBColor(25, 62, 70));
        if (heartImg != null) {
            g.drawImage(heartImg, 112, 723, 43, 43, null);
        }
        g.drawString("" +lives +" hearts", 160, 750);
    }
    private void drawWaveInfo(Graphics g) {
        g.setColor(Color.getHSBColor(37, 62, 79));
        g.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
        drawWaveTimerInfo(g);
        drawEnemiesLeftInfo(g);
        drawWavesLeftInfo(g);
    }

    private void drawEnemiesLeftInfo(Graphics g) {
        int remaining = playing.getEnemyManager().getAmountOfAliveEnemies();
        g.drawString("Enemies Left: " + remaining, 450, 790);
    }

    private void drawWavesLeftInfo(Graphics g) {
        int current = playing.getWaveManager().getWaveIndex();
        int size = playing.getWaveManager().getWaves().size();
        g.drawString("Wave " + (current + 1) + "/" + size, 470, 770);
    }

    private void drawWaveTimerInfo(Graphics g) {
        if(playing.getWaveManager().isWaveTimerStarted()){ 
            float timeLeft = playing.getWaveManager().getTimeLeft();
            String formatedText = formatter.format(timeLeft);

            g.drawString("Next Wave in: " + formatedText + "s", 420, 750);
        }
    }

    private void drawDisplayedTower(Graphics g) {
        if(displayedTower != null) {
            g.setColor(Color.getHSBColor(37, 62, 79));
            g.fillRect(410, 645, 220, 85);
            g.setColor(Color.black);
            g.drawRect(410, 645, 220, 85);
            g.drawRect(420, 650, 50, 50);
            g.drawImage(playing.getTowerManager().getTowerImgs()[displayedTower.getTowerType()], 420, 650, 50, 50, null);
            g.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
            g.drawString("" + Towers.GetName(displayedTower.getTowerType()), 480, 660);
            g.drawString("Tier - " + displayedTower.getTier(), 480, 675);
            drawDisplayedTowerRange(g);

            sellTower.draw(g);
            if (displayedTower.getTier() < 3 && banana >= getUpgradeAmount(displayedTower)) {
				upgradeTower.draw(g);
			}

            if (sellTower.isMouseOver()) {
				g.setColor(Color.red);
				g.drawString("Sell for: " + getSellAmount(displayedTower) + "g", 480, 695);
			} else if (upgradeTower.isMouseOver() && banana >= getUpgradeAmount(displayedTower)) {
				g.setColor(Color.blue);
				g.drawString("Upgrade for: " + getUpgradeAmount(displayedTower) + "g", 480, 695);
			}
        }
    }

    private void drawDisplayedTowerRange(Graphics g) {
        g.setColor(Color.white);
        g.drawOval(displayedTower.getX() + 16 - (int) (displayedTower.getRange()*2)/2, displayedTower.getY() + 16 - (int) (displayedTower.getRange()*2)/2, (int) displayedTower.getRange()*2, (int) displayedTower.getRange()*2);
    }

    public void displayTower(Tower t) {
        displayedTower = t;
    }

	private void sellTowerClicked() {
		playing.removeTower(displayedTower);
		banana += helpz.Constants.Towers.GetTowerPrice(displayedTower.getTowerType()) / 2;

		int upgradeCost = (displayedTower.getTier() - 1) * getUpgradeAmount(displayedTower);
		upgradeCost *= 0.5f;
		banana += upgradeCost;

		displayedTower = null;

	}

	private void upgradeTowerClicked() {
		playing.upgradeTower(displayedTower);
		banana -= getUpgradeAmount(displayedTower);

	}

    public void removeOneLife() {
        lives --;
        if (lives <= 0)
            SetGameState(GAMEOVER);
    }

    
    private void togglePause() {
        playing.setGamePaused(!playing.isGamePaused());

        if (playing.isGamePaused())
            bPause.setText("Unpause");
        else
            bPause.setText("Pause");
    }

    private boolean isMoneyEnough(int towerType) {
        return banana >= helpz.Constants.Towers.GetTowerPrice(towerType);
    }

	public void mouseClicked(int x, int y) {
		if (bMenu.getBounds().contains(x, y))
			SetGameState(MENU);
        else if (bPause.getBounds().contains(x, y))
            togglePause();
        else {
            if (displayedTower != null) {
				if (sellTower.getBounds().contains(x, y)) {
					sellTowerClicked();

					return;
				} else if (upgradeTower.getBounds().contains(x, y) && displayedTower.getTier() < 3 && banana >= getUpgradeAmount(displayedTower)) {
					upgradeTowerClicked();
					return;
				}
            }
            for (MyButton b : towerButtons) {
                if (b.getBounds().contains(x, y)) {
                    if(!isMoneyEnough(b.getId()))
                        return;
                        
                    selectedTower = new Tower(0, 0, -1, b.getId());
                    playing.setSelectedTower(selectedTower);
                    return;
                }
            }
        }
    }

    public void mouseMoved(int x, int y) {
        bMenu.setMouseOver(false);
        bPause.setMouseOver(false);
        upgradeTower.setMouseOver(false);
        sellTower.setMouseOver(false);
        showTowerPrice = false;
        for(MyButton b : towerButtons)
            b.setMouseOver(false);

        if (bMenu.getBounds().contains(x, y))
            bMenu.setMouseOver(true);
        else if (bPause.getBounds().contains(x, y))
            bPause.setMouseOver(true);
        else {
            if (displayedTower != null) {
                if (sellTower.getBounds().contains(x, y)) {
                    sellTower.setMouseOver(true);
                    return;
                } else if (upgradeTower.getBounds().contains(x, y) && displayedTower.getTier() < 3) {
                    upgradeTower.setMouseOver(true);
                    return;
                }
            }

            for(MyButton b : towerButtons)
                if (b.getBounds().contains(x, y)) { 
                b.setMouseOver(true);
                showTowerPrice = true;
                towerPriceType = b.getId();
                return;
                }
        }
    }

    public void mousePressed(int x, int y) {
        if (bMenu.getBounds().contains(x, y))
            bMenu.setMousePressed(true);
        else if (bPause.getBounds().contains(x, y))
            bPause.setMousePressed(true);
        else {
            if (displayedTower != null) {
				if (sellTower.getBounds().contains(x, y)) {
					sellTower.setMousePressed(true);
					return;
				} else if (upgradeTower.getBounds().contains(x, y) && displayedTower.getTier() < 3) {
					upgradeTower.setMousePressed(true);
					return;
				}
			}
            for (MyButton b : towerButtons)
                if (b.getBounds().contains(x, y)){
                    b.setMousePressed(true);
                    return;
            }
        }
            
    }

    public void mouseReleased(int x, int y) {
        bMenu.resetBooleans();
        bPause.resetBooleans();
        upgradeTower.resetBooleans();
        sellTower.resetBooleans();
        for (MyButton b : towerButtons)
            b.resetBooleans();
    }

    public void payForTower(int towerType) {
        this.banana -= helpz.Constants.Towers.GetTowerPrice(towerType);
    }

    public void addBanana(int getReward) {
        this.banana += getReward;
    }

    public int getLives() {
        return lives;
    }
    
    private boolean isTowerCostHigherBanana() {
        return getTowerPricePrice() > banana;
    }

    private String getTowerPriceName() {
        return helpz.Constants.Towers.GetName(towerPriceType);
    }

    private int getTowerPricePrice() {
        return helpz.Constants.Towers.GetTowerPrice(towerPriceType);
    }


    private int getUpgradeAmount(Tower displayedTower) {
		return (int) (helpz.Constants.Towers.GetTowerPrice(displayedTower.getTowerType()) * 1.5f);
	}

	private int getSellAmount(Tower displayedTower) {
		int upgradeCost = (displayedTower.getTier() - 1) * getUpgradeAmount(displayedTower);
		upgradeCost *= 0.5f;

		return helpz.Constants.Towers.GetTowerPrice(displayedTower.getTowerType()) / 2 + upgradeCost;
	}

}
