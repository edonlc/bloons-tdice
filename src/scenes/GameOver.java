package scenes;

import java.awt.Graphics;

import java.awt.Font;

import static main.GameStates.MENU;
import static main.GameStates.SetGameState;

import java.awt.Color;
import main.Game;
import ui.MyButton;

public class GameOver extends GameScene implements SceneMethods{

    private MyButton bReplay, bMenu;

    public GameOver(Game game) {
        super(game);
        initButtons();
    }

    private void initButtons() {
        int w = 150;
        int h = w/3;
        int x = 640 / 2 - w/2;
        int y = 300;
        int yOffset = 100;   
        
        bMenu = new MyButton("Menu", x, y, w, h);
    
    }

    @Override
    public void render(Graphics g) {

        // GAME OVER TEXT
        g.setFont(new Font("Comic Sans MS", Font.BOLD, 60));
        g.setColor(Color.getHSBColor(25, 65, 70));
        g.drawString("Game Over!", 160, 150);

        // BOTAO
        g.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
        bMenu.draw(g);


    }


    @Override
    public void mouseClicked(int x, int y) {
        if (bMenu.getBounds().contains(x, y)) 
            SetGameState(MENU);
    }


    @Override
    public void mouseMoved(int x, int y) {
        bMenu.setMouseOver(false);
        bReplay.setMouseOver(false);

        if (bMenu.getBounds().contains(x, y)) 
            bMenu.setMouseOver(true);
        else if (bReplay.getBounds().contains(x, y))
            bReplay.setMouseOver(true);
    }

    @Override
    public void mousePressed(int x, int y) {
        if (bMenu.getBounds().contains(x, y)) 
            bMenu.setMousePressed(true);
        else if (bReplay.getBounds().contains(x, y))
            bReplay.setMousePressed(true);
    }

    @Override
    public void mouseReleased(int x, int y) {
        bMenu.resetBooleans();
        bReplay.resetBooleans();
    }

    @Override
    public void mouseDragged(int x, int y) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mouseDragged'");
    }

}
