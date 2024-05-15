

import javax.swing.JFrame;

public class Game extends JFrame{ 

    private GameScreen gameScreen;

    public Game(){
        setSize(400, 400);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        Game bloons = new Game();
    }
}