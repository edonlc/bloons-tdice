package scenes;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import exceptions.TileOutOfScreenException;
import helpz.LoadSave;
import main.Game;
import objects.PathPoint;
import objects.Tile;
import ui.EditBar;

import static helpz.Constants.Tiles.ROAD_TILE;

public class Editing extends GameScene implements SceneMethods{

    private int[][] lvl;
    private Tile selectedTile;
    private int mouseX, mouseY;
    private int lastTileX, lastTileY, lastTileId;
    private boolean drawSelect;
    private EditBar editbar;
    private PathPoint start, end;

    public Editing(Game game) {
        super(game);
        loadDefaultLevel();
        editbar = new EditBar(0, 640, 640, 160, this);
    }

    private void loadDefaultLevel() {
        lvl = LoadSave.getLevelData("new_level");
        ArrayList<PathPoint> points = LoadSave.getLevelPathPoints("new_level");
        start = points.get(0);
        end = points.get(1);
    }

    @Override
    public void render(Graphics g) {
        drawLevel(g);
        editbar.draw(g);
        drawSelectedTile(g);
        drawPathPoints(g);
    }

    private void drawPathPoints(Graphics g) {
        if (start != null) {
            g.drawImage(editbar.getStartPathImg(), start.getxCord()*32, start.getyCord()*32, 32, 32, null);
        }  

        if (end != null) {
            g.drawImage(editbar.getEndPathImg(), end.getxCord()*32, end.getyCord()*32, 32, 32, null);
        }
    }

    private void drawLevel(Graphics g) {
        for (int y = 0; y < lvl.length; y++) {
            for (int x = 0; x < lvl[y].length; x++) {
                int id = lvl[y][x];
                g.drawImage(getSprite(id), x * 32, y * 32, null);
            }
        }
    }

    private BufferedImage getSprite(int spriteId) {
        return game.getTileManager().getSprite(spriteId);
    }

    public void saveLevel() {
        LoadSave.SaveLevel("new_level", lvl, start, end);
        game.getPlaying().setLevel(lvl);
    }

	private void drawSelectedTile(Graphics g) {
        if(selectedTile != null && drawSelect){
            g.drawImage(selectedTile.getSprite(), mouseX, mouseY, 32, 32, null);
        }
    }

    public void setSelectedTile(Tile tile){
        this.selectedTile = tile;
        drawSelect = true;
    }

    private void changeTile(int x, int y) throws TileOutOfScreenException{ 
        if(selectedTile != null) {
            int tileX = x / 32;
            int tileY = y / 32;
            if(tileX < 0 || tileX >= lvl[0].length || tileY < 0 || tileY >= lvl.length) {
                throw new TileOutOfScreenException("Index " + tileX + ", " + tileY + " out of bounds for level size.");
            }

            if(selectedTile.getId() >= 0) {
                if(lastTileX == tileX &&lastTileY == tileY && lastTileId == selectedTile.getId())
                    return;
                
                lastTileX = tileX;
                lastTileY = tileY;

                lvl[tileY][tileX] = selectedTile.getId();
            } else {
                int id = lvl[tileY][tileX];
                if (game.getTileManager().getTile(id).getTileType() == ROAD_TILE) {
                    if(selectedTile.getId() == -1)
                        start = new PathPoint(tileX, tileY);
                    else
                        end = new PathPoint(tileX, tileY);
                }
            }
        }
    }

    @Override
    public void mouseClicked(int x, int y) {
        if(y >= 640) {
            editbar.mouseClicked(x, y);
        } else {
            try{
                changeTile(x, y);
            }catch (TileOutOfScreenException e) {
                x = 0;
                y = 0;
            }
        }
    }

    @Override
    public void mouseMoved(int x, int y) {
        if(y >= 640) {
            editbar.mouseMoved(x, y);
            drawSelect = false;
        } else {
            drawSelect = true;
            mouseX = (x / 32) * 32;
            mouseY = (y / 32) * 32;
        }
    }

    @Override
    public void mousePressed(int x, int y) {
        if (y >= 640) {
            editbar.mousePressed(x, y);
        }
    }

    @Override
    public void mouseReleased(int x, int y) {
        editbar.mouseReleased(x, y);
    }

    @Override
    public void mouseDragged(int x, int y) {
        try {
            changeTile(x, y);
        } catch (TileOutOfScreenException e) {
            x = 0;
            y = 0;
        }
        // if(y >= 640 ){

        // } else {
        //     changeTile(x, y);
        // }
    }

    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_R) {
            editbar.rotateSprite();
        }
    }

}
