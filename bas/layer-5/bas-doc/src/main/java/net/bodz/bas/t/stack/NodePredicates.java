package net.bodz.bas.t.stack;

import net.bodz.bas.doc.node.*;
import net.bodz.bas.doc.property.PartLevel;
import net.bodz.bas.sugar.IConstants;

public class NodePredicates
        implements
            IConstants {

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

    public static <R extends INode> //
    TypePredicate<INode, R> nodeClass(Class<R> nodeClass) {
        return new TypePredicate<INode, R>() {

            @Override
            public boolean test(INode node) {
                return nodeClass.isInstance(node);
            }

            @Override
            public String toString() {
                return "nodeClass:" + nodeClass.getName();
            }

        };
    }

    public static final TypePredicate<INode, Table> CLASS_TABLE = named("class:Table", nodeClass(Table.class));
    public static final TypePredicate<INode, TableRow> CLASS_TABLE_ROW = named("class:TableRow",
            nodeClass(TableRow.class));
    public static final TypePredicate<INode, TableCell> CLASS_TABLE_CELL = named("class:TableCell",
            nodeClass(TableCell.class));

    public static final TypePredicate<INode, Document> DOC = named("DOC",
            (INode node) -> node.getType() == NodeType.DOCUMENT);

    public static final TypePredicate<INode, PartGroup> PART_GROUP = named("PART_GROUP",
            (INode node) -> node.getType() == NodeType.PART_GROUP);

    public static final TypePredicate<INode, Part> PART = named("PART",
            (INode node) -> node.getType() == NodeType.PART);

    public static final TypePredicate<INode, Table> TABLE = named("TABLE",
            (INode node) -> node.getType() == NodeType.TABLE);
    public static final TypePredicate<INode, TableRow> TABLE_ROW = named("TABLE_ROW",
            (INode node) -> node.getType() == NodeType.TABLE_ROW);
    public static final TypePredicate<INode, TableCell> TABLE_CELL = named("TABLE_CELL",
            (INode node) -> node.getType() == NodeType.TABLE_CELL);

    public static final TypePredicate<INode, ListItem> LIST_ITEM = named("LIST_ITEM",
            (INode node) -> node.getType() == NodeType.LIST_ITEM);

    public static final TypePredicate<INode, FontEnv> FONT_ENV = named("FONT_ENV",
            (INode node) -> node.getType() == NodeType.FONT_ENV);
    public static final TypePredicate<INode, FontStyleEnv> FONT_STYLE_ENV = named("FONT_STYLE_ENV",
            (INode node) -> node.getType() == NodeType.FONT_STYLE_ENV);

    public static final TypePredicate<INode, IPar> IS_PAR = named("isPar", (INode node) -> node.isPar());
    public static final TypePredicate<INode, IRun> IS_RUN = named("isRun", (INode node) -> node.isRun());
    public static final TypePredicate<INode, IHavePars> HAVE_PARS = named("havePars", (INode node) -> node.havePars());
    public static final TypePredicate<INode, IHaveRuns> HAVE_RUNS = named("haveRuns", (INode node) -> node.haveRuns());

//    public static final TypePredicate<INode, ListItem> LIST_ITEM = named("listItem", null);

    public static final TypePredicate<INode, Part> CHAPTER = part(PartLevel.CHAPTER);
    public static final TypePredicate<INode, Part> SECTION = part(PartLevel.SECTION);
    public static final TypePredicate<INode, Part> SUBSECTION = part(PartLevel.SUBSECTION);
    public static final TypePredicate<INode, Part> SUBSUBSECTION = part(PartLevel.SUBSUBSECTION);
    public static final TypePredicate<INode, Part> PARAGRAPH = part(PartLevel.PARAGRAPH);
    public static final TypePredicate<INode, Part> SUBPARAGRAPH = part(PartLevel.SUBPARAGRAPH);

    public static TypePredicate<INode, Part> part(PartLevel level) {
        return new TypePredicate<INode, Part>() {

            @Override
            public boolean test(INode node) {
                if (node.getType() == NodeType.PART) {
                    Part section = (Part) node;
                    PartGroup sectionGroup = section.getParent();
                    if (sectionGroup.getLevel() == level)
                        return true;
                }
                return false;
            }

            @Override
            public String toString() {
                return "part/" + level;
            }

        };
    }

    public static TypePredicate<INode, Part> part(int level) {
        return new TypePredicate<INode, Part>() {

            @Override
            public boolean test(INode node) {
                if (node.getType() == NodeType.PART) {
                    Part section = (Part) node;
                    PartGroup sectionGroup = section.getParent();
                    if (sectionGroup.getLevel().level == level)
                        return true;
                }
                return false;
            }

            @Override
            public String toString() {
                return "part" + level;
            }

        };

    }

    public static final TypePredicate<INode, PartGroup> CHAPTERS = partGroup(PartLevel.CHAPTER);
    public static final TypePredicate<INode, PartGroup> SECTIONS = partGroup(PartLevel.SECTION);
    public static final TypePredicate<INode, PartGroup> SUBSECTIONS = partGroup(PartLevel.SUBSECTION);
    public static final TypePredicate<INode, PartGroup> SUBSUBSECTIONS = partGroup(PartLevel.SUBSUBSECTION);
    public static final TypePredicate<INode, PartGroup> PARAGRAPHS = partGroup(PartLevel.PARAGRAPH);
    public static final TypePredicate<INode, PartGroup> SUBPARAGRAPHS = partGroup(PartLevel.SUBPARAGRAPH);

    public static TypePredicate<INode, PartGroup> partGroup(PartLevel level) {
        return new TypePredicate<INode, PartGroup>() {

            @Override
            public boolean test(INode node) {
                if (node.getType() == NodeType.PART_GROUP) {
                    PartGroup sectionGroup = (PartGroup) node;
                    if (sectionGroup.getLevel() == level)
                        return true;
                }
                return false;
            }

            @Override
            public String toString() {
                return "partGroup/" + level;
            }

        };
    }

    public static TypePredicate<INode, PartGroup> partGroup(int level) {
        return new TypePredicate<INode, PartGroup>() {

            @Override
            public boolean test(INode node) {
                if (node.getType() == NodeType.PART_GROUP) {
                    PartGroup sectionGroup = (PartGroup) node;
                    if (sectionGroup.getLevel().level == level)
                        return true;
                }
                return false;
            }

            @Override
            public String toString() {
                return "partGroup" + level;
            }

        };

    }

    public static TypePredicate<INode, PartGroup> partGroupAbove(PartLevel level) {
        return partGroupAbove(level.level);
    }

    public static TypePredicate<INode, PartGroup> partGroupAbove(int level) {
        return new TypePredicate<INode, PartGroup>() {

            @Override
            public boolean test(INode node) {
                if (node.getType() == NodeType.PART_GROUP) {
                    PartGroup sectionGroup = (PartGroup) node;
                    if (sectionGroup.getLevel().level < level)
                        return true;
                }
                return false;
            }

            @Override
            public String toString() {
                return "part<" + level;
            }

        };

    }

    public static final TypePredicate<INode, ListPar> LIST = listPar(null, null);
    public static final TypePredicate<INode, ListPar> OL = listPar(true);
    public static final TypePredicate<INode, ListPar> UL = listPar(false);

    public static TypePredicate<INode, ListPar> listPar(Boolean ordered) {
        return listPar(ordered, null);
    }

    public static TypePredicate<INode, ListPar> listPar(Integer level) {
        return listPar(null, level);
    }

    public static TypePredicate<INode, ListPar> listPar(Boolean ordered, Integer level) {
        return new TypePredicate<INode, ListPar>() {

            @Override
            public boolean test(INode node) {
                if (!node.havePars())
                    return false;
                if (node instanceof ListPar) {
                    ListPar listPar = (ListPar) node;
                    if (ordered != null)
                        if (listPar.isOrdered() == ordered.booleanValue())
                            return true;
                    if (level != null)
                        if (listPar.getLevel() == level.intValue())
                            return true;
                    return true;
                }
                return false;
            }

            @Override
            public String toString() {
                StringBuilder sb = new StringBuilder();
                if (ordered != null)
                    sb.append(ordered ? "ol" : "ul");
                else
                    sb.append("list");
                if (level != null)
                    sb.append(level);
                return sb.toString();
            }

        };
    }

}
