package net.bodz.lily.tool.javagen.config;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;

import javax.persistence.Column;

import net.bodz.bas.c.string.Phrase;
import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;
import net.bodz.bas.t.catalog.*;

public class CatalogSettingsApplier
        implements
            ICatalogVisitor {

    static final Logger logger = LoggerFactory.getLogger(CatalogSettingsApplier.class);

    public CatalogSettingsApplier() {
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

            String propertyName = pd.getName();
            String columnName;
            Column aColumn = getter.getAnnotation(Column.class);
            if (aColumn != null)
                columnName = aColumn.name();
            else
                columnName = Phrase.fooBar(propertyName).foo_bar;

            if(propertyName.equals("priority"))
                System.err.println(tableView);
            IColumnMetadata column = tableView.getColumn(columnName);
            if (column == null) {
                column = tableView.findColumnByJavaName(propertyName);
            }
            if (column != null) {
                if (column instanceof DefaultColumnMetadata) {
                    DefaultColumnMetadata mutable = (DefaultColumnMetadata) column;
                    mutable.setExcluded(true);
                }
            }
        }
    }

    @Override
    public void primaryKey(ITableMetadata table, TableKey key) {
    }

    @Override
    public void foreignKey(ITableMetadata table, CrossReference crossRef) {
    }

}
