package net.bodz.bas.t.catalog;

import java.io.IOException;
import java.io.Serializable;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import javax.xml.stream.XMLStreamException;

import net.bodz.bas.err.FormatException;
import net.bodz.bas.err.IllegalUsageException;
import net.bodz.bas.err.LoaderException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.json.IJsonForm;
import net.bodz.bas.fmt.json.IJsonOut;
import net.bodz.bas.fmt.json.JsonFormOptions;
import net.bodz.bas.fmt.xml.IXmlForm;
import net.bodz.bas.fmt.xml.IXmlOutput;
import net.bodz.bas.fmt.xml.xq.IElement;
import net.bodz.bas.json.JsonObject;
import net.bodz.bas.t.catalog.CrossReferenceRow.ColumnEntry;
import net.bodz.bas.t.map.JKMap;

public class CrossReference
        implements
            IMutableJavaName,
            IJsonForm,
            IXmlForm,
            Serializable {

    private static final long serialVersionUID = 1L;

    public static final String K_FOREIGN_KEY = "foreignKey";
    public static final String K_PARENT_KEY = "parentKey";
    public static final String K_CONSTRAINT_NAME = "constraintName";
    public static final String K_PRIMARY_KEY_NAME = "primaryKeyName";
    public static final String K_UPDATE_RULE = "updateRule";
    public static final String K_DELETE_RULE = "deleteRule";
    public static final String K_DEFERRABILITY = "deferrability";

    String javaPackage; // not used.
    String javaName;
    String label;
    String description;

    TableKey foreignKey;
    TableKey parentKey;

    ITableMetadata foreignTable;
    ITableMetadata parentTable;
    IColumnMetadata[] foreignColumns;
    IColumnMetadata[] parentColumns;

    String constraintName;
    String primaryKeyName;
    int updateRule;
    int deleteRule;
    int deferrability;

    public CrossReference() {
    }

    @Override
    public String getJavaPackage() {
        return javaPackage;
    }

    @Override
    public void setJavaPackage(String javaPackage) {
        this.javaPackage = javaPackage;
    }

    @Override
    public String getJavaName() {
        return javaName;
    }

    @Override
    public void setJavaName(String javaName) {
        this.javaName = javaName;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TableKey getForeignKey() {
        return foreignKey;
    }

    public void setForeignKey(TableKey foreignKey) {
        this.foreignKey = foreignKey;
    }

    public TableKey getParentKey() {
        return parentKey;
    }

    public void setParentKey(TableKey parentKey) {
        this.parentKey = parentKey;
    }

    public ITableMetadata getForeignTable() {
        return foreignTable;
    }

    public void setForeignTable(ITableMetadata foreignTable) {
        this.foreignTable = foreignTable;
    }

    public ITableMetadata getParentTable() {
        return parentTable;
    }

    public void setParentTable(ITableMetadata parentTable) {
        this.parentTable = parentTable;
    }

    public IColumnMetadata[] getForeignColumns() {
        return foreignColumns;
    }

    public void setForeignColumns(IColumnMetadata[] foreignColumns) {
        this.foreignColumns = foreignColumns;
    }

    public IColumnMetadata[] getParentColumns() {
        return parentColumns;
    }

    public void setParentColumns(IColumnMetadata[] parentColumns) {
        this.parentColumns = parentColumns;
    }

    public String getConstraintName() {
        return constraintName;
    }

    public void setConstraintName(String constraintName) {
        this.constraintName = constraintName;
    }

    public String getPrimaryKeyName() {
        return primaryKeyName;
    }

    public void setPrimaryKeyName(String primaryKeyName) {
        this.primaryKeyName = primaryKeyName;
    }

    public int getUpdateRule() {
        return updateRule;
    }

    public void setUpdateRule(int updateRule) {
        this.updateRule = updateRule;
    }

    public int getDeleteRule() {
        return deleteRule;
    }

    public void setDeleteRule(int deleteRule) {
        this.deleteRule = deleteRule;
    }

    public int getDeferrability() {
        return deferrability;
    }

    public void setDeferrability(int deferrability) {
        this.deferrability = deferrability;
    }

    public String getForeignKeySQL() {
        StringBuilder sb = new StringBuilder();
        sb.append("foreign key (");
        int i = 0;
        for (String n : foreignKey.getColumnNames()) {
            if (i++ != 0)
                sb.append(", ");
            sb.append(n);
        }
        sb.append(") references ");
        sb.append(parentKey.oid.getFullName());
        sb.append(" (");
        i = 0;
        for (String n : parentKey.getColumnNames()) {
            if (i++ != 0)
                sb.append(", ");
            sb.append(n);
        }
        sb.append(")");
        return sb.toString();
    }

    public boolean isExcluded(ITableMetadata table) {
        if (foreignKey == null)
            return false;
        for (String n : foreignKey.getColumnNames())
            if (table.getColumn(n).isExcluded())
                return true;
        return false;
    }

    public int getColumnCount() {
        if (foreignKey == null)
            return 0;
        return foreignKey.columnNames.length;
    }

    public boolean updateParentColumns() {
        if (parentColumns == null) {
            // search thru the catalog.
            if (foreignTable == null)
                throw new NullPointerException("foreignTable");
            if (parentKey == null)
                throw new NullPointerException("parentKey");

            if (parentTable == null) {
                ICatalogMetadata catalog = foreignTable.getCatalog();
                ISchemaMetadata schema = foreignTable.getParent();
                if (catalog != null)
                    parentTable = catalog.getTable(parentKey.getId());
                else if (schema != null)
                    parentTable = schema.getTable(parentKey.getId());

                if (parentTable == null)
                    return false;
            }
            parentColumns = parentKey.resolve(parentTable);
        }
        return true;
    }

    /**
     */
    public IColumnMetadata findParentColumn(String foreignColumnName) {
        if (!updateParentColumns())
            throw new IllegalUsageException("Can't determine parent columns.");

        for (int keySeq = 0; keySeq < foreignColumns.length; keySeq++) {
            String name = foreignColumns[keySeq].getName();
            if (name.equals(foreignColumnName)) {
                return parentColumns[keySeq];
            }
        }
        throw new IllegalArgumentException("no matching foreign column " + foreignColumnName);
    }

    @Override
    public String toString() {
        return foreignKey + " => " + parentKey;
    }

    @Override
    public void jsonIn(JsonObject o, JsonFormOptions opts)
            throws ParseException {
        foreignKey = new TableKey();
        foreignKey.jsonIn(o.getJsonObject(K_FOREIGN_KEY), opts);

        parentKey = new TableKey();
        parentKey.jsonIn(o.getJsonObject(K_PARENT_KEY), opts);

        constraintName = o.getString(K_CONSTRAINT_NAME);
        primaryKeyName = o.getString(K_PRIMARY_KEY_NAME);
        updateRule = o.getInt(K_UPDATE_RULE, DatabaseMetaData.importedKeyNoAction);
        deleteRule = o.getInt(K_DELETE_RULE, DatabaseMetaData.importedKeyNoAction);
        deferrability = o.getInt(K_DEFERRABILITY, DatabaseMetaData.importedKeyNotDeferrable);
    }

    @Override
    public void jsonOut(IJsonOut out, JsonFormOptions opts)
            throws IOException, FormatException {
        if (foreignKey == null)
            throw new NullPointerException("foreignKey");
        if (parentKey == null)
            throw new NullPointerException("parentKey");

        out.key(K_FOREIGN_KEY);
        foreignKey.jsonOut(out, opts, true);

        out.key(K_PARENT_KEY);
        parentKey.jsonOut(out, opts, true);

        out.entry(K_CONSTRAINT_NAME, constraintName);
        out.entry(K_PRIMARY_KEY_NAME, primaryKeyName);
        out.entry(K_UPDATE_RULE, updateRule);
        out.entry(K_DELETE_RULE, deleteRule);
        out.entry(K_DEFERRABILITY, deferrability);
    }

    @Override
    public void readObject(IElement element)
            throws ParseException, LoaderException {
        constraintName = element.getAttribute(K_CONSTRAINT_NAME);
        primaryKeyName = element.getAttribute(K_PRIMARY_KEY_NAME);
        updateRule = element.getAttributeVar(K_UPDATE_RULE).getInt(DatabaseMetaData.importedKeyNoAction);
        deleteRule = element.getAttributeVar(K_DELETE_RULE).getInt(DatabaseMetaData.importedKeyNoAction);
        deferrability = element.getAttributeVar(K_DEFERRABILITY).getInt(DatabaseMetaData.importedKeyNotDeferrable);

        IElement x_foreignKey = element.selectByTag(K_FOREIGN_KEY).getFirst();
        foreignKey = new TableKey();
        foreignKey.readObject(x_foreignKey);

        IElement x_parent = element.selectByTag(K_PARENT_KEY).getFirst();
        parentKey = new TableKey();
        parentKey.readObject(x_parent);
    }

    @Override
    public void writeObject(IXmlOutput out)
            throws XMLStreamException, FormatException {
        if (foreignKey == null)
            throw new NullPointerException("foreignKey");
        if (parentKey == null)
            throw new NullPointerException("parentKey");

        out.attribute(K_CONSTRAINT_NAME, constraintName);
        out.attribute(K_PRIMARY_KEY_NAME, primaryKeyName);

        if (updateRule != DatabaseMetaData.importedKeyNoAction)
            out.attribute(K_UPDATE_RULE, updateRule);
        if (deleteRule != DatabaseMetaData.importedKeyNoAction)
            out.attribute(K_DELETE_RULE, deleteRule);
        if (deferrability != DatabaseMetaData.importedKeyNotDeferrable)
            out.attribute(K_DEFERRABILITY, deferrability);

        out.beginElement(K_FOREIGN_KEY);
        foreignKey.writeObject(out);
        out.endElement();

        out.beginElement(K_PARENT_KEY);
        parentKey.writeObject(out);
        out.endElement();
    }

    public void readObject(List<CrossReferenceRow> rows) {
        if (rows.isEmpty())
            throw new IllegalArgumentException("No cross reference result.");
        Iterator<CrossReferenceRow> iterator = rows.iterator();

        CrossReferenceRow row = iterator.next();
        constraintName = row.FK_NAME;
        primaryKeyName = row.PK_NAME;
        updateRule = row.UPDATE_RULE;
        deleteRule = row.DELETE_RULE;
        deferrability = row.DEFERRABILITY;

        TableOid pkTable = new TableOid();
        pkTable.catalogName = row.PKTABLE_CAT;
        pkTable.schemaName = row.PKTABLE_SCHEM;
        pkTable.tableName = row.PKTABLE_NAME;

        TableOid fkTable = new TableOid();
        fkTable.catalogName = row.FKTABLE_CAT;
        fkTable.schemaName = row.FKTABLE_SCHEM;
        fkTable.tableName = row.FKTABLE_NAME;

        List<ColumnEntry> pairs = new ArrayList<>();
        while (true) {
            ColumnEntry pair = new ColumnEntry(row);
            pairs.add(pair);
            if (!iterator.hasNext())
                break;
            iterator.next();
        }
        Collections.sort(pairs);

        int nkv = pairs.size();
        String[] pkv = new String[nkv];
        String[] fkv = new String[nkv];
        for (int i = 0; i < nkv; i++) {
            pkv[i] = pairs.get(i).PKCOLUMN_NAME;
            fkv[i] = pairs.get(i).FKCOLUMN_NAME;
        }

        foreignKey = new TableKey(fkTable, fkv);
        parentKey = new TableKey(pkTable, pkv);
    }

//    public static CrossReferenceMap convertToParentMap( ResultSet rs)
//            throws SQLException {
//        return convertFromJDBC(ns, rs, true);
//    }

    public static CrossReferenceMap convertToForeignMap(ResultSet rs)
            throws SQLException {
        return convertFromJDBC(rs, false);
    }

    public static CrossReferenceMap convertFromJDBC(ResultSet rs, boolean groupByParent)
            throws SQLException {
        JKMap<TableOid, String, List<CrossReferenceRow>> rawMap = CrossReferenceRow.convert(rs, groupByParent);
        CrossReferenceMap refMap = new CrossReferenceMap(rawMap.getOrder());
        for (TableOid k1 : rawMap.keySet()) {
            for (String k2 : rawMap.get(k1).keySet()) {
                List<CrossReferenceRow> rows = rawMap.get2(k1, k2);
                CrossReference ref = new CrossReference();
                ref.readObject(rows);
                refMap.add2(k1, ref);
            }
        }
        return refMap;
    }

}
