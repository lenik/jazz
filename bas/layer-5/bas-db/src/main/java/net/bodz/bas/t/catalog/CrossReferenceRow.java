package net.bodz.bas.t.catalog;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.bodz.bas.t.map.JKMap;

class CrossReferenceRow {

    String FK_NAME;
    String PK_NAME;
    short UPDATE_RULE;
    short DELETE_RULE;
    short DEFERRABILITY;

    String PKTABLE_CAT;
    String PKTABLE_SCHEM;
    String PKTABLE_NAME;

    String FKTABLE_CAT;
    String FKTABLE_SCHEM;
    String FKTABLE_NAME;

    String FKCOLUMN_NAME;
    String PKCOLUMN_NAME;
    short KEY_SEQ;

    public QualifiedTableName getParentName() {
        return new QualifiedTableName(PKTABLE_CAT, PKTABLE_SCHEM, PKTABLE_NAME);
    }

    public QualifiedTableName getForeignName() {
        return new QualifiedTableName(FKTABLE_CAT, FKTABLE_SCHEM, FKTABLE_NAME);
    }

    public void readObject(ResultSet rs)
            throws SQLException {
        FK_NAME = rs.getString("FK_NAME");
        PK_NAME = rs.getString("PK_NAME");
        UPDATE_RULE = rs.getShort("UPDATE_RULE");
        DELETE_RULE = rs.getShort("DELETE_RULE");
        DEFERRABILITY = rs.getShort("DEFERRABILITY");

        PKTABLE_CAT = rs.getString("PKTABLE_CAT");
        PKTABLE_SCHEM = rs.getString("PKTABLE_SCHEM");
        PKTABLE_NAME = rs.getString("PKTABLE_NAME");

        FKTABLE_CAT = rs.getString("FKTABLE_CAT");
        FKTABLE_SCHEM = rs.getString("FKTABLE_SCHEM");
        FKTABLE_NAME = rs.getString("FKTABLE_NAME");

        FKCOLUMN_NAME = rs.getString("FKCOLUMN_NAME");
        PKCOLUMN_NAME = rs.getString("PKCOLUMN_NAME");
        KEY_SEQ = rs.getShort("KEY_SEQ");
    }

    static JKMap<QualifiedTableName, String, List<CrossReferenceRow>> convert(ResultSet rs, boolean groupByParent)
            throws SQLException {
        JKMap<QualifiedTableName, String, List<CrossReferenceRow>> map = new JKMap<>();
        while (rs.next()) {
            CrossReferenceRow row = new CrossReferenceRow();
            row.readObject(rs);

            QualifiedTableName k1;
            String k2;
            if (groupByParent) {
                k1 = row.getParentName();
                k2 = row.FK_NAME;
            } else {
                k1 = row.getForeignName();
                k2 = row.FK_NAME;
            }

            List<CrossReferenceRow> rows = map.get2(k1, k2);
            if (rows == null) {
                rows = new ArrayList<>();
                map.put2(k1, k2, rows);
            }
            rows.add(row);
        }
        return map;
    }

}