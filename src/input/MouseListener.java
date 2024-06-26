package input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.event.MouseInputListener;

import main.Game;
import main.GameStates;

public class MouseListener implements MouseInputListener, MouseMotionListener{

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
                game.getPlaying().mouseClicked(e.getX(), e.getY());
                break;
            case EDITING:
                game.getEditor().mouseClicked(e.getX(), e.getY());
                break;
            case GAMEOVER:
                game.getGameOver().mouseClicked(e.getX(), e.getY());
                break;
            default:
                break;
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        switch(GameStates.gameState) {
        case MENU:
            game.getMenu().mousePressed(e.getX(), e.getY());
            break;
        case PLAYING:
            game.getPlaying().mousePressed(e.getX(), e.getY());
            break;
        case EDITING:
            game.getEditor().mousePressed(e.getX(), e.getY());
            break;
        case GAMEOVER:
            game.getGameOver().mousePressed(e.getX(), e.getY());
            break;
        default:
            break;
        }
    }
    
    @Override
	public void mouseReleased(MouseEvent e) {
		switch (GameStates.gameState) {
		case MENU:
			game.getMenu().mouseReleased(e.getX(), e.getY());
			break;
		case PLAYING:
			game.getPlaying().mouseReleased(e.getX(), e.getY());
			break;
        case EDITING:
            game.getEditor().mouseReleased(e.getX(), e.getY());
            break;
        case GAMEOVER:
            game.getGameOver().mouseReleased(e.getX(), e.getY());
            break;
		default:
			break;

		}
	}

    @Override
    public void mouseDragged(MouseEvent e) {
        switch(GameStates.gameState) {
        case MENU:
            game.getMenu().mouseDragged(e.getX(), e.getY());
            break;
        case PLAYING:
            game.getPlaying().mouseDragged(e.getX(), e.getY());
            break;
        case EDITING:
            game.getEditor().mouseDragged(e.getX(), e.getY());
            break;
        default:
            break;
        }
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
        case EDITING:
            game.getEditor().mouseMoved(e.getX(), e.getY());
            break;
        case GAMEOVER:
            game.getGameOver().mouseMoved(e.getX(), e.getY());
            break;
        default:
            break;
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

}
