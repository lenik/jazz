package net.bodz.bas.html.viz.builtin;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Map;
import java.util.Map.Entry;

import net.bodz.bas.html.dom.IHtmlTag;
import net.bodz.bas.html.dom.tag.HtmlDivTag;
import net.bodz.bas.html.dom.tag.HtmlInputTag;
import net.bodz.bas.html.dom.tag.HtmlLabelTag;
import net.bodz.bas.html.util.FieldHtmlUtil;
import net.bodz.bas.html.viz.IHttpViewContext;
import net.bodz.bas.potato.ref.UiPropertyRef;
import net.bodz.bas.repr.form.IFieldDecl;
import net.bodz.bas.repr.viz.ViewBuilderException;
import net.bodz.bas.rtx.IOptions;
import net.bodz.bas.t.predef.Predef;
import net.bodz.bas.t.predef.PredefMetadata;

public class PredefRadio_htm
        extends AbstractFormInput_htm<Predef<?, ?>> {

    public PredefRadio_htm() {
        super(Predef.class, "radio");
    }

    @Override
    public IHtmlTag buildHtmlView(IHttpViewContext ctx, IHtmlTag out, UiPropertyRef<Predef<?, ?>> ref,
            IFieldDecl fieldDecl, IOptions options)
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

        HtmlDivTag div = out.div().class_("btn-group");
        div.attr("data-toggle", "buttons");
        Map<String, ?> nameMap = metadata.getNameMap();
        for (Entry<String, ?> entry : nameMap.entrySet()) {
            String key = entry.getKey();
            Predef<?, ?> value = (Predef<?, ?>) entry.getValue();

            String class_ = "btn btn-xs btn-default";
            if (predef == value)
                class_ += " active";

            HtmlLabelTag labelTag = div.label().class_("btn");
            labelTag.class_(class_);

            HtmlInputTag radio = labelTag.input().type("radio");
            if (predef == value)
                radio.checked("checked");

            radio.value(key);
            radio.text(value.getLabel());

            FieldHtmlUtil.apply(radio, fieldDecl, options);
        }
        return out;
    }

}
