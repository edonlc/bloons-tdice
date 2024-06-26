package main;

import javax.swing.JFrame;

import helpz.LoadSave;
import managers.TileManager;
import scenes.Editing;
import scenes.GameOver;
import scenes.Menu;
import scenes.Playing;



public class Game extends JFrame implements Runnable{ 

    private GameScreen gameScreen;
    private Thread gameThread;

    private Render render;
    private Menu menu;
    private Playing playing;
    private Editing editing;
    private GameOver gameOver;

    private TileManager tileManager;


    public Game(){

        initClasses();
        createDefaultLevel();

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("Chimps vs Balloons");
        add(gameScreen);
        pack();
        setVisible(true);
    }

    private void createDefaultLevel() {
        int[] arr = new int[400];
        for(int i = 0; i < arr.length; i++)
            arr[i] = 0;
        LoadSave.createLevel("new_level", arr);
    }

    private void initClasses() {
        tileManager = new TileManager();
        render = new Render(this);
        gameScreen = new GameScreen(this);
        menu = new Menu(this);
        playing = new Playing(this);
        editing = new Editing(this);
        gameOver = new GameOver(this);

    }


    private void start(){
        gameThread = new Thread(this){};
        gameThread.start();
    }

    private void updateGame(){
        switch(GameStates.gameState) {
        case MENU:
            break;
        case PLAYING:
            playing.update();
            break;
        case EDITING:
            break;
        default:
            break;
        }
    }

    public static void main(String[] args) {
        Game bloons = new Game();
        bloons.gameScreen.initInputs();
        bloons.start();
    }

    public void run(){

        double timePerFrame = 1_000_000_000.0 / 120.0;
        double timePerUpdate = 1_000_000_000.0 / 60.0;

        long lastFrame = System.nanoTime();
        long lastUpdate = System.nanoTime();
        long lastTimeCheck = System.currentTimeMillis();
        
        int frames = 0;
        int updates = 0;

        long now;

        while (true) {

            now = System.nanoTime();
            if (now - lastFrame >= timePerFrame) {
                repaint();
                lastFrame = now;
                frames++;
            }

            if (now - lastUpdate >= timePerUpdate) {
                updateGame();
                lastUpdate = now;
                updates++;
            }

            if (System.currentTimeMillis() - lastTimeCheck >= 1000) {
                System.out.println("FPS: " + frames + " | UPS: "+ updates);
                frames = 0;
                updates = 0;
                lastTimeCheck = System.currentTimeMillis();
            }
        }
    }


    public Render getRender() {
        return render;
    }

    public Menu getMenu() {
        return menu;
    }

    public Playing getPlaying() {
        return playing;
    }


    public Editing getEditor() {
        return editing;
    }

    public GameOver getGameOver() {
        return gameOver;
    }

    public TileManager getTileManager() {
        return tileManager;
    }
}
