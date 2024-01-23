package net.bodz.lily.shell;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Table;

import org.apache.ibatis.binding.MapperRegistry;
import org.apache.ibatis.session.Configuration;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.bas.db.ctx.DataHub;
import net.bodz.bas.db.ctx.IDataContextAware;
import net.bodz.bas.db.ibatis.IGenericMapper;
import net.bodz.bas.db.ibatis.IbatisMapperProvider;
import net.bodz.bas.err.IllegalConfigException;
import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;
import net.bodz.bas.meta.build.ProgramName;
import net.bodz.bas.program.skel.BasicCLI;

/**
 * Dump table
 */
@ProgramName("dumptab")
public class DumpTable
        extends BasicCLI
        implements
            IDataContextAware {

    static final Logger logger = LoggerFactory.getLogger(DumpTable.class);

    DataContext dataContext;

    public DumpTable() {
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

    @Override
    protected void mainImpl(String... args)
            throws Exception {
        IbatisMapperProvider provider = (IbatisMapperProvider) getDataContext().getMapperProvider();
        Configuration conf = provider.getSqlSessionFactory().getConfiguration();
        MapperRegistry registry = conf.getMapperRegistry();

        Map<String, Class<?>> tableMap = new HashMap<>();
        Map<String, Class<?>> typeMap = new HashMap<>();

        for (Class<?> mapperClass : registry.getMappers()) {
            String mapperName = mapperClass.getName();

            String typeName = mapperName;
            typeName = typeName.replace(".dao.", ".");
            typeName = typeName.replace(".impl.", ".");
            if (typeName.endsWith("Mapper"))
                typeName = typeName.substring(0, typeName.length() - 6);

            ClassLoader loader = mapperClass.getClassLoader();
            Class<?> clazz;
            try {
                clazz = Class.forName(typeName, false, loader);
            } catch (ReflectiveOperationException e) {
                logger.warn(e.getMessage());
                continue;
            }
            typeMap.put(clazz.getSimpleName(), mapperClass);

            Table aTable = clazz.getAnnotation(Table.class);
            if (aTable != null) {
                tableMap.put(aTable.name(), mapperClass);
            }
        }

        for (String table : args) {
            Class<?> mapperClass = tableMap.get(table);
            if (mapperClass == null) {
                mapperClass = typeMap.get(table);
                if (mapperClass == null) {
                    logger.error("Invalid table: " + table + ", skipped.");
                    continue;
                }
            }

            IGenericMapper<?> mapper = (IGenericMapper<?>) getMapper(mapperClass);
            List<?> list = mapper.all();
            for (Object obj : list) {
                exportDDL(obj);
            }
        }
    }

    void exportDDL(Object obj) {
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

    public static void main(String[] args)
            throws Exception {
        new DumpTable().execute(args);
    }

}
