package net.bodz.bas.ui.model.action;

import net.bodz.bas.io.ITreeOut;
import net.bodz.bas.io.Stdio;
import net.bodz.bas.io.impl.TreeOutImpl;

public class UiLocationTreeBuilderTest {

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
