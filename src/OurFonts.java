import org.newdawn.slick.TrueTypeFont;

import java.awt.*;

public class OurFonts {
    private static Font javaFont18=new Font("Verdana",Font.BOLD,18);
    private static Font javaFont22=new Font("Verdana",Font.BOLD,22);
    private static Font javaFont26=new Font("Verdana",Font.BOLD,26);
    private static TrueTypeFont font18B=new TrueTypeFont(javaFont18,true);
    private static TrueTypeFont font22B=new TrueTypeFont(javaFont22,true);
    private static TrueTypeFont font26B=new TrueTypeFont(javaFont26,true);

    public static TrueTypeFont getFont18B() {
        return font18B;
    }

    public static TrueTypeFont getFont22B() {
        return font22B;
    }

    public static TrueTypeFont getFont26B() {
        return font26B;
    }
}
