package net.bodz.bas.c.org.postgresql.util;

import java.net.InetAddress;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.JdbcType;
import org.postgresql.util.PGobject;

import net.bodz.bas.db.batis.AbstractTypeHandler;

public class PgInetAddressTypeHandler
        extends AbstractTypeHandler<InetAddress> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, InetAddress parameter, JdbcType jdbcType)
            throws SQLException {
        PGobject pgo = new PGobject();
        pgo.setType("");
        pgo.setValue(parameter.getHostAddress());
        ps.setObject(i, pgo);
    }

    @Override
    public InetAddress getNullableResult(ResultSet rs, String columnName)
            throws SQLException {
        PGobject pgo = (PGobject) rs.getObject(columnName);
        return PGobjects.convertIP(pgo);
    }

    @Override
    public InetAddress getNullableResult(ResultSet rs, int columnIndex)
            throws SQLException {
        PGobject pgo = (PGobject) rs.getObject(columnIndex);
        return PGobjects.convertIP(pgo);
    }

    @Override
    public InetAddress getNullableResult(CallableStatement cs, int columnIndex)
            throws SQLException {
        PGobject pgo = (PGobject) cs.getObject(columnIndex);
        return PGobjects.convertIP(pgo);
    }

}
