package net.bodz.bas.t.catalog;

import java.lang.annotation.Annotation;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import net.bodz.bas.c.object.Nullables;
import net.bodz.bas.err.LoaderException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.json.JsonFormOptions;
import net.bodz.bas.fmt.xml.xq.IElement;
import net.bodz.bas.json.JsonArray;
import net.bodz.bas.json.JsonObject;
import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;
import net.bodz.bas.potato.PotatoTypes;
import net.bodz.bas.potato.element.IType;
import net.bodz.bas.t.map.ListMap;
import net.bodz.bas.t.tuple.QualifiedName;

public class DefaultTableMetadata
        extends DefaultRowSetMetadata
        implements
            ITableMetadata,
            IMutableJavaType {

    static final Logger logger = LoggerFactory.getLogger(DefaultTableMetadata.class);

    TableOid oid = new TableOid();
    QualifiedName javaType;

    boolean resolved;
    Class<?> javaClass;
    IType potatoType;

    String baseTypeName;
    Class<?> baseClass;
    IType baseType;

    TableType tableType = getDefaultTableType();

    String label;
    String description;
    protected TableKey primaryKey;
    private Map<String, CrossReference> foreignKeys = new LinkedHashMap<>();
    private Map<String, CrossReference> columnForeignKeyMap = new HashMap<>();

    public DefaultTableMetadata() {
    }

    public DefaultTableMetadata(ISchemaMetadata parent) {
        super(parent);
    }

    @Override
    public TableOid getId() {
        return oid;
    }

    public void setId(TableOid oid) {
        if (oid == null)
            throw new NullPointerException("id");
        this.oid = oid;
    }

    @Override
    public QualifiedName getJavaType() {
        return javaType;
    }

    @Override
    public void setJavaType(QualifiedName javaType) {
        this.javaType = javaType;
    }

    @Override
    public String getJavaPackageName() {
        if (javaType != null)
            return javaType.packageName;
        if (parent != null)
            return parent.getJavaQName().getFullName();
        return null;
    }
//
//    @Override
//    public void setJavaPackage(String javaPackage) {
//        this.javaPackage = javaPackage;
//    }

    @Override
    public String getBaseTypeName() {
        return baseTypeName;
    }

    public void setBaseTypeName(String baseTypeName) {
        this.baseTypeName = baseTypeName;
    }

    @Override
    public TableType getTableType() {
        return tableType;
    }

    public void setTableType(TableType tableType) {
        this.tableType = tableType;
    }

    protected TableType getDefaultTableType() {
        return TableType.VIEW;
    }

    @Override
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public TableKey getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(TableKey primaryKey) {
        if (primaryKey == null)
            throw new NullPointerException("primaryKey");
        this.primaryKey = primaryKey.normalize(this);

        for (IColumnMetadata column : columns) {
            String columnName = column.getName();
            boolean isPrimaryKey = this.primaryKey.contains(columnName);
            if (isPrimaryKey != column.isPrimaryKey()) {
                DefaultColumnMetadata mutable = (DefaultColumnMetadata) column;
                mutable.setPrimaryKey(isPrimaryKey);
            }
        }
    }

    public void parsePrimaryKey(String keyStr) {
        String[] strFields;
        if (Nullables.isEmpty(keyStr))
            strFields = new String[0];
        else {
            strFields = keyStr.split(",");
            for (int i = 0; i < strFields.length; i++)
                strFields[i] = strFields[i].trim();
        }
        primaryKey = new TableKey(getId(), strFields).normalize(this);
    }

    public void setPrimaryKeyFromColumns() {
        List<String> cols = new ArrayList<>();
        for (IColumnMetadata column : columns) {
            if (column.isPrimaryKey())
                cols.add(column.getName());
        }
        String[] cv = cols.toArray(new String[0]);
        primaryKey = new TableKey(getId(), cv).normalize(this);
    }

    @Override
    public Map<String, CrossReference> getForeignKeys() {
        return foreignKeys;
    }

    @Override
    public CrossReference getForeignKey(String constraintName) {
        return foreignKeys.get(constraintName);
    }

    public void addForeignKey(CrossReference foreignKey) {
        foreignKeys.put(foreignKey.constraintName, foreignKey);
        for (String column : foreignKey.getForeignKey().columnNames)
            columnForeignKeyMap.put(column, foreignKey);
    }

    @Override
    public CrossReference getForeignKeyFromColumn(String columnName) {
        return columnForeignKeyMap.get(columnName);
    }

    void onForeignKeysReset() {
        columnForeignKeyMap.clear();
        for (CrossReference foreignKey : foreignKeys.values())
            for (String column : foreignKey.getForeignKey().columnNames)
                columnForeignKeyMap.put(column, foreignKey);
    }

    @Override
    public void wireUp() {
        sortColumns();
    }

    @Override
    public void jsonIn(JsonObject o, JsonFormOptions opts)
            throws ParseException {
        super.jsonIn(o, opts);

        oid.jsonIn(o, opts);

        setJavaType(o.getString(K_JAVA_TYPE));
        baseTypeName = o.getString(K_BASE_TYPE);

        tableType = o.getEnum(TableType.class, K_TABLE_TYPE, getDefaultTableType());

        JsonObject pk = o.getJsonObject(K_PRIMARY_KEY);
        if (pk != null) {
            primaryKey = new TableKey();
            primaryKey.jsonIn(pk, opts);
        } else {
            // HINT: primary key can also be calculated from columns.
            setPrimaryKeyFromColumns();
        }

        JsonArray fkv = o.getJsonArray(K_FOREIGN_KEYS);
        if (fkv != null) {
            int nfk = fkv.length();
            for (int i = 0; i < nfk; i++) {
                JsonObject fk = fkv.getJsonObject(i);
                CrossReference ref = new CrossReference();
                ref.jsonIn(fk, opts);
                foreignKeys.put(ref.getConstraintName(), ref);
            }
        }
        onForeignKeysReset();
    }

    @Override
    public void readObject(IElement x_table)
            throws ParseException, LoaderException {
        super.readObject(x_table);

        oid.readObject(x_table);

        setJavaType(x_table.a(K_JAVA_TYPE).getString());
        baseTypeName = x_table.a(K_BASE_TYPE).getString();

        tableType = x_table.a(K_TABLE_TYPE)//
                .getEnum(TableType.class, TableType.VIEW);

        IElement x_pk = x_table.selectByTag(K_PRIMARY_KEY).getFirst();
        if (x_pk != null) {
            primaryKey = new TableKey();
            primaryKey.readObject(x_pk);
        } else {
            setPrimaryKeyFromColumns();
        }

        IElement x_foreignKeys = x_table.selectByTag(K_FOREIGN_KEYS).getFirst();
        if (x_foreignKeys != null) {
            foreignKeys.clear();
            for (IElement x_fk : x_foreignKeys.children()) {
                CrossReference ref = new CrossReference();
                ref.readObject(x_fk);
                foreignKeys.put(ref.getConstraintName(), ref);
            }
        }
        onForeignKeysReset();
    }

    public void loadFromRSMD(Connection connection)
            throws SQLException {
        // Parse from empty-query.
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(//
                "select * from " + getCompactName() + " where 1=2");
        loadFromRSMD(rs.getMetaData());
        rs.close();
    }

    @Override
    public void loadFromRSMD(ResultSetMetaData rsmd)
            throws SQLException {
        super.loadFromRSMD(rsmd);

        int columnOfThisTable = 1;
        oid.catalogName = rsmd.getCatalogName(columnOfThisTable);
        oid.schemaName = rsmd.getSchemaName(columnOfThisTable);
        oid.tableName = rsmd.getTableName(columnOfThisTable);
    }

    class TableHandler
            implements
                IJDBCMetaDataHandler {

        @Override
        public ITableMetadata table(ResultSet rs)
                throws SQLException {
            oid.catalogName = rs.getString("table_cat");
            oid.schemaName = rs.getString("table_schem");
            oid.tableName = rs.getString("table_name");
            tableType = TableType.parseJDBC(rs, getDefaultTableType());
            description = rs.getString("remarks");
            return DefaultTableMetadata.this;
        }

        @Override
        public IColumnMetadata column(ResultSet rs)
                throws SQLException {
            DefaultColumnMetadata column = createColumn();
            column.readObject(rs);
            addColumn(column);
            return column;
        }

        @Override
        public void primaryKey(ITableMetadata table, TableKey primaryKey)
                throws SQLException {
            setPrimaryKey(primaryKey);
        }

        @Override
        public void crossReference(ITableMetadata table, CrossReference foreignKey)
                throws SQLException {
            addForeignKey(foreignKey);
        }

    }

    IJDBCMetaDataHandler metaDataHandler = createJDBCMetaDataHandler();

    protected IJDBCMetaDataHandler createJDBCMetaDataHandler() {
        return new TableHandler();
    }

    @Override
    public IJDBCMetaDataHandler getJDBCMetaDataHandler() {
        return metaDataHandler;
    }

    public void setJDBCMetaDataHandler(IJDBCMetaDataHandler handler) {
        metaDataHandler = handler;
    }

    /**
     * @return <code>false</code> if table isn't found.
     */
    public boolean loadFromJDBC(Connection connection, LoadFromJDBCOptions options)
            throws SQLException {
        logger.debug("Load table/view from JDBC: " + getId());

        DatabaseMetaData dmd = connection.getMetaData();
        ResultSet rs;

        // Parse from table's metadata
        rs = dmd.getTables(oid.catalogName, oid.schemaName, oid.tableName, null);
        int count = 0;
        while (rs.next()) {
            if (count == 0)
                metaDataHandler.table(rs);
            count++;
        }
        rs.close();
        if (count == 0)
            return false;

        // Parse from columns' metadata
        rs = dmd.getColumns(oid.catalogName, oid.schemaName, oid.tableName, null);
        while (rs.next())
            metaDataHandler.column(rs);
        rs.close();

        // Find out primary key
        if (options.isLoadKeys()) {
            rs = dmd.getPrimaryKeys(oid.catalogName, oid.schemaName, oid.tableName);
            Map<TableOid, TableKey> primaryKeyMap = TableKey.convertFromJDBC(rs);
            for (TableKey pk : primaryKeyMap.values())
                metaDataHandler.primaryKey(this, pk);
            rs.close();

            rs = dmd.getCrossReference(null, null, null, //
                    oid.catalogName, oid.schemaName, oid.tableName);
            ListMap<TableOid, CrossReference> foreignMap = CrossReference.convertToForeignMap(rs);
            assert foreignMap.size() <= 1;
            if (! foreignMap.isEmpty()) {
                List<CrossReference> parentList = foreignMap.values().iterator().next();
                for (CrossReference crossRef : parentList)
                    metaDataHandler.crossReference(this, crossRef);
            }
            rs.close();
        }

        metaDataHandler.endTable(this);
        return true;
    }

    @Override
    public void accept(ICatalogVisitor visitor) {
        visitor.beginTableOrView(this);

        visitor.beginColumns(this);
        int n = getColumnCount();
        for (int i = 0; i < n; i++)
            getColumn(i).accept(visitor);
        visitor.endColumns(this);

        visitor.primaryKey(this, getPrimaryKey());

        for (CrossReference crossRef : getForeignKeys().values())
            visitor.foreignKey(this, crossRef);

        _acceptMore(visitor);

        visitor.endTableOrView(this);
    }

    protected void _acceptMore(ICatalogVisitor visitor) {
    }

    @Override
    public String toString() {
        SchemaOid ns = getParent().getId();
        return tableType + " " + oid.getCompactName(ns) + "(" + getColumnNames() + ")";
    }

    /** ⇱ Java runtime binding */
    /* _____________________________ */static section.iface __JAVA_BIND__;

    boolean excluded;

    @Override
    public boolean isExcluded() {
        return excluded;
    }

    public void setExcluded(boolean excluded) {
        this.excluded = excluded;
    }

    private synchronized void resolveType() {
        if (! resolved) {
            QualifiedName javaType = getJavaType();
            if (javaType != null) {
                javaClass = javaType.getJavaClass();
                if (javaClass != null)
                    try {
                        potatoType = PotatoTypes.getInstance().loadType(javaClass);
                    } catch (NoClassDefFoundError e) {
                        assert false;
                    }
            }
            resolved = true;
        }
    }

    @Override
    public Class<?> getJavaClass() {
        resolveType();
        return javaClass;
    }

    @Override
    public IType getPotatoType() {
        resolveType();
        return potatoType;
    }

    @Override
    public Class<?> getBaseClass() {
        if (baseClass == null) {
            if (baseTypeName == null)
                return null;
            int lt = baseTypeName.indexOf('<');
            String rawName = lt == -1 //
                    ? baseTypeName
                    : baseTypeName.substring(0, lt);
            try {
                baseClass = Class.forName(rawName);
            } catch (ClassNotFoundException e) {
                return null;
            }
        }
        return baseClass;
    }

    @Override
    public IType getBaseType() {
        if (baseType == null) {
            Class<?> clazz = getBaseClass();
            if (clazz != null)
                baseType = PotatoTypes.getInstance().loadType(clazz);
        }
        return baseType;
    }

    @Override
    public <A extends Annotation> A getAnnotation(Class<A> annotationClass) {
        IType type = getPotatoType();
        if (type == null)
            return null;
        else
            return type.getAnnotation(annotationClass);
    }

}
