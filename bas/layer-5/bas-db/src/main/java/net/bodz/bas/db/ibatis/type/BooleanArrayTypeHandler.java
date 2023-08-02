package net.bodz.bas.db.ibatis.type;

import java.sql.Array;
import java.sql.CallableStatement;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.Alias;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;

import net.bodz.bas.db.ibatis.AliasedType;
import net.bodz.bas.db.ibatis.TypeHandler;
import net.bodz.bas.t.variant.conv.IVarConverter;
import net.bodz.bas.t.variant.conv.VarConverters;

@Alias("Booleans")
@AliasedType
@MappedTypes(Boolean[].class)
public class BooleanArrayTypeHandler
        extends TypeHandler<Boolean[]> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, Boolean[] parameter, JdbcType jdbcType)
            throws SQLException {
        Boolean[] elements = wrap(parameter);
        ParameterMetaData metaData = ps.getParameterMetaData();
        String typeName = metaData.getParameterTypeName(i);
        Array array = ps.getConnection().createArrayOf(typeName, elements);
        ps.setArray(i, array);
    }

    static Boolean[] wrap(Boolean[] array) {
        Boolean[] wrapped = new Boolean[array.length];
        for (int i = 0; i < array.length; i++)
            wrapped[i] = array[i];
        return wrapped;
    }

    @Override
    public Boolean[] getNullableResult(ResultSet rs, String columnName)
            throws SQLException {
        Array array = rs.getArray(columnName);
        return toBooleanArray(array);
    }

    @Override
    public Boolean[] getNullableResult(ResultSet rs, int columnIndex)
            throws SQLException {
        Array array = rs.getArray(columnIndex);
        return toBooleanArray(array);
    }

    @Override
    public Boolean[] getNullableResult(CallableStatement cs, int columnIndex)
            throws SQLException {
        Array array = cs.getArray(columnIndex);
        return toBooleanArray(array);
    }

    static Boolean[] toBooleanArray(Array _array)
            throws SQLException {
        if (_array == null)
            return null;
        Object array = _array.getArray();
        if (array == null)
            return null;
        return toBooleanArray(array);
    }

    static Boolean[] toBooleanArray(Object array) {
        Class<?> arrayClass = array.getClass();
        int n = java.lang.reflect.Array.getLength(array);
        Boolean[] bools = new Boolean[n];

        Class<?> valType = arrayClass.getComponentType();
        IVarConverter<Object> converter = VarConverters.getConverter(valType);

        for (int i = 0; i < n; i++) {
            Object v = java.lang.reflect.Array.get(array, i);
            Boolean boolVal = v == null ? null : converter.toBoolean(v);
            bools[i] = boolVal;
        }
        return bools;
    }

}
