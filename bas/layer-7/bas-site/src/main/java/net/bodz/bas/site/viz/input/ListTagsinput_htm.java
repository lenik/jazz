package net.bodz.bas.site.viz.input;

import java.io.IOException;
import java.util.List;

import net.bodz.bas.c.type.SingletonUtil;
import net.bodz.bas.html.artifact.ArtifactType;
import net.bodz.bas.html.dom.IHtmlTag;
import net.bodz.bas.html.dom.tag.HtmlSelectTag;
import net.bodz.bas.html.util.FieldHtmlUtil;
import net.bodz.bas.html.viz.IHtmlHeadData;
import net.bodz.bas.html.viz.IHttpViewContext;
import net.bodz.bas.html.viz.builtin.AbstractFormInput_htm;
import net.bodz.bas.meta.decl.ItemType;
import net.bodz.bas.potato.ref.UiPropertyRef;
import net.bodz.bas.repr.form.IFieldDecl;
import net.bodz.bas.repr.viz.ViewBuilderException;
import net.bodz.bas.rtx.IOptions;
import net.bodz.bas.site.config.JQueryPlugins;
import net.bodz.bas.ui.dom1.IUiRef;

public class ListTagsinput_htm
        extends AbstractFormInput_htm<List<?>> {

    boolean objects = false;

    public ListTagsinput_htm() {
        super(List.class, "tagsinput");
    }

    @Override
    public void preview(IHttpViewContext ctx, IUiRef<List<?>> ref, IOptions options) {
        IHtmlHeadData headData = ctx.getHeadData();
        headData.addDependency(JQueryPlugins.TAGSINPUT, ArtifactType.SCRIPT);
    }

    @Override
    public IHtmlTag buildHtmlView(IHttpViewContext ctx, IHtmlTag out, UiPropertyRef<List<?>> ref, IFieldDecl fieldDecl,
            IOptions options)
            throws ViewBuilderException, IOException {
        List<?> list = ref.get();
        if (list == null) {
            out.span().text("n/a");
            return out;
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
            tagTyper = (ITagTyper<Object>) SingletonUtil.instantiateCached(typerClass);
        }

        HtmlSelectTag select = out.select();
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

        FieldHtmlUtil.apply(select, fieldDecl, options, null);
        return out;
    }

}
