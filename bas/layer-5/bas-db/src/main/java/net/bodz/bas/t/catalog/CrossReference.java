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
import net.bodz.bas.err.LoaderException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.json.IJsonForm;
import net.bodz.bas.fmt.json.IJsonOut;
import net.bodz.bas.fmt.xml.IXmlForm;
import net.bodz.bas.fmt.xml.IXmlOutput;
import net.bodz.bas.fmt.xml.xq.IElement;
import net.bodz.bas.json.JsonObject;
import net.bodz.bas.t.catalog.CrossReferenceRow.ColumnEntry;
import net.bodz.bas.t.map.JKMap;

public class CrossReference
        implements
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

    TableKey foreignKey;
    TableKey parentKey;

    String constraintName;
    String primaryKeyName;
    int updateRule;
    int deleteRule;
    int deferrability;

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

    @Override
    public String toString() {
        return foreignKey + " => " + parentKey;
    }

    @Override
    public void readObject(JsonObject o)
            throws ParseException {
        foreignKey = new TableKey();
        foreignKey.readObject(o.getJsonObject(K_FOREIGN_KEY));

        parentKey = new TableKey();
        parentKey.readObject(o.getJsonObject(K_PARENT_KEY));

        constraintName = o.getString(K_CONSTRAINT_NAME);
        primaryKeyName = o.getString(K_PRIMARY_KEY_NAME);
        updateRule = o.getInt(K_UPDATE_RULE, DatabaseMetaData.importedKeyNoAction);
        deleteRule = o.getInt(K_DELETE_RULE, DatabaseMetaData.importedKeyNoAction);
        deferrability = o.getInt(K_DEFERRABILITY, DatabaseMetaData.importedKeyNotDeferrable);
    }

    @Override
    public void writeObject(IJsonOut out)
            throws IOException, FormatException {
        if (foreignKey == null)
            throw new NullPointerException("foreignKey");
        if (parentKey == null)
            throw new NullPointerException("parentKey");

        out.key(K_FOREIGN_KEY);
        foreignKey.writeObjectBoxed(out);

        out.key(K_PARENT_KEY);
        parentKey.writeObjectBoxed(out);

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

        QualifiedTableName pkTable = new QualifiedTableName();
        pkTable.catalogName = row.PKTABLE_CAT;
        pkTable.schemaName = row.PKTABLE_SCHEM;
        pkTable.tableName = row.PKTABLE_NAME;

        QualifiedTableName fkTable = new QualifiedTableName();
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

    public static CrossReferenceMap convertToParentMap(ResultSet rs)
            throws SQLException {
        return convertFromJDBC(rs, true);
    }

    public static CrossReferenceMap convertToForeignMap(ResultSet rs)
            throws SQLException {
        return convertFromJDBC(rs, false);
    }

    public static CrossReferenceMap convertFromJDBC(ResultSet rs, boolean groupByParent)
            throws SQLException {
        JKMap<QualifiedTableName, String, List<CrossReferenceRow>> rawMap = CrossReferenceRow.convert(rs,
                groupByParent);
        CrossReferenceMap refMap = new CrossReferenceMap(rawMap.getOrder());
        for (QualifiedTableName k1 : rawMap.keySet()) {
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
