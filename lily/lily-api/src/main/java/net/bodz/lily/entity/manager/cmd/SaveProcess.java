package net.bodz.lily.entity.manager.cmd;

import java.io.IOException;

import net.bodz.bas.db.ibatis.IEntityMapper;
import net.bodz.bas.err.FormatException;
import net.bodz.bas.err.NoSuchKeyException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.json.IJsonForm;
import net.bodz.bas.fmt.json.IJsonOut;
import net.bodz.bas.fmt.json.JsonFormOptions;
import net.bodz.bas.json.JsonObject;
import net.bodz.bas.t.file.IPathFields;
import net.bodz.lily.concrete.StructRow;
import net.bodz.lily.entity.IId;
import net.bodz.lily.entity.manager.AbstractEntityCommandProcess;
import net.bodz.lily.entity.manager.IEntityCommandContext;
import net.bodz.lily.entity.manager.IEntityCommandType;
import net.bodz.lily.entity.manager.IJdbcRowOpListener;
import net.bodz.lily.entity.manager.JdbcRowOpEvent;
import net.bodz.lily.entity.manager.JdbcRowOpListeners;
import net.bodz.lily.entity.manager.JdbcRowOpType;
import net.bodz.lily.entity.manager.ResolvedEntity;
import net.bodz.lily.entity.manager.RowOpAwares;
import net.bodz.lily.entity.type.EntityOpListeners;

public class SaveProcess
        extends AbstractEntityCommandProcess
        implements IJsonForm {

    boolean createNew;
    boolean hasId;
    Object id;

    ResolvedEntity resolvedEntity;
    JsonObject contentJson;

    public SaveProcess(IEntityCommandType type, IEntityCommandContext context, ResolvedEntity resolvedEntity) {
        super(type, context);
        Class<?> entityClass = typeInfo.getEntityClass();
        hasId = IId.class.isAssignableFrom(entityClass);

        this.resolvedEntity = resolvedEntity;
    }

    @Override
    public void setCommandPath(IPathFields path) {
        super.setCommandPath(path);
        switch (path.toString()) {
            case "saveNew":
                createNew = true;
                break;
            case "update":
                createNew = false;
                break;
        }
    }

    @Override
    public ResolvedEntity getResolvedEntity() {
        return resolvedEntity;
    }

    @Override
    public Object execute()
            throws Exception {
        // 1. Prepare the object to be saved
        StructRow obj;
        if (resolvedEntity != null) {
            obj = resolvedEntity.entity;
        } else {
            if (createNew) {
                obj = (StructRow) typeInfo.newInstance();
            } else {
                obj = (StructRow) getEntityMapper().select(id);
                if (obj == null)
                    throw new NoSuchKeyException(String.format(//
                            "Invalid entity id: \"%s\"", id));
            }
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

        beforeRowOp(event, obj);
        EntityOpListeners.beforeUpdate(getClass(), jsonForm, createNew);

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

        result.setData(obj);

        return result.succeed();
    }

    boolean beforeRowOp(JdbcRowOpEvent event, Object obj)
            throws Exception {

        if (obj instanceof IJdbcRowOpListener) {
            IJdbcRowOpListener l = (IJdbcRowOpListener) obj;
            if (!l.beforeRowOperation(event, obj))
                return false;
        }

        if (!RowOpAwares.beforeRowOperation(event, obj))
            return false;

        EntityOpListeners.beforeUpdate(obj, !createNew);

        JdbcRowOpListeners.beforeRowOperation(event, obj);

        return true;
    }

    void afterRowOp(JdbcRowOpEvent event, Object obj)
            throws Exception {

        if (obj instanceof IJdbcRowOpListener) {
            IJdbcRowOpListener l = (IJdbcRowOpListener) obj;
            l.afterRowOperation(event, obj);
        }

        RowOpAwares.afterRowOperation(event, obj);

        EntityOpListeners.afterUpdate(obj, !createNew);

        JdbcRowOpListeners.afterRowOperation(event, obj);

    }

    @Override
    public void jsonIn(JsonObject o, JsonFormOptions opts)
            throws ParseException {
        if (resolvedEntity != null) {
            // createNew = undefined;
            // createNew = false;
        } else {
            if (hasId) {
                String[] names = typeInfo.getPrimaryKeyPropertyNames();
                String[] params = new String[names.length];
                for (int i = 0; i < names.length; i++) {
                    String property = names[i];
                    String value = o.getString(property);
                    if (value == null)
                        throw new IllegalArgumentException("pk-column[" + i + "] isn't specified.");
                    params[i] = value;
                }
                this.id = typeInfo.parseId(params);
                if (id == null)
                    this.createNew = true;
            } else {
                throw new UnsupportedOperationException("id isn't supported: " + typeInfo.getEntityClass());
            }
        }
        this.contentJson = o;
    }

    @Override
    public void jsonOut(IJsonOut out, JsonFormOptions opts)
            throws IOException, FormatException {
    }

}
