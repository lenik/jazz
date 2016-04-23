package net.bodz.bas.ui.model.action;

import java.io.IOException;
import java.util.Map;

import net.bodz.bas.html.io.IHtmlOut;
import net.bodz.bas.html.io.tag.HtmlUl;
import net.bodz.bas.html.viz.AbstractHtmlViewBuilder;
import net.bodz.bas.html.viz.IHtmlViewContext;
import net.bodz.bas.http.ui.cmd.UiServletAction;
import net.bodz.bas.repr.viz.ViewBuilderException;
import net.bodz.bas.ui.dom1.IUiElement;
import net.bodz.bas.ui.dom1.IUiRef;
import net.bodz.bas.ui.dom1.UiVar;
import net.bodz.bas.ui.model.action.UiActionNode;
import net.bodz.bas.ui.model.action.UiLocationDecl;

public class UiActionNode_htm
        extends AbstractHtmlViewBuilder<UiActionNode> {

    public UiActionNode_htm() {
        super(UiActionNode.class);
    }

    @Override
    public IHtmlOut buildHtmlViewStart(IHtmlViewContext ctx, IHtmlOut out, IUiRef<UiActionNode> ref)
            throws ViewBuilderException, IOException {
        UiActionNode node = ref.get();
        IUiElement element = node.getElement();

        if (node.isRoot()) {
            // root node
        } else {
            out = out.li();
            out.text(element.getLabel());
        }

        boolean mergeChildren = false;
        boolean mergeToParent = false;

        if (element instanceof UiLocationDecl) {
            UiLocationDecl decl = (UiLocationDecl) element;
            mergeChildren = decl.isMergeChildren();
            mergeToParent = decl.isMergeToParent();
        }

        if (element instanceof UiServletAction) {
            UiServletAction action = (UiServletAction) element;
            Object context = node.getContext();
            for (String scriptId : action.getScriptIds()) {
                String script = action.getScript(scriptId, context);
                if (script != null)
                    out.attr(scriptId, script);
            }
            Map<String, String> attributeMap = action.getAttributeMap();
            if (attributeMap != null)
                out.attrs(attributeMap);
        }

        if (!node.isEmpty()) {
            IHtmlOut sub = out;
            if (!mergeToParent) {
                HtmlUl ul = out.ul();
                if (node.isRoot())
                    ul.class_("ZwkMenuBar p1 -top");
                else
                    ul.class_("ZwkMenu p1");
                sub = ul;
            }

            for (UiActionNode child : node.getChildren()) {
                if (mergeChildren) {
                    for (UiActionNode childChild : child.getChildren())
                        buildHtmlView(ctx, sub, UiVar.wrap(childChild));
                } else {
                    buildHtmlView(ctx, sub, UiVar.wrap(child));
                }
            }
        }

        return out;
    }

}
