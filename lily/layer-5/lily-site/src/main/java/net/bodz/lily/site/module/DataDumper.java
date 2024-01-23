package net.bodz.lily.site.module;

import java.lang.reflect.Method;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.bas.db.ctx.DataHub;
import net.bodz.bas.db.ctx.IDataContextAware;
import net.bodz.bas.err.IllegalConfigException;
import net.bodz.bas.program.skel.BasicCLI;

/**
 * Data dumper
 */
public abstract class DataDumper
        extends BasicCLI
        implements
            IDataContextAware {

    DataContext dataContext;

    public DataDumper() {
        DataHub dataContexts = DataHub.getPreferredHub();
        if (dataContexts == null)
            throw new IllegalConfigException("No data contexts installed.");
        dataContext = dataContexts.getMain();
    }

    @Override
    public DataContext getDataContext() {
        return dataContext;
    }

    @Override
    public void setDataContext(DataContext dataContext) {
        this.dataContext = dataContext;
    }

    protected void exportDDL(Object obj) {
        String exporterClassName = obj.getClass().getName() + "Exporter";
        Class<?> exporterClass;
        try {
            exporterClass = Class.forName(exporterClassName);

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
        Method exportMethod;
        try {
            exportMethod = exporterClass.getMethod("exportDDL", obj.getClass());
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
        Object result;
        try {
            result = exportMethod.invoke(null, obj);
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
        System.out.println(result);
    }

}
