package net.bodz.bas.db.ctx;

public interface IDataContextAware {

    DataContext getDataContext();

    void setDataContext(DataContext dataContext);

    class fn {

        public static <T> T apply(DataContext dc, T o) {
            if (o instanceof IDataContextAware) {
                IDataContextAware dca = (IDataContextAware) o;
                dca.setDataContext(dc);
            }
            return o;
        }

    }

}
