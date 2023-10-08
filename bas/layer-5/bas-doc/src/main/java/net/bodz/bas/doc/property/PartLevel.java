package net.bodz.bas.doc.property;

public enum PartLevel {

    CHAPTER(1, ElementType.CHAPTER, null),

    SECTION(2, ElementType.SECTION, CHAPTER),
    SUBSECTION(3, ElementType.SUBSECTION, SECTION),
    SUBSUBSECTION(4, ElementType.SUBSUBSECTION, SUBSECTION),

    PARAGRAPH(5, ElementType.PARAGRAPH, SUBSUBSECTION),
    SUBPARAGRAPH(6, ElementType.SUBPARAGRAPH, PARAGRAPH),

    H1(1, ElementType.CHAPTER, null),
    H2(2, ElementType.SECTION, H1),
    H3(3, ElementType.SUBSECTION, H2),
    H4(4, ElementType.SUBSUBSECTION, H3),
    H5(5, ElementType.PARAGRAPH, H4),
    H6(6, ElementType.SUBPARAGRAPH, H5),

    ;

    public static final int LEVEL_MIN = 1;
    public static final int LEVEL_MAX = 6;

    public final int level;
    public final ElementType elementType;
    private final PartLevel up;
    private PartLevel down;

    private PartLevel(int level, ElementType elementType, PartLevel up) {
        this.level = level;
        this.elementType = elementType;
        this.up = up;
        if (up != null)
            up.down = this;
    }

    public PartLevel getUp() {
        return up;
    }

    public PartLevel getDown() {
        return down;
    }

    public PartLevel toHtml() {
        switch (this) {
        case CHAPTER:
            return H1;
        case SECTION:
            return H2;
        case SUBSECTION:
            return H3;
        case SUBSUBSECTION:
            return H4;
        case PARAGRAPH:
            return H5;
        case SUBPARAGRAPH:
            return H6;
        default:
            return this;
        }
    }

    public PartLevel toLatex() {
        switch (this) {
        case H1:
            return CHAPTER;
        case H2:
            return SECTION;
        case H3:
            return SUBSECTION;
        case H4:
            return SUBSUBSECTION;
        case H5:
            return PARAGRAPH;
        case H6:
            return SUBPARAGRAPH;
        default:
            return this;
        }
    }

    public static PartLevel html(int level) {
        switch (level) {
        case 1:
            return H1;
        case 2:
            return H2;
        case 3:
            return H3;
        case 4:
            return H4;
        case 5:
            return H5;
        case 6:
            return H6;
        default:
            throw new IllegalArgumentException("bad h-level: " + level);
        }
    }

    public static PartLevel latex(int level) {
        switch (level) {
        case 1:
            return CHAPTER;
        case 2:
            return SECTION;
        case 3:
            return SUBSECTION;
        case 4:
            return SUBSUBSECTION;
        case 5:
            return PARAGRAPH;
        case 6:
            return SUBPARAGRAPH;
        default:
            throw new IllegalArgumentException("bad tex-level: " + level);
        }
    }

}
