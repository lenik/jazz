package net.bodz.bas.c.object;

import java.io.Serializable;

public class TreeDumpFormat
        implements Serializable {

    private static final long serialVersionUID = 1L;

    private boolean showIdentity;
    private boolean showNames;
    private boolean showValues;
    private boolean lineBreak;
    private int maxDepth;
    private TreeDumpFormat nestedFormat;

    public TreeDumpFormat() {
        nestedFormat = this;
    }

    public boolean isShowIdentity() {
        return showIdentity;
    }

    public void setShowIdentity(boolean showIdentity) {
        this.showIdentity = showIdentity;
    }

    public boolean isShowFields() {
        return showNames || showValues;
    }

    public boolean isShowNames() {
        return showNames;
    }

    public void setShowNames(boolean showNames) {
        this.showNames = showNames;
    }

    public boolean isShowValues() {
        return showValues;
    }

    public void setShowValues(boolean showValues) {
        this.showValues = showValues;
    }

    public boolean isLineBreak() {
        return lineBreak;
    }

    public void setLineBreak(boolean lineBreak) {
        this.lineBreak = lineBreak;
    }

    public int getMaxDepth() {
        return maxDepth;
    }

    public void setMaxDepth(int maxDepth) {
        this.maxDepth = maxDepth;
    }

    public TreeDumpFormat getNestedFormat() {
        return nestedFormat;
    }

    public static final TreeDumpFormat SIMPLE = new TreeDumpFormat();
    public static final TreeDumpFormat SHORT = new TreeDumpFormat();
    public static final TreeDumpFormat NORMAL = new TreeDumpFormat();
    public static final TreeDumpFormat VERBOSE = new TreeDumpFormat();
    public static final TreeDumpFormat LOG_ENTRY = new TreeDumpFormat();
    public static final TreeDumpFormat FULL = new TreeDumpFormat();

    public static TreeDumpFormat DEFAULT = NORMAL; // TODO

    static {
        SIMPLE.setShowIdentity(true);

        SHORT.setShowIdentity(true);
        SHORT.setShowValues(true);

        NORMAL.setShowIdentity(true);
        NORMAL.setShowNames(true);
        NORMAL.setShowValues(true);
        NORMAL.setLineBreak(true);

        VERBOSE.setShowIdentity(true);
        VERBOSE.setShowNames(true);
        VERBOSE.setShowValues(true);
        VERBOSE.setLineBreak(true);
        VERBOSE.setMaxDepth(1);

        LOG_ENTRY.setShowIdentity(true);
        LOG_ENTRY.setShowNames(true);
        LOG_ENTRY.setShowValues(true);

        FULL.setShowIdentity(true);
        FULL.setShowNames(true);
        FULL.setShowValues(true);
        FULL.setLineBreak(true);
        FULL.setMaxDepth(1000);
    }

}
