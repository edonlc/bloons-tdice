package helpz;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import enemies.*;
import managers.EnemyManager;

public class SaveWave {
    private static final String path = "arq/waves/";
    


    public static void createWaveFile(String name) {
        ArrayList<Enemy> enemies = new ArrayList<>();
        for (int i = 0; i < 9; i++){
            Enemy r = new Red(0, 0, 0);
            enemies.add(r);
        }

        for (int i = 0; i < 1; i++){
            Enemy b = new Blue(0, 0, 1);
            enemies.add(b);
        }

        try (FileOutputStream fos = new FileOutputStream(path + name);
            ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(enemies);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static ArrayList<Enemy> loadWaveFile(String name) {
        ArrayList<Enemy> enemies = new ArrayList<Enemy>();
        try (FileInputStream fis = new FileInputStream(path + name);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
                @SuppressWarnings("unchecked")
                ArrayList<Enemy> enemiess= (ArrayList<Enemy>) ois.readObject();
                return enemiess;
        } catch (IOException e) {
                e.printStackTrace();
        } catch (ClassNotFoundException cnfe) {
                cnfe.printStackTrace();
        }
        return enemies;
    }

}
