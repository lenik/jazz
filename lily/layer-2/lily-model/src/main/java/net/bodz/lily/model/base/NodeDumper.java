package net.bodz.lily.model.base;

import java.util.function.BiConsumer;

import net.bodz.bas.io.ITreeOut;

public class NodeDumper<node_t extends CoNode<node_t, id_t>, id_t>
        implements
            ICoNodeVisitor<node_t> {

    ITreeOut out;
    BiConsumer<ITreeOut, node_t> nodeFormat;

    public NodeDumper(ITreeOut out) {
        this(out, NodeDumper::formatNode);
    }

    public NodeDumper(ITreeOut out, BiConsumer<ITreeOut, node_t> nodeFormat) {
        this.out = out;
        this.nodeFormat = nodeFormat;
    }

    public static <n extends CoNode<n, i>, i> NodeDumper<n, i> create(ITreeOut out) {
        return new NodeDumper<>(out);
    }

    public static <n extends CoNode<n, i>, i> NodeDumper<n, i> create(ITreeOut out,
            BiConsumer<ITreeOut, n> nodeFormat) {
        return new NodeDumper<>(out, nodeFormat);
    }

    @Override
    public boolean beginNode(node_t node) {
        // formatNode(out, node);
        nodeFormat.accept(out, node);
        return true;
    }

    @Override
    public boolean beginChildren(node_t node) {
        out.enter();
        return true;
    }

    @Override
    public void endChildren(node_t node) {
        out.leave();
    }

    public static void formatNode(ITreeOut out, CoNode<?, ?> node) {
        out.print(node.getGraphPrefix());
        out.println(node.getNodeLabel());
    }

}
