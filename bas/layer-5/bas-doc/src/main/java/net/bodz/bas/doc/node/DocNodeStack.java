package net.bodz.bas.doc.node;

import java.util.NoSuchElementException;
import java.util.Stack;
import java.util.function.Predicate;

import net.bodz.bas.doc.attr.SectionLevel;

public class DocNodeStack {

    Stack<IDocNode> stack = new Stack<>();

    public IDocNode top() {
        if (stack.isEmpty())
            throw new IllegalStateException("no document or closed.");
        return stack.lastElement();
    }

    public boolean isEmpty() {
        return stack.isEmpty();
    }

    @SuppressWarnings("unchecked")
    public <T extends IDocNode> T push(T item) {
        return (T) stack.push(item);
    }

    public IDocNode pop() {
        return stack.pop();
    }

    public int size() {
        return stack.size();
    }

    public void clear() {
        stack.clear();
    }

    public <T extends IDocNode> T popUntil(Class<T> nodeType) {
        if (stack.isEmpty())
            throw new IllegalStateException("no document or closed.");
        int n = stack.size();
        for (int i = n - 1; i >= 0; i--) {
            IDocNode node = stack.get(i);
            if (nodeType.isInstance(node)) {
                for (int j = i + 1; j < n; j++)
                    stack.pop();
                return nodeType.cast(node);
            }
        }
        throw new NoSuchElementException("node of " + nodeType);
    }

    public IDocNode popUntil(Predicate<IDocNode> predicate) {
        if (stack.isEmpty())
            throw new IllegalStateException("no document or closed.");
        int n = stack.size();
        for (int i = n - 1; i >= 0; i--) {
            IDocNode node = stack.get(i);
            if (predicate.test(node)) {
                for (int j = i + 1; j < n; j++)
                    stack.pop();
                return node;
            }
        }
        throw new NoSuchElementException();
    }

    public DocDocument popUntilDoc() {
        return (DocDocument) popUntil((IDocNode node) -> node.isDocument());
    }

    public DocParGroup popUntilPars() {
        return (DocParGroup) popUntil((IDocNode node) -> node.havePars());
    }

    public DocRunGroup popUntilRuns() {
        return (DocRunGroup) popUntil((IDocNode node) -> node.haveRuns());
    }

    public DocSection popUntilSection(SectionLevel level) {
        return popUntilSection(level.level);
    }

    public DocSection popUntilSection(int level) {
        return (DocSection) popUntil((IDocNode node) -> {
            if (!node.havePars())
                return false;
            if (node instanceof DocSection) {
                DocSection section = (DocSection) node;
                if (section.getLevel().level == level)
                    return true;
            }
            return false;
        });
    }

    public DocSection popUntilList() {
        return popUntilList(null, null);
    }

    public DocSection popUntilList(Boolean ordered) {
        return popUntilList(ordered, null);
    }

    public DocSection popUntilList(Boolean ordered, Integer level) {
        return (DocSection) popUntil((IDocNode node) -> {
            if (!node.havePars())
                return false;
            if (node instanceof DocListPar) {
                DocListPar listPar = (DocListPar) node;
                if (ordered != null)
                    if (listPar.ordered = ordered.booleanValue())
                        return true;
                if (level != null)
                    if (listPar.level == level.intValue())
                        return true;
            }
            return false;
        });
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (IDocNode node : stack)
            sb.append(node + "\n");
        return sb.toString();
    }

}
