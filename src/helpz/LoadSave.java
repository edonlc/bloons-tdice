package helpz;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import javax.imageio.ImageIO;

public class LoadSave {

    public static BufferedImage getSpriteAtlas() {

        BufferedImage img = null;
        InputStream is = LoadSave.class.getClassLoader().getResourceAsStream("spriteatlas.png");

        try {
            img = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return img;
    }

    public static void createFile() {
        File txtFile = new File("arq/testeTextFile.txt");

        try {
            txtFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void writeFile(File f, int[] idArr) {

        try {
            PrintWriter pw = new PrintWriter(f);

            for (Integer i : idArr) {
                pw.println(i);
            }
            pw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void SaveLevel(String name, int[][] idArr) {
        File levelFile = new File("arq/" + name + ".txt");

        if (levelFile.exists()) {
            writeFile(levelFile, Utilz.matrixToVector(idArr));
        } else {
            System.out.println("File: " + name + " does not exists!");
            return;
        }
    }

    public static ArrayList<Integer> readFile(File f) {
        ArrayList<Integer> list = new ArrayList<>();
        try {
            Scanner sc = new Scanner(f);

            while (sc.hasNextLine()) {
                list.add(Integer.parseInt(sc.nextLine()));
            }

            sc.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static void createLevel(String name, int[] idArr) {
        File newLevel= new File("arq/" + name + ".txt");

        if (newLevel.exists()) {
            System.out.println("File: " + name + " already exists!");
            return;
        } else {
            try {
                newLevel.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            writeFile(newLevel, idArr);
        }
    } 

    public static int[][] getLevelData(String name) {
        File lvlFile = new File("arq/" + name + ".txt");

        if (lvlFile.exists()) {
            ArrayList<Integer> list = readFile(lvlFile);
            return Utilz.arrayToMatrix(list, 20, 20);
        } else {
            System.out.println("File: " + name + " does not exists!");
            return null;
        }
    }
}
