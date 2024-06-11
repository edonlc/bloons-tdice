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
    private int waveTickLimit  = 60 * 5;
    private int waveTick = 0;
    private boolean waveStartTimer;
    private boolean waveTickTimeOver;

    public WaveManager(Playing playing) {
        this.playing = playing;
        createWaves();
    }

    public void update() {
        if(enemySpawnTick < enemySpawnTickLimit)
            enemySpawnTick++;

        if(waveStartTimer) {
            waveTick++;
            if(waveTick >= waveTickLimit) {

                waveTickTimeOver = true;
            }
        }
    }

    public void increaseWaveIndex() {
        waveIndex++;
        waveTickTimeOver = false;
        waveStartTimer = false;
    }

    public boolean isWaveTimerOver() {
        return waveTickTimeOver;
    }


    public void startWaveTimer() {
        waveStartTimer = true;
    }


    public Enemy getNextEnemy() {
        enemySpawnTick = 0;
        return waves.get(waveIndex).getEnemies().get(enemyIndex++);
    }

    private void createWaves() {
        for (int i = 1; i < 8; i++) {
            ArrayList<Enemy> enemies = SaveWave.loadWaveFile(i + ".wave");
            waves.add(new Wave(enemies));
        }


        //ArrayList<Enemy> enemies = SaveWave.loadWaveFile("1.wave");
        //waves.add(new Wave(enemies));
    }

    public ArrayList<Wave> getWaves() {
        return waves;
    }

    public boolean isTimeForNewEnemy() {
        return enemySpawnTick >= enemySpawnTickLimit;
    }

    public boolean isThereMoreEnemies() { // SE TEM MAIS INIMIGOS NA WAVE
        return enemyIndex  < waves.get(waveIndex).getEnemies().size();
    }

    public boolean isThereMoreWaves() {
        return waveIndex + 1 < waves.size();
    }

    public void resetEnemyIndex() {
        enemyIndex = 0;
    }

    public float getTimeLeft() {
        float ticksLeft = waveTickLimit - waveTick;
        return ticksLeft / 60.0f;
    }

    public boolean isWaveTimerStarted() {
        return waveStartTimer;
    }
    
    public int getWaveIndex() {
        return waveIndex;
    }

}
