package net.bodz.bas.db.ibatis.type;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.JdbcType;

import net.bodz.bas.db.ibatis.TypeHandler;
import net.bodz.bas.repr.state.State;
import net.bodz.bas.repr.state.StateGroups;

public class StateTypeHandler
        extends TypeHandler<State> {

    private int nullStateKey = 0;

    public int getNullStateId() {
        return nullStateKey;
    }

    public void setNullStateId(int nullStateKey) {
        this.nullStateKey = nullStateKey;
    }

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, State parameter, JdbcType jdbcType)
            throws SQLException {
        int key;
        if (parameter == null)
            key = nullStateKey;
        else
            key = parameter.getKey();
        ps.setInt(i, key);
    }

    @Override
    public State getNullableResult(ResultSet rs, String columnName)
            throws SQLException {
        int key = rs.getInt(columnName);
        return StateGroups.getState(key);
    }

    @Override
    public State getNullableResult(ResultSet rs, int columnIndex)
            throws SQLException {
        int key = rs.getInt(columnIndex);
        return StateGroups.getState(key);
    }

    @Override
    public State getNullableResult(CallableStatement cs, int columnIndex)
            throws SQLException {
        int key = cs.getInt(columnIndex);
        return StateGroups.getState(key);
    }

}
