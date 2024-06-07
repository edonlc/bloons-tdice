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

        public static float GetSpeed(int enemyType) {
            switch(enemyType) {
            case RED:
                return 0.5f;
            case BLUE:
                return 0.7f;
            }
            return 0;
        }

        public static int GetStartHealth(int enemyType) {
            switch (enemyType) {
                case RED:
                    return 10;
                case BLUE:
                    return 20;
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
        public static final int DART_MONKEY_LV2 = 1;
        public static final int DART_MONKEY_LV3 = 2;
        public static final int ICE_MONKEY = 3;
        public static final int ICE_MONKEY_LV2 = 4;
        public static final int ICE_MONKEY_LV3 = 5;
        public static final int TACK_SHOOTER = 6;

        public static String GetName(int towerType) {
            switch (towerType) {
            case DART_MONKEY:
                return "Dart Monkey - 1";
            case DART_MONKEY_LV2:
                return "Dart Monkey - 2";
            case DART_MONKEY_LV3:
                return "Dart Monkey - 3";
            case ICE_MONKEY:
                return "Ice Monkey - 1";
            case ICE_MONKEY_LV2:
                return "Ice Monkey - 2";
            case ICE_MONKEY_LV3:
                return "Ice Monkey - 3";
            case TACK_SHOOTER:
                return "Tack Shooter - 1";
            }
            return "";
        }

        public static int GetDefaultDmg(int towerType) {

            switch (towerType) {
                case DART_MONKEY:
                    return 10;
                case DART_MONKEY_LV2:
                    return 20;
                case DART_MONKEY_LV3:
                    return 30;
                case ICE_MONKEY:
                    return 1;
                case ICE_MONKEY_LV2:
                    return 1;
                case ICE_MONKEY_LV3:
                    return 1;
                case TACK_SHOOTER:
                    return 20;
                }

                return 0;
        }

        public static float GetDefaultRange(int towerType) {

            switch (towerType) {
                case DART_MONKEY:
                    return 50;
                case DART_MONKEY_LV2:
                    return 75;
                case DART_MONKEY_LV3:
                    return 100;
                case ICE_MONKEY:
                    return 100;
                case ICE_MONKEY_LV2:
                    return 150;
                case ICE_MONKEY_LV3:
                    return 200;
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
                    return 30;
                case DART_MONKEY_LV3:
                    return 20;
                case ICE_MONKEY:
                    return 10;
                case ICE_MONKEY_LV2:
                    return 7;
                case ICE_MONKEY_LV3:
                    return 5;
                case TACK_SHOOTER:
                    return 10;
                }

                return 0;

        }
    }

    public static class Projectiles{
        public static final int DART = 0;
        public static final int ICE = 1;
        public static final int TACK = 2;

        public static float getSpeed(int type) {
            switch (type) {
            case DART:
                return 3f;
            case ICE:
                return 2f;
            case TACK:
                return 1f;
            }
            return 0f;
        }
    }

}
