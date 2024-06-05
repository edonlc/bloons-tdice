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
        public static final int ICE_MONKEY = 1;
        public static final int TACK_SHOOTER = 2;

        public static String GetName(int towerType) {
            switch (towerType) {
            case DART_MONKEY:
                return "Dart Monkey";
            case ICE_MONKEY:
                return "Ice Monkey";
            case TACK_SHOOTER:
                return "Tack Shooter";
            }
            return "";
        }

        public static float GetDefaultDmg(int towerType) {

            switch (towerType) {
                case DART_MONKEY:
                    return 10;
                case ICE_MONKEY:
                    return 5;
                case TACK_SHOOTER:
                    return 20;
                }

                return 0;
        }

        public static float GetDefaultRange(int towerType) {

            switch (towerType) {
                case DART_MONKEY:
                    return 100;
                case ICE_MONKEY:
                    return 100;
                case TACK_SHOOTER:
                    return 100;
                }

                return 0;
        }

        public static float GetDefaultCooldown(int towerType) {

            switch (towerType) {
                case DART_MONKEY:
                    return 10;
                case ICE_MONKEY:
                    return 10;
                case TACK_SHOOTER:
                    return 10;
                }

                return 0;

        }
    }

}
