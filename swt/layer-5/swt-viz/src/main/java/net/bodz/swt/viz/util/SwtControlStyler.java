package net.bodz.swt.viz.util;

import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.*;

import net.bodz.bas.err.CreateException;
import net.bodz.bas.gui.css3.ICss3StyleDeclaration;
import net.bodz.bas.gui.css3.property.VisibilityMode;
import net.bodz.bas.gui.style.FontType;
import net.bodz.bas.gui.style.IColor;
import net.bodz.bas.gui.style.IFontType;
import net.bodz.bas.gui.style.IGUIElementStyleDeclaration;
import net.bodz.bas.gui.style.IImageData;
import net.bodz.bas.gui.style.ImageUsage;
import net.bodz.swt.gui.style.FontDataFontType;
import net.bodz.swt.gui.style.SwtColors;
import net.bodz.swt.gui.style.SwtImageMapper;
import net.bodz.swt.viz.ISwtControlStyleDeclaration;

public class SwtControlStyler {

    public static void apply(Control control, IGUIElementStyleDeclaration styleDecl) {
        applyVisibility(control, styleDecl.getVisibility());
        applyEnabled(control, styleDecl.getEnabled());
        applyColor(control, styleDecl.getColor());
        applyBackColor(control, styleDecl.getBackgroundColor());
        applyFont(control, styleDecl.getFontType());
        applySize(control, styleDecl);
    }

    public static void applyVisibility(Control control, VisibilityMode visibilityMode) {
        if (visibilityMode != null)
            control.setVisible(visibilityMode == VisibilityMode.visible);
    }

    public static void applyEnabled(Control control, Boolean enabled) {
        if (enabled != null)
            control.setEnabled(enabled);
    }

    public static void applyColor(Control control, IColor color) {
        if (color != null)
            control.setForeground(SwtColors.convert(color, control.getDisplay()));
    }

    public static void applyBackColor(Control control, IColor backColor) {
        if (backColor != null)
            control.setBackground(SwtColors.convert(backColor, control.getDisplay()));
    }

    public static void applyFont(Control control, ICss3StyleDeclaration styleDecl) {
        IFontType fontType = new FontType(styleDecl);
        applyFont(control, fontType);

        // styleDecl.getTextTransform(); ...
    }

    public static void applyFont(Control control, IFontType fontType) {
        if (fontType != null) {
            FontData fontData = FontDataFontType.convert(fontType);
            Font font = new Font(control.getDisplay(), fontData);
            control.setFont(font);
        }
    }

    public static void applySize(Control control, IGUIElementStyleDeclaration styleDecl) {
        Point size = SizeResolver.resolveSize(styleDecl, control);
        if (size != null)
            control.setSize(size);
    }

    public static void applyAuto(Widget widget, ISwtControlStyleDeclaration styleDecl)
            throws InjectException {
        if (widget instanceof Item)
            apply((Item) widget, styleDecl);
        else if (widget instanceof Menu)
            apply((Menu) widget, styleDecl);
        else if (widget instanceof ScrollBar)
            apply((ScrollBar) widget, styleDecl);
        else if (widget instanceof ToolTip)
            apply((ToolTip) widget, styleDecl);
    }

    /**
     * @param item
     *            SWT item, can be {@link ToolItem}, {@link MenuItem}, {@link TreeItem}, etc.
     */
    public static void apply(Item item, IGUIElementStyleDeclaration styleDecl)
            throws InjectException {
        Device display = item.getDisplay();

        try {
            IImageData icon = styleDecl.getImage(ImageUsage.NORMAL);
            if (icon != null)
                item.setImage(SwtImageMapper.convert(display, icon));

            if (item instanceof MenuItem) {
                // MenuItem menuItem = (MenuItem) item;
                // do nothing

            } else if (item instanceof ToolItem) {
                ToolItem toolItem = (ToolItem) item;

                IImageData hoverImage = styleDecl.getImage(ImageUsage.HOVER);
                if (hoverImage != null)
                    toolItem.setHotImage(SwtImageMapper.convert(display, hoverImage));

                IImageData disabledImage = styleDecl.getImage(ImageUsage.DISABLED);
                if (disabledImage != null)
                    toolItem.setDisabledImage(SwtImageMapper.convert(display, disabledImage));

                // toolItem.setToolTipText(doc);

            } else if (item instanceof ExpandItem) {
                // ExpandItem expandItem = (ExpandItem) item;
                // Point resize = expandItem.getControl().computeSize(0, 0);
                // expandItem.setHeight(resize.y);
            }

            // bind the item action
        } catch (CreateException e) {
            throw new InjectException(e);
        }
    }

    public static void apply(Menu menu, IGUIElementStyleDeclaration style) {
        if (style.getVisibility() != null)
            switch (style.getVisibility()) {
            case visible:
                menu.setVisible(true);
                break;
            case hidden:
                menu.setVisible(false);
                break;
            default:
            }

        if (style.getEnabled() != null)
            menu.setEnabled(style.getEnabled());
    }

    public static void apply(ScrollBar bar, IGUIElementStyleDeclaration style) {
        if (style.getVisibility() != null)
            switch (style.getVisibility()) {
            case visible:
                bar.setVisible(true);
                break;
            case hidden:
                bar.setVisible(false);
                break;
            default:
            }

        if (style.getEnabled() != null)
            bar.setEnabled(style.getEnabled());
    }

    public static void apply(ToolTip tooltip, IGUIElementStyleDeclaration style) {
        if (style.getVisibility() != null)
            switch (style.getVisibility()) {
            case visible:
                tooltip.setVisible(true);
                break;
            case hidden:
                tooltip.setVisible(false);
                break;
            case collapse:
                break;
            default:
            }
    }

}
