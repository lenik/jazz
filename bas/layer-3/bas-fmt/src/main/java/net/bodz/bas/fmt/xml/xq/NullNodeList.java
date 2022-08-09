package net.bodz.bas.fmt.xml.xq;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class NullNodeList
        implements
            NodeList {

    @Override
    public Node item(int index) {
        return null;
    }

    @Override
    public int getLength() {
        return 0;
    }

    public static NullNodeList INSTANCE = new NullNodeList();

}
