package scenes;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import main.Game;

public class Playing extends GameScene implements SceneMethods{

    private BufferedImage img;
    private ArrayList<BufferedImage> sprites = new ArrayList<>();
    public Playing(Game game) {
        super(game);
        importImg();
        loadSprites();
    }

    public void render(Graphics g){
        for (int y = 0; y < 20; y++) {
            for (int x = 0; x < 20; x++) {
                g.drawImage(sprites.get(5), x*32, y*32, null);
            }
        }
    }


    private void importImg(){

        InputStream is = getClass().getResourceAsStream("/spriteatlas.png");

        try {
            img = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadSprites(){
        for(int y = 0; y < 3; y++){
            for(int x = 0; x < 10; x++){
                sprites.add(img.getSubimage(x * 32, y * 32, 32, 32));
            }
        }
    }

    @Override
    public void mouseClicked(int x, int y) {
    }

    @Override
    public void mouseMoved(int x, int y) {
        // TODO Auto-generated method stub
    }

    @Override
    public void mousePressed(int x, int y) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mousePressed'");
    }

    @Override
    public void mouseReleased(int x, int y) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mouseReleased'");
    }

}
