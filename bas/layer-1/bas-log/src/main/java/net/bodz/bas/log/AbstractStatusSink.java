package net.bodz.bas.log;

public abstract class AbstractStatusSink
        extends AbstractLogSink
        implements IStatusSink {

    public static int defaultColumns = 80;

    protected int getScreenColumns() {
        // System.console();
        String columns = System.getenv("COLUMNS");
        if (columns != null) {
            try {
                return Integer.parseInt(columns);
            } catch (NumberFormatException e) {
            }
        }
        return defaultColumns;
    }

    @Override
    public void clear() {
        p_();
    }

    int MAX_100 = Integer.MAX_VALUE / 100;

    @Override
    public void progress(int value, int scale) {
        if (scale < MAX_100)
            value = value * 100 / scale;
        else
            value = value / (scale / 100);
        put(value + "%");
    }

}
