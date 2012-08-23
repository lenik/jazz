package net.bodz.swt.gui.a;

import java.lang.reflect.AnnotatedElement;

import org.eclipse.swt.graphics.ImageData;

import net.bodz.bas.err.CreateException;
import net.bodz.bas.gui.a.Icon;
import net.bodz.bas.jvm.stack.Caller;
import net.bodz.bas.model.IFactory;
import net.bodz.swt.c.resources.SWTResources;

public class IconAnnotation {

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

}
