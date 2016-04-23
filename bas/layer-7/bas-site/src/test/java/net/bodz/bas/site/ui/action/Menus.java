package net.bodz.bas.site.ui.action;

import net.bodz.bas.io.ITreeOut;
import net.bodz.bas.io.Stdio;
import net.bodz.bas.io.impl.TreeOutImpl;
import net.bodz.bas.ui.model.action.UiLocationNode;
import net.bodz.bas.ui.model.action.UiLocationTreeBuilder;

public class Menus {

    public static void main(String[] args) {
        UiLocationTreeBuilder builder = new UiLocationTreeBuilder();
        builder.buildTree(null);

        ITreeOut out = TreeOutImpl.from(Stdio.cout);
        for (UiLocationNode root : builder.getRoots()) {
            out.println("root:");
            out.enter();
            root.dump(out);
            out.leave();
        }
    }

}
