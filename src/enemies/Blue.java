package enemies;

import static helpz.Constants.Enemies.BLUE;

import managers.EnemyManager;

public class Blue extends Enemy {

    public Blue(int x, int y, int ID, EnemyManager em) {
        super(x, y, ID, BLUE, em);
    }
}
