package net.bodz.bas.ctx.scope.fast;

public class FastScopeFrame {

    VarNode head;
    VarNode tail;

    public VarNode alloc(Object data) {
        VarNode node = new VarNode(data);
        node.frame = this;

        if (tail != null) {
            tail.nextVar = node;
            node.prevVar = tail;
        }

        tail = node;
        return node;
    }

}
