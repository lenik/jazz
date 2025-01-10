package net.bodz.bas.esm.skel01;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.InetAddress;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.bodz.bas.esm.EsmName;
import net.bodz.bas.esm.ITsTypeInfoProvider;
import net.bodz.bas.esm.TsTypeMap;
import net.bodz.bas.esm.skel01.Core.BasInfo;
import net.bodz.bas.esm.skel01.Core.BaseInfo;
import net.bodz.bas.esm.skel01.Core.LangTime;
import net.bodz.bas.fmt.json.JsonVariant;
import net.bodz.bas.t.variant.MutableVariant;

public class TsTypeInfos
        extends TsTypeMap
        implements
            ITsTypeInfoProvider {

    {
        BaseInfo baseInfo = Skel01Modules.core.baseInfo;
        BasInfo basInfo = Skel01Modules.core.basInfo;
        LangTime time = Skel01Modules.core.time;

        addType(byte.class, baseInfo.BYTE);
        addType(short.class, baseInfo.SHORT);
        addType(int.class, baseInfo.INT);
        addType(long.class, baseInfo.LONG);
        addType(float.class, baseInfo.FLOAT);
        addType(double.class, baseInfo.DOUBLE);
        addType(boolean.class, baseInfo.BOOLEAN);
        addType(char.class, baseInfo.CHAR);

        addType(Byte.class, baseInfo.BYTE);
        addType(Short.class, baseInfo.SHORT);
        addType(Integer.class, baseInfo.INT);
        addType(Long.class, baseInfo.LONG);
        addType(Float.class, baseInfo.FLOAT);
        addType(Double.class, baseInfo.DOUBLE);
        addType(Boolean.class, baseInfo.BOOLEAN);
        addType(Character.class, baseInfo.CHAR);
        addType(String.class, baseInfo.STRING);

        addType(BigInteger.class, baseInfo.BIG_INTEGER);
        addType(BigDecimal.class, baseInfo.BIG_DECIMAL);

        addType(Date.class, time.JAVA_DATE);
        addType(java.sql.Date.class, time.SQL_DATE);
        addType(Time.class, time.SQL_TIME);
        addType(Timestamp.class, time.TIMESTAMP);

        addAbstractType(List.class, baseInfo.LIST);
        addAbstractType(Set.class, baseInfo.SET);
        addAbstractType(Map.class, baseInfo.MAP);

        addType(JsonVariant.class, basInfo.JSON_VARIANT);
        addType(MutableVariant.class, basInfo.JSON_VARIANT);

        addAbstractType(InetAddress.class, Skel01Modules.core.baseInfo.INET_ADDRESS);
    }

    @Override
    public EsmName forClass(Class<?> clazz) {
        EsmName esmName = super.forClass(clazz);
        if (esmName != null)
            return esmName;

        if (clazz.isArray())
            return Skel01Modules.core.baseInfo.ARRAY;
        if (clazz.isEnum())
            return Skel01Modules.core.baseInfo.ENUM;

        return null;
    }

    public static final TsTypeInfos INSTANCE = new TsTypeInfos();

}
