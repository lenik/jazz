package net.bodz.bas.t.stack;

import net.bodz.bas.doc.node.*;
import net.bodz.bas.doc.property.PartLevel;
import net.bodz.bas.sugar.IConstants;

public class NodePredicates
        implements
            IConstants {

    public static <R extends INode> //
    TypePredicate<INode, R> nodeType(Class<R> nodeType) {
        return new TypePredicate<INode, R>() {

            @Override
            public boolean test(INode node) {
                return nodeType.isInstance(node);
            }

            @Override
            public String toString() {
                return "nodeType:" + nodeType.getName();
            }

        };
    }

    public static final TypePredicate<INode, Table> TABLE = nodeType(Table.class);
    public static final TypePredicate<INode, TableRow> TABLE_ROW = nodeType(TableRow.class);
    public static final TypePredicate<INode, TableCell> TABLE_CELL = nodeType(TableCell.class);

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

    public static final TypePredicate<INode, Document> IS_DOC = named("isDoc",
            (INode node) -> node.getType() == NodeType.DOCUMENT);

    public static final TypePredicate<INode, PartGroup> IS_PART_GROUP = named("isPartGroup",
            (INode node) -> node.getType() == NodeType.PART_GROUP);

    public static final TypePredicate<INode, Part> IS_PART = named("isPart",
            (INode node) -> node.getType() == NodeType.PART);

    public static final TypePredicate<INode, IPar> IS_PAR = named("isPar", (INode node) -> node.isPar());
    public static final TypePredicate<INode, IRun> IS_RUN = named("isRun", (INode node) -> node.isRun());
    public static final TypePredicate<INode, AbstractParGroup> HAVE_PARS = named("havePars",
            (INode node) -> node.havePars());
    public static final TypePredicate<INode, AbstractRunGroup> HAVE_RUNS = named("haveRuns",
            (INode node) -> node.haveRuns());

    public static final TypePredicate<INode, Part> CHAPTER = section(PartLevel.CHAPTER);
    public static final TypePredicate<INode, Part> SECTION = section(PartLevel.SECTION);
    public static final TypePredicate<INode, Part> SUBSECTION = section(PartLevel.SUBSECTION);
    public static final TypePredicate<INode, Part> SUBSUBSECTION = section(PartLevel.SUBSUBSECTION);
    public static final TypePredicate<INode, Part> PARAGRAPH = section(PartLevel.PARAGRAPH);
    public static final TypePredicate<INode, Part> SUBPARAGRAPH = section(PartLevel.SUBPARAGRAPH);

    public static TypePredicate<INode, Part> section(PartLevel level) {
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
                return "section:" + level;
            }

        };
    }

    public static TypePredicate<INode, Part> section(int level) {
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
                return "section" + level;
            }

        };

    }

    public static final TypePredicate<INode, PartGroup> CHAPTERS = sectionGroup(PartLevel.CHAPTER);
    public static final TypePredicate<INode, PartGroup> SECTIONS = sectionGroup(PartLevel.SECTION);
    public static final TypePredicate<INode, PartGroup> SUBSECTIONS = sectionGroup(PartLevel.SUBSECTION);
    public static final TypePredicate<INode, PartGroup> SUBSUBSECTIONS = sectionGroup(PartLevel.SUBSUBSECTION);
    public static final TypePredicate<INode, PartGroup> PARAGRAPHS = sectionGroup(PartLevel.PARAGRAPH);
    public static final TypePredicate<INode, PartGroup> SUBPARAGRAPHS = sectionGroup(PartLevel.SUBPARAGRAPH);

    public static TypePredicate<INode, PartGroup> sectionGroup(PartLevel level) {
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
                return "sectionGroup:" + level;
            }

        };
    }

    public static TypePredicate<INode, PartGroup> sectionGroup(int level) {
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
                return "sectionGroup" + level;
            }

        };

    }

    public static TypePredicate<INode, PartGroup> sectionGroupAbove(PartLevel level) {
        return sectionGroupAbove(level.level);
    }

    public static TypePredicate<INode, PartGroup> sectionGroupAbove(int level) {
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
                return "section<" + level;
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

    public static final TypePredicate<INode, FontEnv> FONT_ENV = named("fontEnv",
            (INode node) -> node.getType() == NodeType.FONT_ENV);
    public static final TypePredicate<INode, FontStyleEnv> FONT_STYLE_ENV = named("fontStyleEnv",
            (INode node) -> node.getType() == NodeType.FONT_STYLE_ENV);

}
