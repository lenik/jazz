package net.bodz.bas.html.viz.builtin;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Map;
import java.util.Map.Entry;

import net.bodz.bas.html.io.IHtmlOut;
import net.bodz.bas.html.io.tag.HtmlOption;
import net.bodz.bas.html.io.tag.HtmlSelect;
import net.bodz.bas.html.util.FieldDeclToHtml;
import net.bodz.bas.html.viz.IHtmlViewContext;
import net.bodz.bas.potato.ref.UiPropertyRef;
import net.bodz.bas.repr.form.IFormProperty;
import net.bodz.bas.repr.viz.ViewBuilderException;
import net.bodz.bas.t.predef.Predef;
import net.bodz.bas.t.predef.PredefMetadata;

public class PredefSelect_htm
        extends AbstractFormInput_htm<Predef<?, ?>> {

    public PredefSelect_htm() {
        super(Predef.class); // TODO , "select");
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

        HtmlSelect select = out.select();
        FieldDeclToHtml.apply(select, fieldDecl);

        Map<String, ?> nameMap = metadata.getNameMap();
        for (Entry<String, ?> entry : nameMap.entrySet()) {
            String key = entry.getKey();
            Predef<?, ?> value = (Predef<?, ?>) entry.getValue();
            HtmlOption option = select.option();
            option.label(value.getName());
            option.value(key);
            if (predef == value)
                option.selected("selected");
            option.text(value.getLabel());
        }
    }

}
