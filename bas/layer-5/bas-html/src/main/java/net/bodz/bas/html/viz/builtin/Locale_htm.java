package net.bodz.bas.html.viz.builtin;

import java.io.IOException;
import java.util.Locale;

import net.bodz.bas.html.dom.IHtmlTag;
import net.bodz.bas.html.dom.tag.HtmlOptionTag;
import net.bodz.bas.html.dom.tag.HtmlSelectTag;
import net.bodz.bas.html.util.FieldHtmlUtil;
import net.bodz.bas.html.viz.IHttpViewContext;
import net.bodz.bas.potato.ref.UiPropertyRef;
import net.bodz.bas.repr.form.FieldDeclBuilder;
import net.bodz.bas.repr.form.IFieldDecl;
import net.bodz.bas.repr.viz.ViewBuilderException;
import net.bodz.bas.rtx.IOptions;

public class Locale_htm
        extends AbstractFormInput_htm<Locale> {

    static FieldDeclBuilder fieldDeclBuilder = new FieldDeclBuilder();

    public Locale_htm() {
        super(Locale.class);
    }

    @Override
    public IHtmlTag buildHtmlView(IHttpViewContext ctx, IHtmlTag out, UiPropertyRef<Locale> ref, IFieldDecl fieldDecl,
            IOptions options)
            throws ViewBuilderException, IOException {
        Locale value = ref.get();
        if (value == null)
            value = Locale.getDefault();

        HtmlSelectTag select = out.select();
        FieldHtmlUtil.apply(select, fieldDecl, options);

        for (Locale locale : Locale.getAvailableLocales()) {
            boolean selected = locale.equals(value);
            HtmlOptionTag option = select.option();
            option.value(locale.getLanguage());
            option.text(locale.getDisplayName());
            option.label(locale.getLanguage());
            if (selected)
                option.selected("selected");
        }
        return out;
    }

}
