package net.bodz.bas.db.ibatis;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface IResultSetForm {

    void readObject(ResultSet rs)
            throws SQLException;

}
