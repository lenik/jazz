package net.bodz.bas.html.viz.builtin;

import java.io.IOException;
import java.util.TimeZone;

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

public class TimeZone_htm
        extends AbstractFormInput_htm<TimeZone> {

    static FieldDeclBuilder fieldDeclBuilder = new FieldDeclBuilder();

    public TimeZone_htm() {
        super(TimeZone.class);
    }

    @Override
    public IHtmlTag buildHtmlView(IHttpViewContext ctx, IHtmlTag out, UiPropertyRef<TimeZone> ref,
            IFieldDecl fieldDecl, IOptions options)
            throws ViewBuilderException, IOException {
        TimeZone value = ref.get();
        if (value == null)
            value = TimeZone.getDefault();

        HtmlSelectTag select = out.select();
        FieldHtmlUtil.apply(select, fieldDecl, options);

        for (String id : TimeZone.getAvailableIDs()) {
            TimeZone timeZone = TimeZone.getTimeZone(id);
            boolean selected = timeZone.equals(value);
            HtmlOptionTag option = select.option();
            option.value(timeZone.getID());
            option.text(timeZone.getDisplayName() + " +" + timeZone.getRawOffset() / 60000);
            option.label(timeZone.getID());
            if (selected)
                option.selected("selected");
        }
        return out;
    }

}
