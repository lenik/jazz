package net.bodz.lily.tool.javagen.config;

import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;
import net.bodz.bas.t.catalog.*;

public class ProjectConfigurator
        implements
            ICatalogVisitor {

    static final Logger logger = LoggerFactory.getLogger(ProjectConfigurator.class);

    ProjectConfig config;

    public ProjectConfigurator(ProjectConfig config) {
        this.config = config;
    }

    @Override
    public boolean beginTableView(ITableViewMetadata table) {
        if (table instanceof DefaultTableViewMetadata) {
            DefaultTableViewMetadata mutable = (DefaultTableViewMetadata) table;

            String javaName = config.tableNameMap.get(table.getName());
            if (javaName != null) {
                if (!javaName.contains("."))
                    javaName = config.defaultPackageName + "." + javaName;
                mutable.setJavaQName(javaName);
            }

            String type = config.getTableClassMap().get(table.getName());
            if (type != null)
                mutable.setJavaType(type);
        }
        return true;
    }

    @Override
    public void column(IColumnMetadata column) {
        if (column instanceof DefaultColumnMetadata) {
            DefaultColumnMetadata mutable = (DefaultColumnMetadata) column;

            String property = config.columnPropertyMap.get(column.getName());
            if (property != null) {
                if (property.isEmpty() || "-".equals(property))
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
