package main;

import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;

import input.KeyboardListener;
import input.MouseListener;

public class GameScreen extends JPanel {

    private Game game;
    private Dimension size;

    private MouseListener mouse;
    private KeyboardListener kb;
    

    public GameScreen(Game game){
        this.game = game;

        setPanelSize();
    }

    public void initInputs() {
        mouse = new MouseListener(game);
        kb = new KeyboardListener();

        addMouseListener(mouse);
        addMouseMotionListener(mouse);
        addKeyListener(kb);

        requestFocus();
    }

    private void setPanelSize() {
        size = new Dimension(640, 640);
        setMinimumSize(size);
        setPreferredSize(size);
        setMaximumSize(size);

    }


    public void paintComponent(Graphics g) {
        super.paintComponents(g);
        game.getRender().render(g);
    }
}
