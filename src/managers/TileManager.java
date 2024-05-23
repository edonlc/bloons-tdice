package managers;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import helpz.ImgFix;
import helpz.LoadSave;
import objects.Tile;

public class TileManager {

    public Tile GRASS,WATER,HROAD;                                                     //tiles normais

    public Tile BL_WATER_CORNER, TL_WATER_CORNER, BR_WATER_CORNER, TR_WATER_CORNER;   // corners da agua
    public Tile CL_WATER_CORNER, CR_WATER_CORNER, CT_WATER_CORNER, CB_WATER_CORNER;   // "corner" em C da agua
    public Tile LINE_T_WATER, LINE_B_WATER, LINE_L_WATER , LINE_R_WATER;              // linha unica agua
    public Tile DLINE_V_WATER, DLINE_H_WATER;                                          // linha dupla agua
    public Tile PISCINA;                                                              // piscininha de agua

    public Tile V_ROAD;                                                                // rua vertical
    public Tile TR_ROAD_CORNER, TL_ROAD_CORNER, BR_ROAD_CORNER, BL_ROAD_CORNER;       // corners da rua

    public BufferedImage atlas;
    public ArrayList<Tile> tiles = new ArrayList<>();


    public TileManager(){

        loadAtalas();
        createTiles();

    }

    private void createTiles() {

        int id = 0;
        // NORMAIS
        tiles.add(WATER = new Tile(getSprite(0, 1), id++, "Water"));
        tiles.add(GRASS = new Tile(getSprite(0, 0), id++, "Grass"));
        tiles.add(HROAD = new Tile(getSprite(1, 0), id++, "HRoad"));

        // AGUA EM L
        tiles.add(BR_WATER_CORNER = new Tile(ImgFix.buildImage(getImgs(0, 1, 2, 2)), id++, "BR_Water_Corner"));
        tiles.add(BL_WATER_CORNER = new Tile(ImgFix.getBuildRotImage(getImgs(0, 1, 2, 2), 90, 1), id++, "BL_Water_Corner"));
        tiles.add(TL_WATER_CORNER = new Tile(ImgFix.getBuildRotImage(getImgs(0, 1, 2, 2), 180, 1), id++, "TL_Water_Corner"));
        tiles.add(TR_WATER_CORNER = new Tile(ImgFix.getBuildRotImage(getImgs(0, 1, 2, 2), 270, 1), id++, "TR_Water_Corner"));

        //AGUA EM C
        tiles.add(CL_WATER_CORNER = new Tile(ImgFix.buildImage(getImgs(0, 1, 1, 2)), id++, "CL_Water_Corner"));
        tiles.add(CR_WATER_CORNER = new Tile(ImgFix.getBuildRotImage(getImgs(0, 1, 1, 2), 180, 1), id++, "CR_Water_Corner"));
        tiles.add(CT_WATER_CORNER = new Tile(ImgFix.getBuildRotImage(getImgs(0, 1, 1, 2), 90, 1), id++, "CT_Water_Corner"));
        tiles.add(CB_WATER_CORNER = new Tile(ImgFix.getBuildRotImage(getImgs(0, 1, 1, 2), 270, 1), id++, "CB_Water_Corner"));

        // AGUA COM LINHA UNICA
        tiles.add(LINE_T_WATER = new Tile(ImgFix.buildImage(getImgs(0, 1, 1, 0)), id++, "LINE_T_WATER"));
        tiles.add(LINE_R_WATER = new Tile(ImgFix.getBuildRotImage(getImgs(0, 1, 1, 0), 90, 1), id++, "LINE_R_WATER"));
        tiles.add(LINE_L_WATER = new Tile(ImgFix.getBuildRotImage(getImgs(0, 1, 1, 0), 270, 1), id++, "LINE_L_WATER"));
        tiles.add(LINE_B_WATER = new Tile(ImgFix.getBuildRotImage(getImgs(0, 1, 1, 0), 180, 1), id++, "LINE_B_WATER"));

        // CAMINHO DE AGUA (HORIZONTAL OU VERTICAL)
        tiles.add(DLINE_H_WATER = new Tile(ImgFix.buildImage(getImgs(0, 1, 2, 1)), id++, "DLINE_H_WATER"));
        tiles.add(DLINE_V_WATER = new Tile(ImgFix.getBuildRotImage(getImgs(0, 1, 2, 1), 90, 1), id++, "DLINE_V_WATER"));

        // PISCINA DE AGUA
        tiles.add(PISCINA = new Tile(ImgFix.buildImage(getImgs(0, 2, 0, 2)), id++, "PISCINA"));

        // RUA VERTICAL
        tiles.add(V_ROAD = new Tile(ImgFix.getBuildRotImage(getImgs(1, 0, 1, 0), 90, 1), id++, "V_ROAD"));

        // CORNER DA RUA
        tiles.add(TR_ROAD_CORNER = new Tile(ImgFix.buildImage(getImgs(2, 0, 2, 0)), id++, "TR_Road_Corner"));
        tiles.add(BL_ROAD_CORNER = new Tile(ImgFix.getBuildRotImage(getImgs(2, 0, 2, 0), 180, 1), id++, "BL_Road_Corner"));
        tiles.add(TL_ROAD_CORNER = new Tile(ImgFix.getBuildRotImage(getImgs(2, 0, 2, 0), 270, 1), id++, "TL_Road_Corner"));
        tiles.add(BR_ROAD_CORNER = new Tile(ImgFix.getBuildRotImage(getImgs(2, 0, 2, 0), 90, 1), id++, "BR_Road_Corner"));
    }

    private BufferedImage[] getImgs(int firstX, int firstY, int secondX, int secondY) {
        return new BufferedImage[]{getSprite(firstX, firstY), getSprite(secondX, secondY)};
    }

    private void loadAtalas() {

        atlas = LoadSave.getSpriteAtlas();

    }

    public Tile getTile(int id) {
        return tiles.get(id);
    }

    public BufferedImage getSprite(int id){
        return tiles.get(id).getSprite();
    }

    private BufferedImage getSprite(int xCord, int yCord) {
        return atlas.getSubimage(xCord*32, yCord*32, 32, 32);
    }

}
