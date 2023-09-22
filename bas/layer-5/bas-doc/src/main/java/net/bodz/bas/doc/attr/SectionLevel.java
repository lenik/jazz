package net.bodz.bas.doc.attr;

public enum SectionLevel {

    CHAPTER(1, ElementType.CHAPTER),

    SECTION(2, ElementType.SECTION),
    SUBSECTION(3, ElementType.SUBSECTION),
    SUBSUBSECTION(4, ElementType.SUBSUBSECTION),

    PARAGRAPH(5, ElementType.PARAGRAPH),
    SUBPARAGRAPH(6, ElementType.SUBPARAGRAPH),

    H1(1, ElementType.CHAPTER),
    H2(2, ElementType.SECTION),
    H3(3, ElementType.SUBSECTION),
    H4(4, ElementType.SUBSUBSECTION),
    H5(5, ElementType.PARAGRAPH),
    H6(6, ElementType.SUBPARAGRAPH),

    ;

    public final int level;
    public final ElementType elementType;

    private SectionLevel(int level, ElementType elementType) {
        this.level = level;
        this.elementType = elementType;
    }

    public SectionLevel toHtml() {
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

    public SectionLevel toLatex() {
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

    public static SectionLevel html(int level) {
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

    public static SectionLevel latex(int level) {
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
