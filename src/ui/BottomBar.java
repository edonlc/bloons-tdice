package ui;

import java.awt.Graphics;
import java.awt.Color;
import static main.GameStates.MENU;
import static main.GameStates.setGameState;



public class BottomBar {

    private int x, y, width, height;

    private MyButton bMenu;

    public BottomBar(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        initButtons();
    }
    
    private void initButtons(){
        bMenu = new MyButton("Menu", 2, 640, 100, 30);
    }


    private void drawButtons(Graphics g) {
        bMenu.draw(g);
    }


    public void draw(Graphics g) {

        //background
        g.setColor(new Color(102, 111, 63));
        g.fillRect(x, y, width, height);

        //botões
        drawButtons(g);
    }

    
    public void mouseClicked(int x, int y) {
        if(bMenu.getBounds().contains(x,y))
            SetGameState(MENU);
    }

    public void mouseMoved(int x, int y) {
        bMenu.setMouseOver(false);
        if (bMenu.getBounds().contains(x, y))
            bMenu.setMouseOver(true);
    }

    public void mousePressed(int x, int y) {
        if(bMenu.getBounds().contains(x, y))
            bMenu.setMousePressed(true);
    }

    public void mouseReleased(int x, int y) {
        bMenu.resetBooleans();
    }


}
