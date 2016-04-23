package net.bodz.bas.site.ui.action;

import net.bodz.bas.html.io.HtmlDoc;
import net.bodz.bas.html.io.HtmlOutputFormat;
import net.bodz.bas.html.io.tag.HtmlDiv;
import net.bodz.bas.io.ITreeOut;
import net.bodz.bas.io.Stdio;
import net.bodz.bas.io.impl.TreeOutImpl;
import net.bodz.bas.ui.dom1.UiVar;
import net.bodz.bas.ui.model.action.UiActionNode;
import net.bodz.bas.ui.model.action.UiActionNode_htm;
import net.bodz.bas.ui.model.action.UiActionTreeBuilder;

public class Menus {

    public static void main(String[] args)
            throws Exception {
        UiActionTreeBuilder builder = new UiActionTreeBuilder();
        builder.buildTree(null);

        ITreeOut _out = TreeOutImpl.from(Stdio.cout);
        HtmlDoc doc = new HtmlDoc(_out, HtmlOutputFormat.DEFAULT);

        for (UiActionNode root : builder.getRoots()) {
            _out.println("root:");
            _out.enter();
            root.dump(_out);
            _out.leave();

            UiActionNode_htm htm = new UiActionNode_htm();
            HtmlDiv out = new HtmlDiv(doc);
            htm.buildHtmlView(null, out, UiVar.wrap(root));
        }
    }

}
