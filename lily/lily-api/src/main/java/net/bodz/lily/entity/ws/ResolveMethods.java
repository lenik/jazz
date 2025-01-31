package net.bodz.lily.entity.ws;

import java.util.List;

import net.bodz.bas.c.java.util.Arrays;
import net.bodz.bas.c.string.StringArray;
import net.bodz.bas.db.ibatis.IEntityMapper;
import net.bodz.bas.db.ibatis.sql.SelectOptions;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;
import net.bodz.bas.repr.path.IPathArrival;
import net.bodz.bas.repr.path.ITokenQueue;
import net.bodz.bas.repr.path.PathArrival;
import net.bodz.bas.repr.path.PathDispatchException;
import net.bodz.bas.t.tuple.Split;
import net.bodz.bas.t.variant.IVariantMap;
import net.bodz.lily.concrete.StructRow;
import net.bodz.lily.criteria.FieldCriterionBuilder;
import net.bodz.lily.criteria.ICriteriaBuilder;
import net.bodz.lily.entity.manager.ResolvedEntity;
import net.bodz.lily.entity.type.IEntityTypeInfo;

public class ResolveMethods<T> {

    static final Logger logger = LoggerFactory.getLogger(ResolveMethods.class);

    IEntityTypeInfo typeInfo;
    IEntityMapper<T> mapper;

    public ResolveMethods(IEntityTypeInfo typeInfo, IEntityMapper<T> mapper) {
        this.typeInfo = typeInfo;
        this.mapper = mapper;
    }

    public ResolvedEntity resolveIdPath(ITokenQueue tokens)
            throws PathDispatchException {
        int idColumnCount = typeInfo.getIdColumnCount();
        assert idColumnCount >= 1;

        String[] heads = tokens.peek(idColumnCount);
        if (heads == null)
            return null;

        for (String head : heads)
            if (head.contains("=")) // needs to be escaped.
                return null;
        // unescape =

        Split name_ext = Split.nameExtension(heads[idColumnCount - 1]);
        heads[idColumnCount - 1] = name_ext.a;

        Object[] idvec;
        try {
            idvec = typeInfo.parseIdColumns(heads);
        } catch (ParseException e) {
            return null;
//            throw new PathDispatchException(String.format(//
//                    "error parse id fields %s: %s", //
//                    Arrays.asList(heads), e.getMessage()), e);
        }

        Object id;
        try {
            id = typeInfo.newId(idvec);
        } catch (ReflectiveOperationException e) {
            throw new PathDispatchException(String.format(//
                    "error create id from %s: %s", //
                    Arrays.asList(idvec), e.getMessage()), e);
        }

        ResolvedEntity target = new ResolvedEntity();
        target.consumedTokenCount = heads.length;
        target.idFieldStrings = heads;
        target.idFields = idvec;
        target.id = id;
        target.preferredExtension = name_ext.b;
        return target;
    }

    public IPathArrival byIdPath(IPathArrival previous, ITokenQueue tokens, IVariantMap<String> q)
            throws PathDispatchException {
        ResolvedEntity target = resolveIdPath(tokens);
        if (target == null)
            return null;

        Object id = target.id;
        T obj;
        try {
            obj = mapper.select(id);
        } catch (Exception e) {
            String err = String.format("Error select %s from %s: %s", //
                    id, typeInfo.getEntityClass().getSimpleName(), e.getMessage());
            logger.error(e, err);
            return null;
        }

        if (obj == null)
            return null;

        // if (tokens.available() > idColumnCount)
        // return PathArrival.shift(idColumnCount, previous, this, obj, tokens);

        target.entity = (StructRow) obj;
        return PathArrival.shift(target.consumedTokenCount, previous, this, target, tokens);
    }

    public IPathArrival byFieldPath(IPathArrival previous, ITokenQueue tokens, IVariantMap<String> q)
            throws PathDispatchException {
        ICriteriaBuilder<?> criteria = typeInfo.newCriteriaBuilder();

        int offset = 0;
        int available = tokens.available();
        while (offset < available) {
            String token = tokens.peekAhead(offset);
            if (! token.contains("="))
                break;
            offset++;
        }
        if (offset == 0)
            return null;

        String[] tokenv = tokens.peek(offset);
        String extension = null;
        for (int i = 0; i < offset; i++) {
            String token = tokenv[i];

            int eq = token.indexOf('=');
            String fieldName = token.substring(0, eq);
            String fieldStr = token.substring(eq + 1);

            if (i == offset - 1) {
                int dot = fieldStr.lastIndexOf("."); // dot(.) needs to be escaped.
                if (dot != -1) {
                    extension = fieldStr.substring(dot + 1);
                    fieldStr = fieldStr.substring(0, dot);
                }
            }

            FieldCriterionBuilder<?, ?, Object> field = criteria.getField(fieldName);
            if (field == null)
                throw new IllegalArgumentException("Undefined field name: " + fieldName);

            try {
                Object fieldVal = field.parse(fieldStr);
                field.eq(fieldVal);
            } catch (ParseException e) {
                return null;
            }
        }

        ResolvedEntity target = new ResolvedEntity();
        target.consumedTokenCount = offset;
        target.preferredExtension = extension;

        List<T> list;
        try {
            list = mapper.filter(criteria.get(), SelectOptions.TOP1);
        } catch (Exception e) {
            String fieldPath = StringArray.join("/", tokenv);
            String err = String.format("Error select %s from %s: %s", //
                    fieldPath, typeInfo.getEntityClass().getSimpleName(), e.getMessage());
            logger.error(e, err);
            return null;
        }

        if (list.isEmpty())
            return null;
        Object obj = list.get(0);

        // if (tokens.available() > idColumnCount)
        // return PathArrival.shift(idColumnCount, previous, this, obj, tokens);

        target.entity = (StructRow) obj;
        return PathArrival.shift(target.consumedTokenCount, previous, this, target, tokens);
    }

}
