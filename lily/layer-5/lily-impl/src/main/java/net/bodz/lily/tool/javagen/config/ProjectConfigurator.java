package net.bodz.lily.tool.javagen.config;

import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;
import net.bodz.bas.t.catalog.*;

public class ProjectConfigurator
        implements
            ICatalogVisitor {

    static final Logger logger = LoggerFactory.getLogger(ProjectConfigurator.class);

    ProjectConfig project;
    TableViewSettings tableSettings;

    public ProjectConfigurator(ProjectConfig projectConfig) {
        this.project = projectConfig;
    }

    @Override
    public boolean beginTableView(ITableViewMetadata table) {
        if (table instanceof DefaultTableViewMetadata) {
            DefaultTableViewMetadata mutable = (DefaultTableViewMetadata) table;

            String javaName = project.tableNameMap.get(table.getName());
            if (javaName != null) {
                if (!javaName.contains("."))
                    javaName = project.defaultPackageName + "." + javaName;
                mutable.setJavaQName(javaName);
            }

            String type = project.getTableClassMap().get(table.getName());
            if (type != null)
                mutable.setJavaType(type);

        }
        tableSettings = project.tableMap.get(table.getName());
        return true;
    }

    @Override
    public void column(IColumnMetadata column) {
        ColumnSettings columnSettings = null;
        if (tableSettings != null)
            columnSettings = tableSettings.columnMap.get(column.getName());

        if (column instanceof DefaultColumnMetadata) {
            DefaultColumnMetadata mutable = (DefaultColumnMetadata) column;

            String property = project.columnPropertyMap.get(column.getName());
            if (property != null) {
                if (property.isEmpty() || "-".equals(property))
                    mutable.setExcluded(true);
                else
                    mutable.setJavaName(property);
            }

            Integer verboseLevel = project.columnLevelMap.get(column.getName());
            if (verboseLevel != null)
                mutable.setVerboseLevel(verboseLevel.intValue());

            Integer joinLevel = project.joinLevelMap.get(column.getName());
            if (joinLevel != null)
                mutable.setJoinLevel(joinLevel.intValue());

            if (columnSettings != null) {
                if (columnSettings.javaName != null)
                    mutable.setJavaName(columnSettings.javaName);

                if (columnSettings.javaType != null) {
                    Class<?> javaClass;
                    try {
                        javaClass = Class.forName(columnSettings.javaType);
                    } catch (ClassNotFoundException e) {
                        throw new RuntimeException(e.getMessage(), e);
                    }
                    mutable.setType(javaClass);
                }

                if (columnSettings.description != null)
                    mutable.setDescription(columnSettings.description);
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
