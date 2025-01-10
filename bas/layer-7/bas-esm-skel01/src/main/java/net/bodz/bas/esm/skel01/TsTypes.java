package net.bodz.bas.esm.skel01;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.InetAddress;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.OffsetTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;

import net.bodz.bas.esm.EsmName;
import net.bodz.bas.esm.ITsTypeProvider;
import net.bodz.bas.esm.TsTypeMap;
import net.bodz.bas.esm.skel01.Core.BasType;
import net.bodz.bas.esm.skel01.Core.BaseType;
import net.bodz.bas.esm.skel01.Core.LangTime;
import net.bodz.bas.fmt.json.JsonVariant;
import net.bodz.bas.repr.state.State;
import net.bodz.bas.site.json.JsonMap;
import net.bodz.bas.t.variant.IVariant;
import net.bodz.bas.t.variant.MutableVariant;

public class TsTypes
        extends TsTypeMap
        implements
            ITsTypeProvider {

    {
        BaseType baseType = Skel01Modules.core.baseType;
        BasType basType = Skel01Modules.core.basType;
        LangTime time = Skel01Modules.core.time;

        addType(byte.class, baseType._byte);
        addType(short.class, baseType._short);
        addType(int.class, baseType._int);
        addType(long.class, baseType._long);
        addType(float.class, baseType._float);
        addType(double.class, baseType._double);
        addType(boolean.class, baseType._boolean);
        addType(char.class, baseType._char);

        addType(Byte.class, baseType._byte);
        addType(Short.class, baseType._short);
        addType(Integer.class, baseType._int);
        addType(Long.class, baseType._long);
        addType(Float.class, baseType._float);
        addType(Double.class, baseType._double);
        addType(Boolean.class, baseType._boolean);
        addType(Character.class, baseType._char);
        addType(String.class, baseType._string);

        addType(BigInteger.class, baseType.BigInteger);
        addType(BigDecimal.class, baseType.BigDecimal);

        addAbstractType(List.class, baseType.List);
//        addAbstractType(Set.class, baseType._Set);
//        addAbstractType(Map.class, baseType._Map);

        addAbstractType(InetAddress.class, baseType.InetAddress);

        addType(JsonVariant.class, basType.JsonVariant);
        addType(JsonMap.class, basType.JsonVariant);

        addAbstractType(IVariant.class, basType.JsonVariant);
        addType(MutableVariant.class, basType.JsonVariant);
//        addType(MutableVariant.class, baseType.any);

        addType(Date.class, time.JavaDate);
        addType(java.sql.Date.class, time.SQLDate);
        addType(Time.class, time.SQLTime);
        addType(Timestamp.class, time.Timestamp);

        addType(Instant.class, time.Instant);
        addType(LocalDate.class, time.LocalDate);
        addType(LocalTime.class, time.LocalTime);
        addType(LocalDateTime.class, time.LocalDateTime);
        addType(OffsetTime.class, time.OffsetTime);
        addType(OffsetDateTime.class, time.OffsetDateTime);
        addType(ZonedDateTime.class, time.ZonedDateTime);

        addType(ZoneId.class, baseType._string);

        addType(State.class, Skel01Modules.core.state.DefaultState);
    }

    @Override
    public EsmName forClass(Class<?> clazz) {
        EsmName esmName = super.forClass(clazz);
        if (esmName != null)
            return esmName;

        if (clazz.isEnum())
            return Skel01Modules.core.baseType.Enum;

        return null;
    }

    public static final TsTypes INSTANCE = new TsTypes();

}
