package helpz;

public class Constants {
    public static class Direction {
        public static final int LEFT = 0;
        public static final int UP = 1;
        public static final int RIGHT = 2;
        public static final int DOWN = 3;
    }

    public static class Enemies {
        public static final int RED = 0;
        public static final int BLUE = 1;

        public static int GetReward(int enemyType) {
            switch(enemyType) {
                case RED:
                    return 5;
                case BLUE:
                    return 8;
                }
                return 0;
        }

        public static float GetSpeed(int enemyType) {
            switch(enemyType) {
            case RED:
                return 0.8f;
            case BLUE:
                return 1.1f;
            }
            return 0;
        }

        public static int GetStartHealth(int enemyType) {
            switch (enemyType) {
                case RED:
                    return 20;
                case BLUE:
                    return 30;
            }
            return 0;
        }
    }

    public static class Tiles {
        public static final int WATER_TILE = 0;
        public static final int GRASS_TILE = 1;
        public static final int ROAD_TILE = 2;
    }

    public static class Towers {
        public static final int DART_MONKEY = 0;
        public static final int ICE_MONKEY = 1;
        public static final int TACK_SHOOTER = 2;
        public static final int DART_MONKEY_LV2 = 3;
        public static final int ICE_MONKEY_LV2 = 4;
        public static final int DART_MONKEY_LV3 = 5;
        public static final int ICE_MONKEY_LV3 = 6;

        public static int GetTowerPrice(int towerType) {
            switch (towerType) {
                case DART_MONKEY:
                    return 45;
                case DART_MONKEY_LV2:
                    return 45;
                case DART_MONKEY_LV3:
                    return 45;
                case ICE_MONKEY:
                    return 50;
                case ICE_MONKEY_LV2:
                    return 50;
                case ICE_MONKEY_LV3:
                    return 50;
                case TACK_SHOOTER:
                    return 100;
                }
                return 0;
        }

        public static String GetName(int towerType) {
            switch (towerType) {
            case DART_MONKEY:
                return "Dart Monkey";
            case DART_MONKEY_LV2:
                return "Dart Monkey";
            case DART_MONKEY_LV3:
                return "Dart Monkey";
            case ICE_MONKEY:
                return "Ice Monkey";
            case ICE_MONKEY_LV2:
                return "Ice Monkey";
            case ICE_MONKEY_LV3:
                return "Ice Monkey";
            case TACK_SHOOTER:
                return "Tack Shooter";
            }
            return "";
        }

        public static int GetDefaultDmg(int towerType) {

            switch (towerType) {
                case DART_MONKEY:
                    return 10;
                case DART_MONKEY_LV2:
                    return 12;
                case DART_MONKEY_LV3:
                    return 14;
                case ICE_MONKEY:
                    return 1;
                case ICE_MONKEY_LV2:
                    return 1;
                case ICE_MONKEY_LV3:
                    return 1;
                case TACK_SHOOTER:
                    return 15;
                }

                return 0;
        }

        public static float GetDefaultRange(int towerType) {

            switch (towerType) {
                case DART_MONKEY:
                    return 50;
                case DART_MONKEY_LV2:
                    return 70;
                case DART_MONKEY_LV3:
                    return 90;
                case ICE_MONKEY:
                    return 70;
                case ICE_MONKEY_LV2:
                    return 90;
                case ICE_MONKEY_LV3:
                    return 110;
                case TACK_SHOOTER:
                    return 100;
                }

                return 0;
        }

        public static float GetDefaultCooldown(int towerType) {

            switch (towerType) {
                case DART_MONKEY:
                    return 40;
                case DART_MONKEY_LV2:
                    return 35;
                case DART_MONKEY_LV3:
                    return 30;
                case ICE_MONKEY:
                    return 40;
                case ICE_MONKEY_LV2:
                    return 35;
                case ICE_MONKEY_LV3:
                    return 25;
                case TACK_SHOOTER:
                    return 30;
                }

                return 0;

        }
    }

    public static class Projectiles{
        public static final int DART = 0;
        public static final int DART_T2 = 1;
        public static final int DART_T3 = 2;
        public static final int ICE = 3;
        public static final int ICE_T2 = 4;
        public static final int ICE_T3 = 5;

        public static float getSpeed(int type) {
            switch (type) {
            case DART:
                return 3f;
            case DART_T2:
                return 4f;
            case DART_T3:
                return 5f;
            case ICE:
                return 2f;
            case ICE_T2:
                return 2f;
            case ICE_T3:
                return 2f;
            }
            return 0f;
        }
    }

}
