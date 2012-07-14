package net.bodz.bas.i18n.dom;

class StackedNode {

    StackedNode parent;
    DomainNode<?, ?> prefetch;

    public StackedNode(StackedNode parent, DomainNode<?, ?> first) {
        this.parent = parent;
        this.prefetch = first;
    }

    public void formatReversedPath(StringBuilder out) {
        String token = prefetch.domain;
        if (token != null) {
            if (out.length() != 0)
                out.append(DomainNode.DOMAIN_SEPARATOR);
            for (int i = token.length() - 1; i >= 0; i--)
                out.append(token.charAt(i));
        }
        if (parent != null)
            parent.formatReversedPath(out);
    }

    public String getPath() {
        if (parent == null) {
            // to return null path for the top-level domain, also make it slightly faster.
            return prefetch.domain;
        } else {
            StringBuilder buf = new StringBuilder();
            formatReversedPath(buf);
            buf.reverse();
            return buf.toString();
        }
    }

}
