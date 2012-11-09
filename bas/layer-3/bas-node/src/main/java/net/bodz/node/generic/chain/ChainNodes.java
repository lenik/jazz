package net.bodz.node.generic.chain;

import static net.bodz.node.generic.NodeConfig.checkIntegrityOnChange;

import java.util.HashSet;
import java.util.Set;

import net.bodz.bas.err.CheckFailure;
import net.bodz.bas.meta.decl.Pure;

public class ChainNodes {

    @Pure
    public static IChainNode head(IChainNode node) {
        if (node == null)
            return null;
        while (true) {
            IChainNode prev = node.getPrev();
            if (prev == null)
                return node;
            node = prev;
        }
    }

    @Pure
    public static IChainNode tail(IChainNode node) {
        if (node == null)
            return null;
        while (true) {
            IChainNode next = node.getNext();
            if (next == null)
                return node;
            node = next;
        }
    }

    @Pure
    public static int countPrevExcl(IChainNode node) {
        if (node == null)
            return 0;
        int count = 1;
        while ((node = node.getPrev()) != null)
            count++;
        return count;
    }

    @Pure
    public static int countNextIncl(IChainNode node) {
        if (node == null)
            return 0;
        int count = 1;
        while ((node = node.getNext()) != null)
            count++;
        return count;
    }

    @Pure
    public static int countSiblings(IChainNode node) {
        return countPrevExcl(node) + countPrevExcl(node);
    }

    public static IChainNode insertBefore(IChainNode node, IChainNode another) {
        assert another != null;
        if (node != null) {
            IChainNode prev = node.getPrev();
            if (prev != null)
                prev.setNext(another);
            another.setPrev(prev);
            another.setNext(node);
            node.setPrev(another);
            assert checkIntegrityOnChange && checkCircular(node);
        }
        return another;
    }

    public static IChainNode insertAfter(IChainNode node, IChainNode another) {
        assert another != null;
        if (node != null) {
            IChainNode next = node.getNext();
            if (next != null)
                next.setPrev(another);
            another.setNext(next);
            another.setPrev(node);
            node.setNext(another);
            assert checkIntegrityOnChange && checkCircular(node);
        }
        return another;
    }

    public static IChainNode insertBefore(IChainNode node, IChainNode head, IChainNode tail) {
        assert head != null;
        assert tail != null;
        if (node != null) {
            IChainNode prev = node.getPrev();
            if (prev != null)
                prev.setNext(head);
            head.setPrev(prev);
            tail.setNext(node);
            node.setPrev(tail);
            assert checkIntegrityOnChange && checkCircular(node);
        }
        return head;
    }

    public static IChainNode insertAfter(IChainNode node, IChainNode head, IChainNode tail) {
        assert head != null;
        assert tail != null;
        if (node != null) {
            IChainNode next = node.getNext();
            if (next != null)
                next.setPrev(tail);
            tail.setNext(next);
            head.setPrev(node);
            node.setNext(head);
            assert checkIntegrityOnChange && checkCircular(node);
        }
        return tail;
    }

    public static IChainNode detach(IChainNode node, IChainNode newPrev, IChainNode newNext) {
        assert node != null;
        IChainNode prev = node.getPrev();
        IChainNode next = node.getNext();
        if (prev != null)
            prev.setNext(next);
        if (next != null)
            next.setPrev(prev);
        node.setPrev(newPrev);
        node.setNext(newNext);
        if (newPrev != null || newNext != null)
            assert checkIntegrityOnChange && checkCircular(node);
        return node;
    }

    public static IChainNode detach(IChainNode node) {
        return detach(node, null, null);
    }

    @Pure
    public static boolean checkCircular(IChainNode start)
            throws CheckFailure {
        if (start == null)
            return true;
        Set<IChainNode> set = new HashSet<IChainNode>();
        for (IChainNode node : ChainIteration.siblings(start)) {
            if (set.contains(node))
                throw new CheckFailure("circular loop");
            set.add(node);
        }
        return true;
    }

}
