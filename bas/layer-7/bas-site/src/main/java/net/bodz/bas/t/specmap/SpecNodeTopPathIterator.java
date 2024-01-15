package net.bodz.bas.t.specmap;

import java.util.Iterator;
import java.util.List;

import net.bodz.bas.t.iterator.PrefetchedIterator;
import net.bodz.bas.t.list.ArrayStack;
import net.bodz.bas.t.list.IStack;

public class SpecNodeTopPathIterator<node_t extends ISpecNode<node_t, key_t, ?>, key_t>
        extends PrefetchedIterator<List<key_t>> {

    class Frame {
        node_t node;
        Iterator<key_t> tops;
    }

    IStack<Frame> stack = new ArrayStack<>();
    IStack<key_t> prefix = new ArrayStack<>();

    boolean pruneEmptyNode = true;
    boolean childFirst; // or "thisNodeFirst"

    public SpecNodeTopPathIterator(node_t start) {
        Frame init = new Frame();
        init.node = start;
        init.tops = start.topKeySet().iterator();
    }

    @Override
    protected List<key_t> fetch() {
        while (!stack.isEmpty()) {
            Frame frame = stack.peek();
            if (frame.tops == null) {
                frame.tops = frame.node.topKeySet().iterator();
                prefix.push(null);
            }
            if (frame.tops.hasNext()) {
                key_t key = frame.tops.next();
                node_t child = frame.node.getTop(key);
                if (child.isEmptyNode())
                    if (pruneEmptyNode)
                        continue;
                prefix.replaceTop(key);

                if (child.hasTop()) {
                    Frame childFrame = new Frame();
                    childFrame.node = child;
                    childFrame.tops = null;
                }
                return prefix;
            } else {
                stack.pop();
                prefix.pop();
            }
        }
        return end();
    }

}
