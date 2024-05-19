package input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.event.MouseInputListener;

import main.Game;
import main.GameStates;

public class MouseListener implements MouseInputListener,MouseMotionListener{

    private Game game;

    public MouseListener(Game game) {
        this.game = game;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            
            switch(GameStates.gameState) {
            case MENU:
                    game.getMenu().mouseClicked(e.getX(), e.getY());
                    break;
            case PLAYING:
                    break;
            case SETTINGS:

                    break;
            }
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void mousePressed(MouseEvent e) {
        switch(GameStates.gameState) {
        case MENU:
                game.getMenu().mousePressed(e.getX(), e.getY());
                break;
        case PLAYING:
                break;
        case SETTINGS:

                break;
        }
    }
    

    @Override
    public void mouseReleased(MouseEvent e) {
        switch(GameStates.gameState) {
        case MENU:
            game.getMenu().mouseReleased(e.getX(), e.getY());
            break;
        case PLAYING:
            game.getPlaying().mouseMoved(e.getX(), e.getY());
            break;
        case SETTINGS:
            game.getSettings().mouseMoved(e.getX(), e.getY());
            break;
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        switch(GameStates.gameState) {
        case MENU:
            game.getMenu().mouseMoved(e.getX(), e.getY());
            break;
        case PLAYING:
            game.getPlaying().mouseMoved(e.getX(), e.getY());
            break;
        case SETTINGS:
            game.getSettings().mouseMoved(e.getX(), e.getY());
            break;
        default:
            break;
        }
    }

}