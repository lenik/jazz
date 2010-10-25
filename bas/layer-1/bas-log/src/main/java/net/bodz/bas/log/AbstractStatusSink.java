package net.bodz.bas.log;

public abstract class AbstractStatusSink
        extends AbstractLogSink
        implements IStatusSink {

    public static int DEFAULT_COLUMNS = 80;

    private int scale = 100;
    private int index;

    public AbstractStatusSink() {
        super();
    }

    protected int getScreenColumns() {
        // System.console();
        String columns = System.getenv("COLUMNS");
        if (columns != null) {
            try {
                return Integer.parseInt(columns);
            } catch (NumberFormatException e) {
            }
        }
        return DEFAULT_COLUMNS;
    }

    private static int SMALLINT_MAX = Integer.MAX_VALUE / 100;

    @Override
    public int getProgressScale() {
        return scale;
    }

    @Override
    public void setProgressScale(int scale) {
        if (scale <= 0)
            throw new IllegalArgumentException("scale must be positive: " + scale);
        this.scale = scale;
    }

    @Override
    public int getProgressIndex() {
        return index;
    }

    @Override
    public void setProgressIndex(int index) {
        if (index < 0)
            index = 0;
        else if (index >= scale)
            index = scale;

        this.index = index;

        if (scale < SMALLINT_MAX)
            index = index * 100 / scale;
        else
            index = index / (scale / 100);
        p(index + "%");
    }

}
