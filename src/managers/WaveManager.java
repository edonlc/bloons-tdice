package managers;

import java.util.ArrayList;

import enemies.Enemy;
import events.Wave;
import helpz.SaveWave;
import scenes.Playing;

public class WaveManager {
    private Playing playing;
    private ArrayList<Wave> waves = new ArrayList<>();
    private int enemySpawnTickLimit = 60;
    private int enemySpawnTick = enemySpawnTickLimit;
    private int enemyIndex, waveIndex;
    

    public WaveManager(Playing playing) {
        this.playing = playing;
        createWaves();
    }

    public void update() {
        if(enemySpawnTick < enemySpawnTickLimit)
            enemySpawnTick++;
    }

    public Enemy getNextEnemy() {
        enemySpawnTick = 0;
        return waves.get(waveIndex).getEnemies().get(enemyIndex++);
    }

    private void createWaves() {
        ArrayList<Enemy> enemies = SaveWave.loadWaveFile("1.wave");
        waves.add(new Wave(enemies));
    }

    public ArrayList<Wave> getWaves() {
        return waves;
    }

    public boolean isTimeForNewEnemy() {
        return enemySpawnTick >= enemySpawnTickLimit;
    }

    public boolean isThereMoreEnemies() {
        return enemyIndex  < waves.get(waveIndex).getEnemies().size();
    }

}
