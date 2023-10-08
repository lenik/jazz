package net.bodz.bas.doc.word.xwpf;

import net.bodz.bas.t.stack.TypePredicate;

public class XwpfPredicates {

    public static <T, R> TypePredicate<T, R> named(String name, TypePredicate<T, R> impl) {
        return new TypePredicate<T, R>() {

            @Override
            public boolean test(T val) {
                return impl.test(val);
            }

            @Override
            public String toString() {
                return name;
            }

        };
    }

    public static <R extends IXwpfNode> //
    TypePredicate<IXwpfNode, R> nodeClass(Class<R> nodeType) {
        return new TypePredicate<IXwpfNode, R>() {

            @Override
            public boolean test(IXwpfNode node) {
                return nodeType.isInstance(node);
            }

            @Override
            public String toString() {
                return "nodeType:" + nodeType.getName();
            }

        };
    }

    public static final TypePredicate<IXwpfNode, XwpfTableNode> CLASS_TABLE //
            = nodeClass(XwpfTableNode.class);
    public static final TypePredicate<IXwpfNode, XwpfTableRowNode> CLASS_TABLE_ROW //
            = nodeClass(XwpfTableRowNode.class);
    public static final TypePredicate<IXwpfNode, XwpfTableCellNode> CLASS_TABLE_CELL//
            = nodeClass(XwpfTableCellNode.class);

    public static final TypePredicate<IXwpfNode, XwpfDocNode> DOC //
            = named("type=doc", (IXwpfNode node) -> node.getType() == XwpfNodeType.DOCUMENT);

    public static final TypePredicate<IXwpfNode, XwpfParNode> PAR//
            = named("type=par", (IXwpfNode node) -> node.getType() == XwpfNodeType.PARAGRAPH);

    public static final TypePredicate<IXwpfNode, XwpfRunNode> RUN //
            = named("type=run", (IXwpfNode node) -> node.getType() == XwpfNodeType.RUN);

    public static final TypePredicate<IXwpfNode, XwpfTableNode> TABLE //
            = named("type=table", (IXwpfNode node) -> node.getType() == XwpfNodeType.TABLE);

    public static final TypePredicate<IXwpfNode, XwpfTableRowNode> TABLE_ROW //
            = named("type=table-row", (IXwpfNode node) -> node.getType() == XwpfNodeType.TABLE_ROW);

    public static final TypePredicate<IXwpfNode, XwpfTableCellNode> TABLE_CELL //
            = named("type=table-cell", //
                    (IXwpfNode node) -> node.getType() == XwpfNodeType.TABLE_CELL);

    public static final TypePredicate<IXwpfNode, IXwpfNode> IS_PARS//
            = named("isPars", (IXwpfNode node) -> node.isPars());
    public static final TypePredicate<IXwpfNode, IXwpfNode> IS_PAR //
            = named("isPar", (IXwpfNode node) -> node.isPar());
    public static final TypePredicate<IXwpfNode, IXwpfNode> IS_RUN //
            = named("isRun", (IXwpfNode node) -> node.isRun());

}
