import org.newdawn.slick.TrueTypeFont;

import java.awt.*;

public abstract class OurFonts {
    private static Font javaFont14=new Font("Verdana",Font.PLAIN,14);
    private static Font javaFont18=new Font("Verdana",Font.PLAIN,18);
    private static Font javaFont22=new Font("Verdana",Font.BOLD,22);
    private static Font javaFont26=new Font("Verdana",Font.BOLD,26);
    private static TrueTypeFont font14=new TrueTypeFont(javaFont14,true);
    private static TrueTypeFont font18=new TrueTypeFont(javaFont18,true);
    private static TrueTypeFont font22B=new TrueTypeFont(javaFont22,true);
    private static TrueTypeFont font26B=new TrueTypeFont(javaFont26,true);


    public static TrueTypeFont getFont14() {
        return font14;
    }

    public static TrueTypeFont getFont18() {
        return font18;
    }

    public static TrueTypeFont getFont22B() {
        return font22B;
    }

    public static TrueTypeFont getFont26B() {
        return font26B;
    }
}
