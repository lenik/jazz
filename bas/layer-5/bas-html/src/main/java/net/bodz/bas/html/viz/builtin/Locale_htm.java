package net.bodz.bas.html.viz.builtin;

import java.io.IOException;
import java.util.Locale;

import net.bodz.bas.html.dom.IHtmlTag;
import net.bodz.bas.html.dom.tag.HtmlOptionTag;
import net.bodz.bas.html.dom.tag.HtmlSelectTag;
import net.bodz.bas.html.util.FieldHtmlUtil;
import net.bodz.bas.html.viz.IHtmlViewContext;
import net.bodz.bas.potato.ref.UiPropertyRef;
import net.bodz.bas.repr.form.FieldDeclBuilder;
import net.bodz.bas.repr.form.IFieldDecl;
import net.bodz.bas.repr.viz.ViewBuilderException;

public class Locale_htm
        extends AbstractFormInput_htm<Locale> {

    static FieldDeclBuilder fieldDeclBuilder = new FieldDeclBuilder();

    public Locale_htm() {
        super(Locale.class);
    }

    @Override
    public void buildHtmlView(IHtmlViewContext ctx, IHtmlTag out, UiPropertyRef<Locale> ref, IFieldDecl fieldDecl)
            throws ViewBuilderException, IOException {
        Locale value = ref.get();
        if (value == null)
            value = Locale.getDefault();

        HtmlSelectTag select = out.select().class_("noprint");
        FieldHtmlUtil.apply(select, fieldDecl);

        for (Locale locale : Locale.getAvailableLocales()) {
            boolean selected = locale.equals(value);
            HtmlOptionTag option = select.option();
            option.value(locale.getLanguage());
            option.text(locale.getDisplayName());
            option.label(locale.getLanguage());
            if (selected)
                option.selected("selected");
        }

        String str = value.getLanguage() + " - " + value.getDisplayName();
        out.span().class_("print").text(str);
    }

}
