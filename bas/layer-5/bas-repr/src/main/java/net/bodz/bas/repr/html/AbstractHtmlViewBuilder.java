package net.bodz.bas.repr.html;

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
        return buildHtmlView(ctx, entry, options);
    }

    @Override
    public final IHtmlOutputContext buildHtmlView(IHtmlOutputContext ctx, IRefEntry<T> entry)
            throws ViewBuilderException {
        return buildHtmlView(ctx, entry, IOptions.NULL);
    }

    protected void makeOutmostTag(IHtmlOutputContext ctx, String tagName, IGUIElementStyleDeclaration style) {
        IHtmlOut out = ctx.getOut();
        out.startTag(tagName);

        Border border = style.getBorder();
        if (border != null) {
            border.getWidth();
            border.getColor();

            style.getBorderTop();
            style.getBorderRight();
            style.getBorderBottom();
            style.getBorderLeft();
        }

        out.endTag();
    }

}
