package net.bodz.lily.format.doc;

import net.bodz.bas.io.ITreeOut;

import com.vladsch.flexmark.util.ast.Document;
import com.vladsch.flexmark.util.ast.Node;

public class FlexDocDump {

    ITreeOut out;

    public FlexDocDump(ITreeOut out) {
        this.out = out;
    }

    public void dump(Document doc) {
        dumpNode(doc);
    }

    public void dumpNode(Node node) {
        // doc.getParent();
        out.println(node.getClass().getSimpleName() + ": " + node);
        out.enter();
        for (Node child : node.getChildren()) {
            dumpNode(child);
        }
        out.leave();
    }

}
