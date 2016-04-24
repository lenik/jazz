package net.bodz.bas.ui.model.action;

import java.io.IOException;
import java.util.Map;

import net.bodz.bas.html.io.IHtmlOut;
import net.bodz.bas.html.io.tag.HtmlUl;
import net.bodz.bas.html.viz.AbstractHtmlViewBuilder;
import net.bodz.bas.html.viz.IHtmlViewContext;
import net.bodz.bas.http.ui.cmd.UiServletAction;
import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;
import net.bodz.bas.repr.viz.ViewBuilderException;
import net.bodz.bas.ui.dom1.IUiElement;
import net.bodz.bas.ui.dom1.IUiRef;
import net.bodz.bas.ui.dom1.UiVar;

public class UiActionNode_htm
        extends AbstractHtmlViewBuilder<UiActionNode> {

    static final Logger logger = LoggerFactory.getLogger(UiActionNode_htm.class);

    boolean debugView = false;
    String menuBarClass = "ZwkMenuBar p1";
    String menuClass = "ZwkMenu p1";

    public UiActionNode_htm() {
        super(UiActionNode.class);
    }

    boolean isDebugView() {
        return debugView;
    }

    @Override
    public IHtmlOut buildHtmlViewStart(IHtmlViewContext ctx, IHtmlOut out, IUiRef<UiActionNode> ref)
            throws ViewBuilderException, IOException {
        UiActionNode node = ref.get();
        IUiElement element = node.getElement();

        boolean mergeChildren = false;
        boolean mergeToParent = false;

        if (element instanceof UiLocationDecl) {
            UiLocationDecl decl = (UiLocationDecl) element;
            mergeChildren = decl.isMergeChildren();
            mergeToParent = decl.isMergeToParent();
        }

        boolean pseudo = node.isRoot() || mergeToParent;
        if (!pseudo) {
            // root node doesn't have label, image, at all.
            out = out.li();

            if (isDebugView()) {
                out.attr("j:class", element.getClass().getSimpleName());
            }
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

        if (!pseudo)
            out.text(element.getLabel());

        if (!node.isEmpty()) {
            IHtmlOut sub = out;
            if (mergeToParent) {
            } else {
                HtmlUl ul = out.ul();
                if (node.isRoot())
                    ul.class_(menuBarClass + " -top");
                else
                    ul.class_(menuClass);
                sub = ul;
            }

            for (UiActionNode child : node.getChildren()) {
                if (mergeChildren) {
                    for (UiActionNode childChild : child.getChildren())
                        buildHtmlView(ctx, sub, UiVar.wrap(childChild));
                    out.hr();
                } else {
                    buildHtmlView(ctx, sub, UiVar.wrap(child));
                }
            }

            if (mergeToParent)
                out.hr();
        }

        return out;
    }

}
