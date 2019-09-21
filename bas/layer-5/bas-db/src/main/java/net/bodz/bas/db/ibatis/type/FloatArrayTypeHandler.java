package net.bodz.bas.db.ibatis.type;

import java.sql.Array;
import java.sql.CallableStatement;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;

import net.bodz.bas.db.ibatis.AliasedType;
import net.bodz.bas.db.ibatis.TypeHandler;
import net.bodz.bas.t.variant.conv.IVarConverter;
import net.bodz.bas.t.variant.conv.VarConverters;

@AliasedType
@MappedTypes(float[].class)
public class FloatArrayTypeHandler
        extends TypeHandler<float[]> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, float[] parameter, JdbcType jdbcType)
            throws SQLException {
        Float[] elements = wrap(parameter);
        ParameterMetaData metaData = ps.getParameterMetaData();
        String typeName = metaData.getParameterTypeName(i);
        Array array = ps.getConnection().createArrayOf(typeName, elements);
        ps.setArray(i, array);
    }

    static Float[] wrap(float[] array) {
        Float[] wrapped = new Float[array.length];
        for (int i = 0; i < array.length; i++)
            wrapped[i] = array[i];
        return wrapped;
    }

    @Override
    public float[] getNullableResult(ResultSet rs, String columnName)
            throws SQLException {
        Array array = rs.getArray(columnName);
        return toShortArray(array);
    }

    @Override
    public float[] getNullableResult(ResultSet rs, int columnIndex)
            throws SQLException {
        Array array = rs.getArray(columnIndex);
        return toShortArray(array);
    }

    @Override
    public float[] getNullableResult(CallableStatement cs, int columnIndex)
            throws SQLException {
        Array array = cs.getArray(columnIndex);
        return toShortArray(array);
    }

    static float[] toShortArray(Array _array)
            throws SQLException {
        if (_array == null)
            return null;
        Object array = _array.getArray();
        if (array == null)
            return null;
        return toShortArray(array);
    }

    static float[] toShortArray(Object array) {
        Class<?> arrayClass = array.getClass();
        int n = java.lang.reflect.Array.getLength(array);
        float[] shorts = new float[n];

        Class<?> valType = arrayClass.getComponentType();
        IVarConverter<Object> converter = VarConverters.getConverter(valType);

        for (int i = 0; i < n; i++) {
            Object v = java.lang.reflect.Array.get(array, i);
            Float shortVal = converter.toFloat(v);
            if (shortVal != null)
                shorts[i] = shortVal;
        }
        return shorts;
    }

}
