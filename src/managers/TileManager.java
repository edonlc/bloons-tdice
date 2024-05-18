package managers;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import helpz.LoadSave;
import objects.Tile;

public class TileManager {

    public Tile GRASS,WATER,ROAD;
    public BufferedImage atlas;
    public ArrayList<Tile> tiles = new ArrayList<>();


    public TileManager(){

        loadAtalas();
        createTiles();

    }

    private void createTiles() {

        tiles.add(WATER = new Tile(getSprite(1, 1)));
        tiles.add(GRASS = new Tile(getSprite(1, 1)));
        tiles.add(ROAD = new Tile(getSprite(2, 1)));
    }

    private void loadAtalas() {

        atlas = LoadSave.getSpriteAtlas();

    }

    public BufferedImage getSprite(int id){
        return tiles.get(id).getSprite();
    }

    private BufferedImage getSprite(int xCord, int yCord) {
        return atlas.getSubimage(xCord*32, yCord*32, 32, 32);
    }

}
