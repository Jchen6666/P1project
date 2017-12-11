public abstract class Settings {
    private static float SCREEN_WIDTH;
    private static float SCREEN_HEIGHT;
    private static boolean FULLSCREEN;
    private static float MUSIC_VOLUME;
    private static float SCREEN_WIDTH_CHANGED;
    private static float SCREEN_HEIGHT_CHANGED;
    private static float MUSIC_VOLUME_CHANGED;

    public static void setScreenWidth(float screenWidth) {
        SCREEN_WIDTH = screenWidth;
    }

    public static void setScreenHeight(float screenHeight) {
        SCREEN_HEIGHT = screenHeight;
    }

    public static float getScreenWidthChanged() {
        return SCREEN_WIDTH_CHANGED;
    }

    public static void setScreenWidthChanged(float screenWidthChanged) {
        SCREEN_WIDTH_CHANGED = screenWidthChanged;
    }

    public static float getScreenHeightChanged() {
        return SCREEN_HEIGHT_CHANGED;
    }

    public static void setScreenHeightChanged(float screenHeightChanged) {
        SCREEN_HEIGHT_CHANGED = screenHeightChanged;
    }

    public static float getMusicVolumeChanged() {
        return MUSIC_VOLUME_CHANGED;
    }

    public static void setMusicVolumeChanged(float musicVolumeChanged) {
        MUSIC_VOLUME_CHANGED = musicVolumeChanged;
    }

    public static float getMusicVolume() {
        return MUSIC_VOLUME;
    }

    public static void setMusicVolume(float musicVolume) {
        MUSIC_VOLUME = musicVolume;
    }

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
