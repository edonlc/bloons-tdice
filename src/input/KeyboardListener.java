package input;

import static main.GameStates.EDITING;
import static main.GameStates.PLAYING;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import main.Game;
import main.GameStates;

public class KeyboardListener implements KeyListener{

    private Game game;

    public KeyboardListener(Game game) {
        this.game = game;    
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (GameStates.gameState == EDITING) {
            game.getEditor().keyPressed(e);
        } else if (GameStates.gameState == PLAYING) {
            game.getPlaying().keyPressed(e);
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

}
