package managers;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import helpz.ImgFix;
import helpz.LoadSave;
import objects.Tile;
import static helpz.Constants.Tiles.*;

public class TileManager {

    public Tile GRASS,WATER,H_ROAD;                                                     //tiles normais

    public Tile BL_WATER_CORNER, TL_WATER_CORNER, BR_WATER_CORNER, TR_WATER_CORNER;   // corners da agua
    public Tile CL_WATER_CORNER, CR_WATER_CORNER, CT_WATER_CORNER, CB_WATER_CORNER;   // "corner" em C da agua
    public Tile LINE_T_WATER, LINE_B_WATER, LINE_L_WATER , LINE_R_WATER;              // linha unica agua
    public Tile DLINE_V_WATER, DLINE_H_WATER;                                          // linha dupla agua
    public Tile PISCINA;                                                              // piscininha de agua

    public Tile V_ROAD;                                                                // rua vertical
    public Tile TR_ROAD_CORNER, TL_ROAD_CORNER, BR_ROAD_CORNER, BL_ROAD_CORNER;       // corners da rua

    public BufferedImage atlas;
    public ArrayList<Tile> tiles = new ArrayList<>();

    public ArrayList<Tile> roadsS = new ArrayList<>();
    public ArrayList<Tile> roadsC = new ArrayList<>();
    public ArrayList<Tile> waterL = new ArrayList<>();
    public ArrayList<Tile> waterC = new ArrayList<>();
    public ArrayList<Tile> waterLines = new ArrayList<>();
    public ArrayList<Tile> waterPath = new ArrayList<>();
  


    public TileManager(){

        loadAtalas();
        createTiles();

    }

    private void createTiles() {

        int id = 0;
        // NORMAIS
        tiles.add(WATER = new Tile(getSprite(0, 1), id++, WATER_TILE));
        tiles.add(GRASS = new Tile(getSprite(0, 0), id++, GRASS_TILE));

        
        // RUA 
        roadsS.add(H_ROAD = new Tile(getSprite(1, 0), id++, ROAD_TILE));
        roadsS.add(V_ROAD = new Tile(ImgFix.getBuildRotImage(getImgs(1, 0, 1, 0), 90, 1), id++, ROAD_TILE));

        // CORNER DA RUA
        roadsC.add(TR_ROAD_CORNER = new Tile(ImgFix.buildImage(getImgs(2, 0, 2, 0)), id++, ROAD_TILE));
        roadsC.add(BL_ROAD_CORNER = new Tile(ImgFix.getBuildRotImage(getImgs(2, 0, 2, 0), 180, 1), id++, ROAD_TILE));
        roadsC.add(TL_ROAD_CORNER = new Tile(ImgFix.getBuildRotImage(getImgs(2, 0, 2, 0), 270, 1), id++, ROAD_TILE));
        roadsC.add(BR_ROAD_CORNER = new Tile(ImgFix.getBuildRotImage(getImgs(2, 0, 2, 0), 90, 1), id++, ROAD_TILE));

        // AGUA EM L
        waterL.add(BR_WATER_CORNER = new Tile(ImgFix.buildImage(getImgs(0, 1, 2, 2)), id++, WATER_TILE));
        waterL.add(BL_WATER_CORNER = new Tile(ImgFix.getBuildRotImage(getImgs(0, 1, 2, 2), 90, 1), id++, WATER_TILE));
        waterL.add(TL_WATER_CORNER = new Tile(ImgFix.getBuildRotImage(getImgs(0, 1, 2, 2), 180, 1), id++, WATER_TILE));
        waterL.add(TR_WATER_CORNER = new Tile(ImgFix.getBuildRotImage(getImgs(0, 1, 2, 2), 270, 1), id++, WATER_TILE));

        //AGUA EM C
        waterC.add(CL_WATER_CORNER = new Tile(ImgFix.buildImage(getImgs(0, 1, 1, 2)), id++, WATER_TILE));
        waterC.add(CR_WATER_CORNER = new Tile(ImgFix.getBuildRotImage(getImgs(0, 1, 1, 2), 180, 1), id++, WATER_TILE));
        waterC.add(CT_WATER_CORNER = new Tile(ImgFix.getBuildRotImage(getImgs(0, 1, 1, 2), 90, 1), id++, WATER_TILE));
        waterC.add(CB_WATER_CORNER = new Tile(ImgFix.getBuildRotImage(getImgs(0, 1, 1, 2), 270, 1), id++, WATER_TILE));

        // AGUA COM LINHA UNICA
        waterLines.add(LINE_T_WATER = new Tile(ImgFix.buildImage(getImgs(0, 1, 1, 1)), id++, WATER_TILE));
        waterLines.add(LINE_R_WATER = new Tile(ImgFix.getBuildRotImage(getImgs(0, 1, 1, 1), 90, 1), id++, WATER_TILE));
        waterLines.add(LINE_L_WATER = new Tile(ImgFix.getBuildRotImage(getImgs(0, 1, 1, 1), 270, 1), id++, WATER_TILE));
        waterLines.add(LINE_B_WATER = new Tile(ImgFix.getBuildRotImage(getImgs(0, 1, 1, 1), 180, 1), id++, WATER_TILE));

        // CAMINHO DE AGUA (HORIZONTAL OU VERTICAL)
        waterPath.add(DLINE_H_WATER = new Tile(ImgFix.buildImage(getImgs(0, 1, 2, 1)), id++, WATER_TILE));
        waterPath.add(DLINE_V_WATER = new Tile(ImgFix.getBuildRotImage(getImgs(0, 1, 2, 1), 90, 1), id++, WATER_TILE));

        tiles.addAll(roadsS);
        tiles.addAll(roadsC);
        tiles.addAll(waterL);
        tiles.addAll(waterC);
        tiles.addAll(waterLines);
        tiles.addAll(waterPath);
        // PISCINA DE AGUA
        tiles.add(PISCINA = new Tile(getSprite(0, 2), id++, WATER_TILE));


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

    public ArrayList<Tile> getRoadsS() {
        return roadsS;
    }

    public ArrayList<Tile> getRoadsC() {
        return roadsC;
    }

    public ArrayList<Tile> getWaterL() {
        return waterL;
    }

    public ArrayList<Tile> getWaterC() {
        return waterC;
    }

    public ArrayList<Tile> getWaterLines() {
        return waterLines;
    }

    public ArrayList<Tile> getWaterPath() {
        return waterPath;
    }

}
