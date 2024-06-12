package objects;

import static helpz.Constants.Towers.*;

public class Tower {

    private int x, y, id, towerType, cdTick, dmg, tier;
    private float range, cooldown;
    

    public Tower(int x, int y, int id, int towerType) {
        this.x = x;
        this.y = y;
        this.id = id;
        this.towerType = towerType;
        switch (towerType) {
            case TACK_SHOOTER:
                this.tier = 3;
            default:
                this.tier = 1;
        }
        setDefaultDmg();
        setDefaultRange();
        setDefaultCooldown();
    }

    public void upgradeTower() {
        this.tier++;
        switch (towerType) {
            case DART_MONKEY:
                towerType += 3;
                range += 20;
                dmg += 2;
                cooldown -= 5;
                break;
            case DART_MONKEY_LV2:
                towerType += 2;
                range += 20;
                dmg += 2;
                cooldown -= 5;
                break;
            case ICE_MONKEY:
                towerType += 3;
                range += 20;
                cooldown -= 5;
                break;
            case ICE_MONKEY_LV2:
                towerType += 2;
                range += 20;
                cooldown -= 5;
                break;
            case TACK_SHOOTER:
                break;
            }

    }

    public void update() {
        cdTick++;
    }

    public boolean isCooldownOver() {

        return cdTick >= cooldown;
    }

    public void resetCooldown() {
        cdTick = 0;
    }

    private void setDefaultCooldown() {
        cooldown = helpz.Constants.Towers.GetDefaultCooldown(towerType);
    }

    private void setDefaultDmg() {
        dmg = helpz.Constants.Towers.GetDefaultDmg(towerType);
    }

    private void setDefaultRange() {
        range = helpz.Constants.Towers.GetDefaultRange(towerType);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getID() {
        return id;
    }

    public void setID(int id) {
        this.id = id;
    }

    public int getTowerType() {
        return towerType;
    }

    public void setTowerType(int towerType) {
        this.towerType = towerType;
    }

    public int getDmg() {
        return dmg;
    }

    public float getRange() {
        return range;
    }

    public float getCooldown() {
        return cooldown;
    }

    public int getTier() {
        return tier;
    }
}
