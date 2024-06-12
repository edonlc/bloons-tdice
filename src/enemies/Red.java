package enemies;

import static helpz.Constants.Enemies.RED;

import managers.EnemyManager;

public class Red extends Enemy {

    public Red(float x, float y, int ID, EnemyManager em) {
        super(x, y, ID, RED, em);
    }
}
