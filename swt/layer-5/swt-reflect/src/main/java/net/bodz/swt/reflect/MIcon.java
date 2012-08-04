package net.bodz.swt.reflect;

import java.lang.reflect.Modifier;

import org.eclipse.swt.graphics.Image;

public class MIcon {

    public static final int DEFAULT = 0;
    public static final int PUBLIC = 1;
    public static final int PROTECTED = 2;
    public static final int PRIVATE = 3;

    private String[] icons;

    public MIcon(String... icons) {
        this.icons = icons;
    }

    public String get() {
        return icons[0];
    }

    public String get(int index) {
        return icons[index >= icons.length ? 0 : index];
    }

    private int modIndex(int modifiers) {
        switch (modifiers & (Modifier.PUBLIC | Modifier.PROTECTED | Modifier.PRIVATE)) {
        case Modifier.PUBLIC:
            return PUBLIC;
        case Modifier.PROTECTED:
            return PROTECTED;
        case Modifier.PRIVATE:
            return PRIVATE;
        default:
            return DEFAULT;
        }
    }

    public String getMod(int modifiers) {
        return get(modIndex(modifiers));
    }

    public Image addOverlay(Image image, Image overlay) {
        return image;
    }

    private static final int visibility;
    private Image[] visibilityOverlays;
    static {
        visibility = Modifier.PUBLIC | Modifier.PROTECTED | Modifier.PRIVATE;
    }

    public Image addModifiers(Image image, int modifiers) {
        switch (modifiers & visibility) {
        case Modifier.PUBLIC:
            return visibilityOverlays[0];
        case Modifier.PROTECTED:
            return visibilityOverlays[1];
        case Modifier.PRIVATE:
            return visibilityOverlays[2];
        default:
            return visibilityOverlays[3];
        }
    }

}
