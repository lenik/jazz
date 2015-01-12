package net.bodz.bas.repr.state;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.JdbcType;

import net.bodz.bas.db.ibatis.MybatisTypeHandler;
import net.bodz.bas.repr.state.State;
import net.bodz.bas.repr.state.StateGroup;

public class StateTypeHandler
        extends MybatisTypeHandler<State> {

    private StateGroup states = StateGroup.INDEXED;
    private int nullStateId = 0;

    public int getNullStateId() {
        return nullStateId;
    }

    public void setNullStateId(int nullStateId) {
        this.nullStateId = nullStateId;
    }

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, State parameter, JdbcType jdbcType)
            throws SQLException {
        int id;
        if (parameter == null)
            id = nullStateId;
        else
            id = parameter.getId();
        ps.setInt(i, id);
    }

    @Override
    public State getNullableResult(ResultSet rs, String columnName)
            throws SQLException {
        int id = rs.getInt(columnName);
        return states.getState(id);
    }

    @Override
    public State getNullableResult(ResultSet rs, int columnIndex)
            throws SQLException {
        int id = rs.getInt(columnIndex);
        return states.getState(id);
    }

    @Override
    public State getNullableResult(CallableStatement cs, int columnIndex)
            throws SQLException {
        int id = cs.getInt(columnIndex);
        return states.getState(id);
    }

}
