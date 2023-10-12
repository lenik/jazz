package net.bodz.bas.doc.word.xwpf;

import net.bodz.bas.t.stack.TypePredicate;

public class XwPredicates {

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

    public static <R extends IXwNode> //
    TypePredicate<IXwNode, R> nodeClass(Class<R> nodeType) {
        return new TypePredicate<IXwNode, R>() {

            @Override
            public boolean test(IXwNode node) {
                return nodeType.isInstance(node);
            }

            @Override
            public String toString() {
                return "nodeType:" + nodeType.getName();
            }

        };
    }

    public static final TypePredicate<IXwNode, XwTable> CLASS_TABLE //
            = nodeClass(XwTable.class);
    public static final TypePredicate<IXwNode, XwTableRow> CLASS_TABLE_ROW //
            = nodeClass(XwTableRow.class);
    public static final TypePredicate<IXwNode, XwTableCell> CLASS_TABLE_CELL//
            = nodeClass(XwTableCell.class);

    public static final TypePredicate<IXwNode, XwDocument> DOC //
            = named("type=doc", (IXwNode node) -> node.getType() == XwNodeType.DOCUMENT);

    public static final TypePredicate<IXwNode, XwPar> PAR//
            = named("type=par", (IXwNode node) -> node.getType() == XwNodeType.PARAGRAPH);

    public static final TypePredicate<IXwNode, XwRun> RUN //
            = named("type=run", (IXwNode node) -> node.getType() == XwNodeType.RUN);

    public static final TypePredicate<IXwNode, XwTable> TABLE //
            = named("type=table", (IXwNode node) -> node.getType() == XwNodeType.TABLE);

    public static final TypePredicate<IXwNode, XwTableRow> TABLE_ROW //
            = named("type=table-row", (IXwNode node) -> node.getType() == XwNodeType.TABLE_ROW);

    public static final TypePredicate<IXwNode, XwTableCell> TABLE_CELL //
            = named("type=table-cell", //
                    (IXwNode node) -> node.getType() == XwNodeType.TABLE_CELL);

    public static final TypePredicate<IXwNode, IXwHavePars> HAVE_PARS//
            = named("isPars", (IXwNode node) -> node.havePars());
    public static final TypePredicate<IXwNode, IXwHaveRuns> HAVE_RUNS//
            = named("isPars", (IXwNode node) -> node.haveRuns());

}
