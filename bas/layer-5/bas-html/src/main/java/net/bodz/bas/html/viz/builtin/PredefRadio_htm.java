package net.bodz.bas.html.viz.builtin;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Map;
import java.util.Map.Entry;

import net.bodz.bas.html.io.IHtmlOut;
import net.bodz.bas.html.io.tag.HtmlDiv;
import net.bodz.bas.html.io.tag.HtmlInput;
import net.bodz.bas.html.io.tag.HtmlLabel;
import net.bodz.bas.html.meta.Radio;
import net.bodz.bas.html.util.FieldDeclToHtml;
import net.bodz.bas.html.viz.IHtmlViewContext;
import net.bodz.bas.potato.ref.UiPropertyRef;
import net.bodz.bas.repr.form.IFormProperty;
import net.bodz.bas.repr.viz.ViewBuilderException;
import net.bodz.bas.t.predef.Predef;
import net.bodz.bas.t.predef.PredefMetadata;

@Radio
public class PredefRadio_htm
        extends AbstractFormInput_htm<Predef<?, ?>> {

    public PredefRadio_htm() {
        super(Predef.class);
    }

    @Override
    public void buildHtmlView(IHtmlViewContext ctx, IHtmlOut out, UiPropertyRef<Predef<?, ?>> ref, IFormProperty fieldDecl)
            throws ViewBuilderException, IOException {
        Predef<?, ?> predef = ref.get();
        PredefMetadata<?, ?> metadata;
        if (predef != null)
            metadata = predef.getMetadata();
        else {
            Class<?> predefType = ref.getValueType();
            try {
                Field metadataField = predefType.getDeclaredField("METADATA");
                metadata = (PredefMetadata<?, ?>) metadataField.get(null);
            } catch (ReflectiveOperationException e) {
                throw new ViewBuilderException(e.getMessage(), e);
            }
        }

        HtmlDiv div = out.div().class_("btn-group");
        div.attr("data-toggle", "buttons");
        Map<String, ?> nameMap = metadata.getNameMap();
        for (Entry<String, ?> entry : nameMap.entrySet()) {
            String key = entry.getKey();
            Predef<?, ?> value = (Predef<?, ?>) entry.getValue();

            String class_ = "btn btn-xs btn-default";
            if (predef == value)
                class_ += " active";

            HtmlLabel labelTag = div.label().class_("btn");
            labelTag.class_(class_);

            HtmlInput radio = labelTag.input().type("radio");
            FieldDeclToHtml.apply(radio, fieldDecl);

            if (predef == value)
                radio.checked("checked");

            radio.value(key);
            radio.text(value.getLabel());
        }
    }

}
