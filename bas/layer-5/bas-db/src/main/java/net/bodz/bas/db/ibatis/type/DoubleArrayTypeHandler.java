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
@MappedTypes(double[].class)
public class DoubleArrayTypeHandler
        extends TypeHandler<double[]> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, double[] parameter, JdbcType jdbcType)
            throws SQLException {
        Double[] elements = wrap(parameter);
        ParameterMetaData metaData = ps.getParameterMetaData();
        String typeName = metaData.getParameterTypeName(i);
        Array array = ps.getConnection().createArrayOf(typeName, elements);
        ps.setArray(i, array);
    }

    static Double[] wrap(double[] array) {
        Double[] wrapped = new Double[array.length];
        for (int i = 0; i < array.length; i++)
            wrapped[i] = array[i];
        return wrapped;
    }

    @Override
    public double[] getNullableResult(ResultSet rs, String columnName)
            throws SQLException {
        Array array = rs.getArray(columnName);
        return toShortArray(array);
    }

    @Override
    public double[] getNullableResult(ResultSet rs, int columnIndex)
            throws SQLException {
        Array array = rs.getArray(columnIndex);
        return toShortArray(array);
    }

    @Override
    public double[] getNullableResult(CallableStatement cs, int columnIndex)
            throws SQLException {
        Array array = cs.getArray(columnIndex);
        return toShortArray(array);
    }

    static double[] toShortArray(Array _array)
            throws SQLException {
        if (_array == null)
            return null;
        Object array = _array.getArray();
        if (array == null)
            return null;
        return toShortArray(array);
    }

    static double[] toShortArray(Object array) {
        Class<?> arrayClass = array.getClass();
        int n = java.lang.reflect.Array.getLength(array);
        double[] shorts = new double[n];

        Class<?> valType = arrayClass.getComponentType();
        IVarConverter<Object> converter = VarConverters.getConverter(valType);

        for (int i = 0; i < n; i++) {
            Object v = java.lang.reflect.Array.get(array, i);
            Double doubleVal = converter.toDouble(v);
            if (doubleVal != null)
                shorts[i] = doubleVal;
        }
        return shorts;
    }

}
