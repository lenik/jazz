package net.bodz.bas.html.viz.builtin;

import java.io.IOException;
import java.util.TimeZone;

import net.bodz.bas.html.io.IHtmlOut;
import net.bodz.bas.html.io.tag.HtmlOption;
import net.bodz.bas.html.io.tag.HtmlSelect;
import net.bodz.bas.html.util.FieldDeclToHtml;
import net.bodz.bas.html.viz.IHtmlViewContext;
import net.bodz.bas.potato.ref.UiPropertyRef;
import net.bodz.bas.repr.form.IFormProperty;
import net.bodz.bas.repr.viz.ViewBuilderException;

public class TimeZone_htm
        extends AbstractFormInput_htm<TimeZone> {

    public TimeZone_htm() {
        super(TimeZone.class);
    }

    @Override
    public void buildHtmlView(IHtmlViewContext ctx, IHtmlOut out, UiPropertyRef<TimeZone> ref, IFormProperty fieldDecl)
            throws ViewBuilderException, IOException {
        TimeZone value = ref.get();
        if (value == null)
            value = TimeZone.getDefault();

        HtmlSelect select = out.select().class_("noprint");
        FieldDeclToHtml.apply(select, fieldDecl);

        for (String id : TimeZone.getAvailableIDs()) {
            TimeZone timeZone = TimeZone.getTimeZone(id);
            boolean selected = timeZone.equals(value);
            HtmlOption option = select.option();
            option.value(timeZone.getID());
            option.label(timeZone.getID());
            option.text(timeZone.getDisplayName() + " +" + timeZone.getRawOffset() / 60000);
            if (selected)
                option.selected("selected");
        }

        String str = value.getID() + " - " + value.getDisplayName();
        out.span().class_("print").text(str);
    }

}
