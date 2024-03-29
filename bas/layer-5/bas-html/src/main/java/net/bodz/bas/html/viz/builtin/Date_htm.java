package net.bodz.bas.html.viz.builtin;

import java.io.IOException;
import java.util.Date;

import net.bodz.bas.c.java.util.DateTimes;
import net.bodz.bas.c.type.TypeId;
import net.bodz.bas.c.type.TypeKind;
import net.bodz.bas.html.io.IHtmlOut;
import net.bodz.bas.html.io.tag.HtmlInput;
import net.bodz.bas.html.util.FieldDeclToHtml;
import net.bodz.bas.html.viz.IHtmlViewContext;
import net.bodz.bas.potato.ref.UiPropertyRef;
import net.bodz.bas.repr.form.IFormProperty;
import net.bodz.bas.repr.viz.ViewBuilderException;

public class Date_htm
        extends AbstractFormInput_htm<Date> {

    public Date_htm() {
        super(Date.class);
    }

    @Override
    public void buildHtmlView(IHtmlViewContext ctx, IHtmlOut out, UiPropertyRef<Date> ref, IFormProperty fieldDecl)
            throws ViewBuilderException, IOException {
        Date value = ref.get();

        HtmlInput input = out.input().class_("noprint");
        String str = null;

        FieldDeclToHtml.apply(input, fieldDecl);

        int typeId = TypeKind.getTypeId(ref.getValueType());
        switch (typeId) {
        case TypeId.SQL_DATE:
            input.type("date");
            if (value != null) {
                str = DateTimes.ISO_LOCAL_DATE.format(//
                        DateTimes.convert((java.sql.Date) value));
                input.value(str);
            }
            break;

        case TypeId.SQL_TIME:
            input.type("time");
            if (value != null) {
                str = DateTimes.ISO_LOCAL_TIME.format(//
                        DateTimes.convert((java.sql.Time) value));
                input.value(str);
            }
            break;

        case TypeId.TIMESTAMP:
        case TypeId.DATE:
        default:
            input.type("date");
            if (value != null) {
                str = DateTimes.ISO_LOCAL_DATE.format(//
                        DateTimes.convertDate(value));
                input.value(str);
            }
            input.type("time");
            if (value != null) {
                str = DateTimes.ISO_LOCAL_TIME.format(//
                        DateTimes.convertDate(value));
                input.value(str);
            }
            str = DateTimes.ISO_LOCAL_DATE_TIME.format(//
                    DateTimes.convertDate(value));
            break;
        }

        if (str != null)
            out.span().class_("print").text(str);
    }

}
