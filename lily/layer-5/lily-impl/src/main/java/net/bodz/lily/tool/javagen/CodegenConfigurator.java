package net.bodz.lily.tool.javagen;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;

import javax.persistence.Column;

import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;
import net.bodz.bas.t.catalog.*;

public class CodegenConfigurator
        implements
            ICatalogVisitor {

    static final Logger logger = LoggerFactory.getLogger(CodegenConfigurator.class);

    CodegenConfig config;

    public CodegenConfigurator(CodegenConfig config) {
        this.config = config;
    }

    @Override
    public boolean beginTableView(ITableViewMetadata table) {
        if (table instanceof DefaultTableViewMetadata) {
            DefaultTableViewMetadata mutable = (DefaultTableViewMetadata) table;

            String tableClass = config.tableClassMap.get(table.getName());
            if (tableClass != null) {
                String parentType = null;
                int colon = tableClass.indexOf(':');
                if (colon != -1) {
                    parentType = tableClass.substring(colon + 1);
                    tableClass = tableClass.substring(0, colon);
                }
                mutable.setJavaName(tableClass);
                if (parentType != null)
                    mutable.setJavaType(parentType);
            }
        }
        return true;
    }

    @Override
    public void endTableView(ITableViewMetadata tableView) {
        String javaType = tableView.getJavaType();
        if (javaType == null)
            return;

        BeanInfo beanInfo;

        try {
            Class<?> superclass = Class.forName(javaType);
            if (superclass == null)
                return;
            beanInfo = Introspector.getBeanInfo(superclass);
        } catch (ClassNotFoundException e) {
            logger.error(e, "unknown java type " + javaType);
            return;
        } catch (IntrospectionException e) {
            logger.error(e, "error getting bean info for " + javaType);
            return;
        }

        for (PropertyDescriptor pd : beanInfo.getPropertyDescriptors()) {
            Method getter = pd.getReadMethod();
            if (getter == null)
                continue;
            Column aColumn = getter.getAnnotation(Column.class);
            if (aColumn == null)
                continue;
            String columnName = aColumn.name();
            IColumnMetadata column = tableView.getColumn(columnName);
            if (column != null) {
                if (column instanceof DefaultColumnMetadata) {
                    DefaultColumnMetadata mutable = (DefaultColumnMetadata) column;
                    mutable.setExcluded(true);
                }
            }
        }
    }

    @Override
    public void column(IColumnMetadata column) {
        if (column instanceof DefaultColumnMetadata) {
            DefaultColumnMetadata mutable = (DefaultColumnMetadata) column;

            String property = config.columnPropertyMap.get(column.getName());
            if (property != null) {
                if ("-".equals(property))
                    mutable.setExcluded(true);
                else
                    mutable.setJavaName(property);
            }

            Integer verboseLevel = config.columnLevelMap.get(column.getName());
            if (verboseLevel != null)
                mutable.setVerboseLevel(verboseLevel.intValue());

            Integer joinLevel = config.joinLevelMap.get(column.getName());
            if (joinLevel != null)
                mutable.setJoinLevel(joinLevel.intValue());
        }
    }

    @Override
    public void primaryKey(ITableMetadata table, TableKey key) {
    }

    @Override
    public void foreignKey(ITableMetadata table, CrossReference crossRef) {
    }

}
