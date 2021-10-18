package net.bodz.bas.html.viz.builtin;

import java.io.IOException;
import java.util.Locale;

import net.bodz.bas.html.io.IHtmlOut;
import net.bodz.bas.html.io.tag.HtmlOption;
import net.bodz.bas.html.io.tag.HtmlSelect;
import net.bodz.bas.html.util.FieldDeclToHtml;
import net.bodz.bas.html.viz.IHtmlViewContext;
import net.bodz.bas.potato.ref.UiPropertyRef;
import net.bodz.bas.repr.form.IFieldDecl;
import net.bodz.bas.repr.viz.ViewBuilderException;

public class Locale_htm
        extends AbstractFormInput_htm<Locale> {

    public Locale_htm() {
        super(Locale.class);
    }

    @Override
    public void buildHtmlView(IHtmlViewContext ctx, IHtmlOut out, UiPropertyRef<Locale> ref, IFieldDecl fieldDecl)
            throws ViewBuilderException, IOException {
        Locale value = ref.get();
        if (value == null)
            value = Locale.getDefault();

        HtmlSelect select = out.select().class_("noprint");
        FieldDeclToHtml.apply(select, fieldDecl);

        for (Locale locale : Locale.getAvailableLocales()) {
            boolean selected = locale.equals(value);
            HtmlOption option = select.option();
            option.value(locale.getLanguage());
            option.label(locale.getLanguage());
            if (selected)
                option.selected("selected");
            option.text(locale.getDisplayName());
        }

        String str = value.getLanguage() + " - " + value.getDisplayName();
        out.span().class_("print").text(str);
    }

}
