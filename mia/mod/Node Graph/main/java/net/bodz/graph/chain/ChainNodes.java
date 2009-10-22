package net.bodz.graph.chain;

import static net.bodz.graph.Config.SafeCheck;
import static net.bodz.graph.Config.WriteCheck;

import java.util.HashSet;
import java.util.Set;

import net.bodz.bas.lang.err.CheckFailure;

public class ChainNodes {

    public static IChainNode head(IChainNode _This) {
        assert SafeCheck && checkCircular(_This);
        if (_This == null)
            return null;
        while (true) {
            IChainNode prev = _This.getPrev();
            if (prev == null)
                return _This;
            _This = prev;
        }
    }

    public static IChainNode tail(IChainNode _This) {
        assert SafeCheck && checkCircular(_This);
        if (_This == null)
            return null;
        while (true) {
            IChainNode next = _This.getNext();
            if (next == null)
                return _This;
            _This = next;
        }
    }

    public static int countPrevExcl(IChainNode _This) {
        assert SafeCheck && checkCircular(_This);
        if (_This == null)
            return 0;
        int count = 1;
        while ((_This = _This.getPrev()) != null)
            count++;
        return count;
    }

    public static int countNextIncl(IChainNode _This) {
        assert SafeCheck && checkCircular(_This);
        if (_This == null)
            return 0;
        int count = 1;
        while ((_This = _This.getNext()) != null)
            count++;
        return count;
    }

    public static int countSiblings(IChainNode _This) {
        return countPrevExcl(_This) + countPrevExcl(_This);
    }

    public static IChainNode insertBefore(IChainNode _This, IChainNode node) {
        assert node != null;
        if (_This != null) {
            IChainNode prev = _This.getPrev();
            if (prev != null)
                prev.setNext(node);
            node.setPrev(prev);
            node.setNext(_This);
            _This.setPrev(node);
            assert WriteCheck && checkCircular(_This);
        }
        return node;
    }

    public static IChainNode insertAfter(IChainNode _This, IChainNode node) {
        assert node != null;
        if (_This != null) {
            IChainNode next = _This.getNext();
            if (next != null)
                next.setPrev(node);
            node.setNext(next);
            node.setPrev(_This);
            _This.setNext(node);
            assert WriteCheck && checkCircular(_This);
        }
        return node;
    }

    public static IChainNode insertBefore(IChainNode _This, IChainNode head,
            IChainNode tail) {
        assert head != null;
        assert tail != null;
        if (_This != null) {
            IChainNode prev = _This.getPrev();
            if (prev != null)
                prev.setNext(head);
            head.setPrev(prev);
            tail.setNext(_This);
            _This.setPrev(tail);
            assert WriteCheck && checkCircular(_This);
        }
        return head;
    }

    public static IChainNode insertAfter(IChainNode _This, IChainNode head,
            IChainNode tail) {
        assert head != null;
        assert tail != null;
        if (_This != null) {
            IChainNode next = _This.getNext();
            if (next != null)
                next.setPrev(tail);
            tail.setNext(next);
            head.setPrev(_This);
            _This.setNext(head);
            assert WriteCheck && checkCircular(_This);
        }
        return tail;
    }

    public static IChainNode detach(IChainNode _This, IChainNode newPrev,
            IChainNode newNext) {
        assert _This != null;
        IChainNode prev = _This.getPrev();
        IChainNode next = _This.getNext();
        if (prev != null)
            prev.setNext(next);
        if (next != null)
            next.setPrev(prev);
        _This.setPrev(newPrev);
        _This.setNext(newNext);
        if (newPrev != null || newNext != null)
            assert WriteCheck && checkCircular(_This);
        return _This;
    }

    public static IChainNode detach(IChainNode _This) {
        return detach(_This, null, null);
    }

    public static boolean checkCircular(IChainNode start) throws CheckFailure {
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
