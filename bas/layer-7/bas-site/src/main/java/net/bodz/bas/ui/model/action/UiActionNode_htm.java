package net.bodz.bas.ui.model.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.bodz.bas.html.io.IHtmlOut;
import net.bodz.bas.html.io.tag.HtmlUl;
import net.bodz.bas.html.viz.AbstractHtmlViewBuilder;
import net.bodz.bas.html.viz.IHtmlViewContext;
import net.bodz.bas.http.ui.cmd.UiServletAction;
import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;
import net.bodz.bas.repr.viz.ViewBuilderException;
import net.bodz.bas.site.IBasicSiteAnchors;
import net.bodz.bas.site.artifact.ZwkArtifacts;
import net.bodz.bas.ui.dom1.IUiElement;
import net.bodz.bas.ui.dom1.IUiRef;
import net.bodz.bas.ui.dom1.UiVar;
import net.bodz.mda.xjdoc.model.IElementDoc;
import net.bodz.mda.xjdoc.model.javadoc.IXjdocAware;

public class UiActionNode_htm
        extends AbstractHtmlViewBuilder<UiActionNode>
        implements IBasicSiteAnchors {

    static final Logger logger = LoggerFactory.getLogger(UiActionNode_htm.class);

    boolean debugView = false;

    Integer minPriority;
    Integer maxPriority;

    String menuBarClass = "ZwkMenuBar p1";
    String menuClass = "ZwkMenu p1";

    public UiActionNode_htm() {
        super(UiActionNode.class);
    }

    boolean isDebugView() {
        return debugView;
    }

    @Override
    public void precompile(IHtmlViewContext ctx, IUiRef<UiActionNode> ref) {
        super.precompile(ctx, ref);
        ctx.getHeadData().addDependency(ZwkArtifacts.zwkMenus.page(1));
    }

    @Override
    public IHtmlOut buildHtmlViewStart(IHtmlViewContext ctx, IHtmlOut out, IUiRef<UiActionNode> ref)
            throws ViewBuilderException, IOException {
        UiActionNode node = ref.get();
        IUiElement element = node.getElement();

        IElementDoc xjdoc = (element instanceof IXjdocAware) ? ((IXjdocAware) element).getXjdoc() : null;

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
            if (attributeMap != null && !attributeMap.isEmpty())
                out.attrs(attributeMap);
        }

        if (!pseudo) {
            // IImageData image = element.getStyle().getImage(ImageUsage.NORMAL);
            String icon = xjdoc == null ? null : (String) xjdoc.getFirstTag("icon");
            if (icon != null) {
                if (icon.startsWith("\"")) {
                    String iconChar = icon.substring(1, icon.length() - 1);
                    out.span().class_("icon").text(iconChar);
                } else {
                    out.img().class_("icon").src(_webApp_.join(icon));
                }
            }
            out.text(element.getLabel());
        }

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
                if (!isIncluded(child))
                    continue;
                if (mergeChildren) {
                    List<UiActionNode> selection = new ArrayList<>();
                    for (UiActionNode childChild : child.getChildren())
                        if (isIncluded(childChild))
                            selection.add(childChild);

                    if (!selection.isEmpty()) {
                        for (UiActionNode childChild : selection)
                            buildHtmlView(ctx, sub, UiVar.wrap(childChild));
                        out.hr();
                    }
                } else {
                    buildHtmlView(ctx, sub, UiVar.wrap(child));
                }
            }

            if (mergeToParent)
                out.hr();
        }

        return out;
    }

    public Integer getMinPriority() {
        return minPriority;
    }

    public void setMinPriority(Integer minPriority) {
        this.minPriority = minPriority;
    }

    public Integer getMaxPriority() {
        return maxPriority;
    }

    public void setMaxPriority(Integer maxPriority) {
        this.maxPriority = maxPriority;
    }

    boolean isIncluded(UiActionNode node) {
        IUiElement element = node.getElement();
        if (minPriority != null && element.getPriority() < minPriority)
            return false;
        if (maxPriority != null && element.getPriority() > maxPriority)
            return false;
        return true;
    }

}
