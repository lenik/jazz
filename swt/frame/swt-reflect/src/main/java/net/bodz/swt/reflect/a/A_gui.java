package net.bodz.swt.reflect.a;

import static net.bodz.swt.reflect.nls.GUINLS.GUINLS;

import java.lang.reflect.AnnotatedElement;
import java.util.HashMap;
import java.util.Map;

import javax.swing.Icon;

import net.bodz.bas.c.type.SingletonUtil;
import net.bodz.bas.err.CreateException;
import net.bodz.bas.err.IllegalUsageError;
import net.bodz.bas.jvm.stack.Caller;
import net.bodz.bas.model.IFactory;
import net.bodz.bas.ui.a.Font;
import net.bodz.bas.ui.a.PreferredSize;
import net.bodz.swt.util.SWTResources;

import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.RGB;

public class A_gui
        extends net.bodz.bas.ui.a.A_ui {

    public static ImageData getIcon(AnnotatedElement aobject) {
        Icon aicon = aobject.getAnnotation(Icon.class);
        if (aicon == null)
            return null;
        IFactory<?> iconFactory = getIconFactory(aicon);
        if (iconFactory == null) { // load resource-path
            String[] resPaths = aicon.value();
            if (resPaths.length == 0)
                return null;
            String resPath = resPaths[0];
            ClassLoader loader = Caller.getCallerClassLoader(1);
            ImageData image = SWTResources.getImageDataRes(loader, resPath);
            return image;
        }
        try {
            ImageData imageData = (ImageData) iconFactory.create();
            return imageData;
        } catch (CreateException e) {
            throw new RuntimeException(e);
        }
    }

    public static IFactory<?> getFontFactory(Font afont) {
        if (afont == null)
            return null;
        Class<? extends IFactory<?>> factoryClass = afont.factory();
        if (factoryClass == IFactory.class) {
            String name = afont.name();
            int height = afont.height();
            int style = afont.style();
            FontData fontData = new FontData(name, height, style);
            return new IFactory.Static<FontData>(fontData);
        }
        try {
            return SingletonUtil.getClassInstance(factoryClass);
        } catch (CreateException e) {
            throw new RuntimeException(e);
        }
    }

    public static FontData getFont(AnnotatedElement aobject) {
        Font nfont = aobject.getAnnotation(Font.class);
        if (nfont == null)
            return null;
        IFactory<?> fontFactory = getFontFactory(nfont);
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

    static Map<String, RGB> colorNames;
    static {
        colorNames = new HashMap<String, RGB>();
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
            throw new IllegalUsageError(GUINLS.getString("A_gui.badColor") + colorExp);
        String failval = colorExp.substring(sharp + 1);
        if (failval.length() != 6)
            throw new IllegalUsageError(GUINLS.getString("A_gui.colorFormat") + failval);
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
