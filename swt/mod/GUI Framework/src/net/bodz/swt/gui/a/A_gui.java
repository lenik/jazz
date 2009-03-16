package net.bodz.swt.gui.a;

import java.lang.reflect.AnnotatedElement;

import net.bodz.bas.gui.a.Font;
import net.bodz.bas.gui.a.Icon;
import net.bodz.bas.gui.a.PreferredSize;
import net.bodz.bas.lang.err.CreateException;
import net.bodz.bas.lang.err.IllegalUsageError;
import net.bodz.bas.mod.Factory;
import net.bodz.bas.types.TextMap;
import net.bodz.bas.types.TextMap.HashTextMap;
import net.bodz.bas.types.util.Types;
import net.bodz.swt.util.SWTResources;

import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.RGB;

public class A_gui extends net.bodz.bas.gui.a.A_gui {

    public static Image getIcon(AnnotatedElement aobject) {
        Icon aicon = aobject.getAnnotation(Icon.class);
        if (aicon == null)
            return null;
        Factory<?> iconFactory = getIconFactory(aicon);
        if (iconFactory == null) { // load resource-path
            String[] resPaths = aicon.value();
            if (resPaths.length == 0)
                return null;
            String resPath = resPaths[0];
            Image image = SWTResources.getImage(resPath);
            return image;
        }
        try {
            Image image = (Image) iconFactory.create();
            return image;
        } catch (CreateException e) {
            throw new RuntimeException(e);
        }
    }

    public static Factory<?> getFontFactory(Font afont) {
        if (afont == null)
            return null;
        Class<? extends Factory<?>> factoryClass = afont.factory();
        if (factoryClass == Factory.class) {
            String name = afont.name();
            int height = afont.height();
            int style = afont.style();
            FontData fontData = new FontData(name, height, style);
            return new Factory.Static<FontData>(fontData);
        }
        try {
            return Types.getClassInstance(factoryClass);
        } catch (CreateException e) {
            throw new RuntimeException(e);
        }
    }

    public static FontData getFont(AnnotatedElement aobject) {
        Font nfont = aobject.getAnnotation(Font.class);
        if (nfont == null)
            return null;
        Factory<?> fontFactory = getFontFactory(nfont);
        try {
            FontData fontData = (FontData) fontFactory.create();
            return fontData;
        } catch (CreateException e) {
            throw new RuntimeException(e);
        }
    }

    public static Point parseSize(PreferredSize size) {
        if (size == null)
            return null;
        return new Point(size.width(), size.height());
    }

    static TextMap<RGB> colorNames;
    static {
        colorNames = new HashTextMap<RGB>();
        colorNames.put("black", new RGB(0, 0, 0));
        colorNames.put("white", new RGB(255, 255, 255));
        colorNames.put("red", new RGB(255, 0, 0));
        colorNames.put("green", new RGB(0, 255, 0));
        colorNames.put("blue", new RGB(0, 0, 255));
        colorNames.put("cyan", new RGB(0, 255, 255));
        colorNames.put("magenta", new RGB(255, 0, 255)); // pink
        colorNames.put("yellow", new RGB(255, 255, 0));
        colorNames.put("gray", new RGB(128, 128, 128));
    }

    /**
     * [NAME]#AARRGGBB
     */
    public static RGB parseColor(String colorExp) {
        if (colorExp == null || colorExp.isEmpty())
            return null;
        int sharp = colorExp.indexOf('#');
        String name = sharp == -1 ? colorExp : colorExp.substring(0, sharp);
        if (!name.isEmpty()) {
            RGB rgb = colorNames.get(name);
            if (rgb != null)
                return rgb;
        }
        // color name not exists, and the fail value isn't specified.
        if (sharp == -1)
            throw new IllegalUsageError("Bad color name: " + colorExp);
        String failval = colorExp.substring(sharp + 1);
        if (failval.length() != 6)
            throw new IllegalUsageError("color format: #rrggbb, given "
                    + failval);
        int rgb = Integer.parseInt(failval, 16);
        int blue = rgb & 0xff;
        rgb >>= 8;
        int green = rgb & 0xff;
        rgb >>= 8;
        int red = rgb & 0xff;
        // rgb >>= 8;
        // int alpha = rgb & 0xff;
        return new RGB(red, green, blue);
    }

}
