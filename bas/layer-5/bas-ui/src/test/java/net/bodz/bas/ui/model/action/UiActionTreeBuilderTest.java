package net.bodz.bas.ui.model.action;

import net.bodz.bas.io.ITreeOut;
import net.bodz.bas.io.Stdio;
import net.bodz.bas.io.impl.TreeOutImpl;

public class UiActionTreeBuilderTest {

    public static void main(String[] args) {
        UiActionTreeBuilder builder = new UiActionTreeBuilder();
        builder.buildTree(null);

        ITreeOut out = TreeOutImpl.from(Stdio.cout);
        for (UiActionNode root : builder.getRoots()) {
            out.println("root:");
            out.enter();
            root.dump(out);
            out.leave();
        }
    }

}
