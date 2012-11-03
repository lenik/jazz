package net.bodz.swt.gui.a;

import java.lang.reflect.AnnotatedElement;

import org.eclipse.swt.graphics.FontData;

import net.bodz.bas.c.type.SingletonUtil;
import net.bodz.bas.err.CreateException;
import net.bodz.bas.gui.mda.Font;
import net.bodz.bas.model.IFactory;

public class FontAnnotation {

    public static IFactory<?> getFontFactory(Font _font) {
        if (_font == null)
            return null;
        Class<? extends IFactory<?>> factoryClass = _font.factory();
        if (factoryClass == IFactory.class) {
            String name = _font.name();
            int height = _font.height();
            int style = _font.style();
            FontData fontData = new FontData(name, height, style);
            return new IFactory.Static<FontData>(fontData);
        }
        try {
            return SingletonUtil.getClassInstance(factoryClass);
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    public static FontData getFont(AnnotatedElement aobject) {
        Font _font = aobject.getAnnotation(Font.class);
        if (_font == null)
            return null;
        IFactory<?> fontFactory = getFontFactory(_font);
        try {
            FontData fontData = (FontData) fontFactory.create();
            return fontData;
        } catch (CreateException e) {
            throw new RuntimeException(e);
        }
    }

}
