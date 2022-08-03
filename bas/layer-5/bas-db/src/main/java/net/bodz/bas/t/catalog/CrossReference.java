package net.bodz.bas.t.catalog;

import java.io.IOException;
import java.io.Serializable;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
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

    public void readFromJDBC(ResultSet rs)
            throws SQLException {
        if (!rs.next())
            throw new IllegalArgumentException("No cross reference result.");

        constraintName = rs.getString("FK_NAME");
        primaryKeyName = rs.getString("PK_NAME");
        updateRule = rs.getShort("UPDATE_RULE");
        deleteRule = rs.getShort("DELETE_RULE");
        deferrability = rs.getShort("DEFERRABILITY");

        QualifiedTableName pkTable = new QualifiedTableName();
        pkTable.catalogName = rs.getString("PKTABLE_CAT");
        pkTable.schemaName = rs.getString("PKTABLE_SCHEM");
        pkTable.tableName = rs.getString("PKTABLE_NAME");

        QualifiedTableName fkTable = new QualifiedTableName();
        fkTable.catalogName = rs.getString("FKTABLE_CAT");
        fkTable.schemaName = rs.getString("FKTABLE_SCHEM");
        fkTable.tableName = rs.getString("FKTABLE_NAME");

        List<ColumnPair> pairs = new ArrayList<>();
        do {
            ColumnPair pair = new ColumnPair();
            pair.fkName = rs.getString("FKCOLUMN_NAME");
            pair.pkName = rs.getString("PKCOLUMN_NAME");
            pair.seq = rs.getShort("KEY_SEQ");
            pairs.add(pair);
        } while (rs.next());
        Collections.sort(pairs);

        int nkv = pairs.size();
        String[] pkv = new String[nkv];
        String[] fkv = new String[nkv];
        for (int i = 0; i < nkv; i++) {
            pkv[i] = pairs.get(i).pkName;
            fkv[i] = pairs.get(i).fkName;
        }

        foreignKey = new TableKey(fkTable, fkv);
        parentKey = new TableKey(pkTable, pkv);
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
        out.attribute(K_CONSTRAINT_NAME, constraintName);
        out.attribute(K_PRIMARY_KEY_NAME, primaryKeyName);

        if (updateRule != DatabaseMetaData.importedKeyNoAction)
            out.attribute(K_UPDATE_RULE, updateRule);
        if (deleteRule != DatabaseMetaData.importedKeyNoAction)
            out.attribute(K_DELETE_RULE, deleteRule);
        if (deferrability != DatabaseMetaData.importedKeyNotDeferrable)
            out.attribute(K_DEFERRABILITY, deferrability);

        out.beginElement(K_FOREIGN_KEY);
        foreignKey.writeObjectBoxed(out);
        out.endElement();

        out.beginElement(K_PARENT_KEY);
        parentKey.writeObjectBoxed(out);
        out.endElement();
    }

}

class ColumnPair
        implements
            Comparable<ColumnPair> {

    short seq;
    String pkName;
    String fkName;

    @Override
    public int compareTo(ColumnPair o) {
        return seq - o.seq;
    }

}
