package net.bodz.lily.entity.manager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.bodz.bas.c.type.IndexedTypes;
import net.bodz.bas.db.ibatis.IEntityMapper;
import net.bodz.bas.err.FormatException;
import net.bodz.bas.err.NoSuchKeyException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.json.IJsonForm;
import net.bodz.bas.fmt.json.IJsonOut;
import net.bodz.bas.fmt.json.JsonFormOptions;
import net.bodz.bas.json.JsonObject;
import net.bodz.lily.concrete.StructRow;
import net.bodz.lily.entity.IId;

public class SaveProcess
        extends AbstractEntityCommandProcess<SaveCommand>
        implements
            IJsonForm {

    boolean createNew;

    boolean hasId;
    Object id;

    JsonObject contentJson;

    public SaveProcess(SaveCommand type, IEntityCommandContext context, ResolvedEntity resolvedEntity) {
        super(type, context, resolvedEntity);
        Class<?> entityClass = typeInfo.getEntityClass();
        hasId = IId.class.isAssignableFrom(entityClass);
    }

    @Override
    public Object execute()
            throws Exception {
        // 1. Prepare the object to be saved
        StructRow obj;
        if (createNew) {
            obj = (StructRow) typeInfo.getEntityClass().getConstructor().newInstance();
        } else {
            obj = (StructRow) getEntityMapper().select(id);
            if (obj == null)
                throw new NoSuchKeyException(String.format(//
                        "Invalid entity id: \"%s\"", id));
        }

        IJsonForm jsonForm = obj;
        try {
            // JsonFormOptions opts = new JsonFormOptions(q);
            jsonForm.jsonIn(contentJson, JsonFormOptions.DEFAULT);
        } catch (ParseException e) {
            throw new ParseException("Failed to parse the content json: " + e.getMessage(), e);
        }
        obj.touch();

        // 2.
        JdbcRowOpEvent event = new JdbcRowOpEvent(this, //
                createNew ? JdbcRowOpType.CREATE : JdbcRowOpType.UPDATE);

        RowOpListeners aRowOps = obj.getClass().getAnnotation(RowOpListeners.class);
        if (aRowOps != null) {
            for (Class<? extends IJdbcRowOpListener> handlerClass : aRowOps.value()) {
                IJdbcRowOpListener handler = handlerClass.getConstructor(IId.class).newInstance(obj);
                if (! handler.beforeRowOperation(event))
                    return false;
            }
        }
        beforeRowOp(event, obj);

        IEntityMapper<Object> mapper = getEntityMapper();
        if (createNew) {
            mapper.insert(obj);
            if (obj instanceof IId<?>) {
                IId<?> entity = (IId<?>) obj;
                id = entity.id();
                result.getLogger().info("Inserted id: " + id);
            }
        } else {
            long rows = mapper.update(obj);
            result.setHeader("count", rows);
            result.getLogger().info("Rows updated: " + rows);
        }

        afterRowOp(event, obj);

        if (hasId)
            result.setHeader("id", id);

        return result.succeed();
    }

    static List<Class<? extends IJdbcRowOpListener>> globalHandlerClasses;
    static boolean enableRowOps = true;
    static {
        globalHandlerClasses = new ArrayList<>();
        if (enableRowOps)
            for (Class<? extends RowOpListeners> c : IndexedTypes.list(RowOpListeners.class, true)) {
                RowOpListeners aRowOps = c.getAnnotation(RowOpListeners.class);
                for (Class<? extends IJdbcRowOpListener> handlerClass : aRowOps.value())
                    globalHandlerClasses.add(handlerClass);
            }
    }

    boolean beforeRowOp(JdbcRowOpEvent event, Object obj)
            throws Exception {
        if (obj instanceof IJdbcRowOpListener) {
            IJdbcRowOpListener l = (IJdbcRowOpListener) obj;
            if (! l.beforeRowOperation(event))
                return false;
        }
        for (Class<? extends IJdbcRowOpListener> c : globalHandlerClasses) {
            IJdbcRowOpListener handler = c.getConstructor(IId.class).newInstance(obj);
            if (! handler.beforeRowOperation(event))
                return false;
        }
        return true;
    }

    void afterRowOp(JdbcRowOpEvent event, Object obj)
            throws Exception {
        if (obj instanceof IJdbcRowOpListener) {
            IJdbcRowOpListener l = (IJdbcRowOpListener) obj;
            l.afterRowOperation(event);
        }
        for (Class<? extends IJdbcRowOpListener> c : globalHandlerClasses) {
            IJdbcRowOpListener handler = c.getConstructor(IId.class).newInstance(obj);
            handler.afterRowOperation(event);
        }
    }

    @Override
    public void jsonIn(JsonObject o, JsonFormOptions opts)
            throws ParseException {
        if (hasId) {
            String idStr = o.getString("id");
            this.id = typeInfo.parseSimpleId(idStr);
            this.createNew = id == null;
        } else {
            throw new UnsupportedOperationException("id isn't supported: " + typeInfo.getEntityClass());
        }
        this.contentJson = o;
    }

    @Override
    public void jsonOut(IJsonOut out, JsonFormOptions opts)
            throws IOException, FormatException {
    }

}
