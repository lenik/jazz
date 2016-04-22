package net.bodz.lily.model.res.art;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import org.apache.ibatis.type.JdbcType;

import net.bodz.bas.db.ibatis.TypeHandler;

public class SupplyMethodTypeHandler
        extends TypeHandler<SupplyMethod> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, SupplyMethod parameter, JdbcType jdbcType)
            throws SQLException {
        ps.setObject(i, parameter.getName(), Types.OTHER);
    }

    @Override
    public SupplyMethod getNullableResult(ResultSet rs, String columnName)
            throws SQLException {
        String name = rs.getString(columnName);
        return SupplyMethod.METADATA.ofName(name);
    }

    @Override
    public SupplyMethod getNullableResult(ResultSet rs, int columnIndex)
            throws SQLException {
        String name = rs.getString(columnIndex);
        return SupplyMethod.METADATA.ofName(name);
    }

    @Override
    public SupplyMethod getNullableResult(CallableStatement cs, int columnIndex)
            throws SQLException {
        String name = cs.getString(columnIndex);
        return SupplyMethod.METADATA.ofName(name);
    }

}
