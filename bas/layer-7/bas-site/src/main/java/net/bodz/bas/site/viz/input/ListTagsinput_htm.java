package net.bodz.bas.site.viz.input;

import java.io.IOException;
import java.util.List;

import net.bodz.bas.c.type.SingletonUtil;
import net.bodz.bas.html.dom.IHtmlHeadData;
import net.bodz.bas.html.io.IHtmlOut;
import net.bodz.bas.html.io.tag.HtmlSelect;
import net.bodz.bas.html.util.FieldDeclToHtml;
import net.bodz.bas.html.viz.IHtmlViewContext;
import net.bodz.bas.html.viz.builtin.AbstractFormInput_htm;
import net.bodz.bas.meta.decl.ItemType;
import net.bodz.bas.potato.ref.UiPropertyRef;
import net.bodz.bas.repr.form.IFormProperty;
import net.bodz.bas.repr.meta.Face;
import net.bodz.bas.repr.viz.ViewBuilderException;
import net.bodz.bas.site.artifact.LibJsArtifacts;
import net.bodz.bas.ui.dom1.IUiRef;

@Face("tagsinput")
public class ListTagsinput_htm
        extends AbstractFormInput_htm<List<?>> {

    boolean objects = false;

    public ListTagsinput_htm() {
        super(List.class);
    }

    @Override
    public void precompile(IHtmlViewContext ctx, IUiRef<List<?>> ref) {
        IHtmlHeadData headData = ctx.getHeadData();
        headData.addDependency(LibJsArtifacts.tagsinput);
    }

    @Override
    public void buildHtmlView(IHtmlViewContext ctx, IHtmlOut out, UiPropertyRef<List<?>> ref, IFormProperty fieldDecl)
            throws ViewBuilderException, IOException {
        List<?> list = ref.get();
        if (list == null) {
            out.span().text("n/a");
            return;
        }

        ITagTyper<Object> tagTyper = ITagTyper.DEFAULT;

        Tagsinput aTagsinput = ref.getAnnotation(Tagsinput.class);
        if (aTagsinput == null) {
            ItemType aItemType = ref.getAnnotation(ItemType.class);
            if (aItemType == null) {
                Class<?> valueType = ref.getValueType();
                aItemType = valueType.getAnnotation(ItemType.class);
            }
            if (aItemType != null) {
                Class<?> itemType = aItemType.value();
                aTagsinput = itemType.getAnnotation(Tagsinput.class);
            }
        }
        if (aTagsinput != null) {
            Class<? extends ITagTyper<?>> typerClass = aTagsinput.typer();
            @SuppressWarnings("unchecked")
            ITagTyper<Object> instance =  (ITagTyper<Object>) SingletonUtil.instantiateCached(typerClass);
            tagTyper = instance;
        }

        HtmlSelect select = out.select();
        select.multiple("multiple");

        if (objects) {
            select.attr("data-role", "tagsinput-obj");
        } else {
            select.attr("data-role", "tagsinput");
        }

        int n = list.size();
        for (int i = 0; i < n; i++) {
            Object item = list.get(i);
            String value = tagTyper.getTagValue(item);
            String text = tagTyper.getTagText(item);
            if (objects)
                select.option().value(value).text(text);
            else
                select.option().value(text).text(text);
        }

        FieldDeclToHtml.apply(select, fieldDecl);
    }

}
