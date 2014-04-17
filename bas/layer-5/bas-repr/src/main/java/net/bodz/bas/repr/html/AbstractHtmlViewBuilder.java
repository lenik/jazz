package net.bodz.bas.repr.html;

import java.io.IOException;

import net.bodz.bas.gui.css3.Border;
import net.bodz.bas.gui.style.IGUIElementStyleDeclaration;
import net.bodz.bas.io.html.IHtmlOut;
import net.bodz.bas.potato.ref.IRefEntry;
import net.bodz.bas.repr.viz.AbstractViewBuilder;
import net.bodz.bas.repr.viz.ViewBuilderException;
import net.bodz.bas.rtx.IOptions;

public abstract class AbstractHtmlViewBuilder<T>
        extends AbstractViewBuilder<T>
        implements IHtmlViewBuilder<T> {

    public AbstractHtmlViewBuilder(Class<?> valueClass, String... supportedFeatures) {
        super(valueClass, supportedFeatures);
    }

    @Override
    public boolean isOrigin(T value) {
        return false;
    }

    @Override
    public void getRequirements(IRequirements requires) {
    }

    @Override
    public final Object buildView(Object _ctx, IRefEntry<T> entry, IOptions options)
            throws ViewBuilderException {
        IHtmlOutputContext ctx = (IHtmlOutputContext) _ctx;
        try {
            return buildHtmlView(ctx, entry, options);
        } catch (IOException e) {
            throw new ViewBuilderException(e.getMessage(), e);
        }
    }

    @Override
    public final IHtmlOutputContext buildHtmlView(IHtmlOutputContext ctx, IRefEntry<T> entry)
            throws ViewBuilderException {
        try {
            return buildHtmlView(ctx, entry, IOptions.NULL);
        } catch (IOException e) {
            throw new ViewBuilderException(e.getMessage(), e);
        }
    }

    protected void makeOutmostTag(IHtmlOutputContext ctx, String tagName, IGUIElementStyleDeclaration style) {
        IHtmlOut out = ctx.getOut();
        out.startTagBegin(tagName);

        Border border = style.getBorder();
        if (border != null) {
            border.getWidth();
            border.getColor();

            style.getBorderTop();
            style.getBorderRight();
            style.getBorderBottom();
            style.getBorderLeft();
        }

        out.startTagEnd(false);
    }

}
