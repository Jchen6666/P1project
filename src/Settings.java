public class Settings {
    private static float SCREEN_WIDTH;
    private static float SCREEN_HEIGHT;
    private static boolean FULLSCREEN;

    public static float getScreenWidth() {
        return SCREEN_WIDTH;
    }

    public static void setScreenWidth(int screenWidth) {
        SCREEN_WIDTH = screenWidth;
    }

    public static float getScreenHeight() {
        return SCREEN_HEIGHT;
    }

    public static void setScreenHeight(int screenHeight) {
        SCREEN_HEIGHT = screenHeight;
    }

    public static boolean isFULLSCREEN() {
        return FULLSCREEN;
    }

    public static void setFULLSCREEN(boolean FULLSCREEN) {
        Settings.FULLSCREEN = FULLSCREEN;
    }
}
