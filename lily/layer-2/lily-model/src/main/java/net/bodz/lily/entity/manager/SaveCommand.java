package net.bodz.lily.entity.manager;

import java.io.IOException;

import net.bodz.bas.db.ibatis.IEntityMapper;
import net.bodz.bas.err.FormatException;
import net.bodz.bas.err.LoaderException;
import net.bodz.bas.err.NoSuchKeyException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.json.IJsonForm;
import net.bodz.bas.fmt.json.IJsonOut;
import net.bodz.bas.fmt.json.JsonFormOptions;
import net.bodz.bas.json.JsonObject;
import net.bodz.bas.site.json.JsonResult;
import net.bodz.bas.t.variant.IVariantMap;
import net.bodz.lily.entity.IId;
import net.bodz.lily.entity.type.IEntityTypeInfo;

@ForEntityType(IJsonForm.class)
public class SaveCommand
        extends AbstractEntityCommand
        implements
            IJsonForm {

    boolean createNew;

    boolean hasId;
    Object id;

    JsonObject contentJson;

    CreateCommand createCommand;
    ResolveCommand resolveCommand;

    JsonResult resp = new JsonResult();

    public SaveCommand(IEntityTypeInfo typeInfo) {
        super(typeInfo);
        createCommand = new CreateCommand(typeInfo);
        resolveCommand = new ResolveCommand(typeInfo);
        Class<?> entityClass = typeInfo.getEntityClass();
        hasId = IId.class.isAssignableFrom(entityClass);
    }

    @Override
    protected Object execute()
            throws Exception {
        // 1. Prepare the object to be saved
        Object obj;
        if (createNew) {
            obj = createCommand.execute(context);
        } else {
            context.setAttribute("id", id);
            obj = resolveCommand.execute(context);
            if (obj == null)
                throw new NoSuchKeyException(String.format(//
                        "Invalid entity id: \"%s\"", id));
        }

        assert obj instanceof IJsonForm;
        IJsonForm jsonForm = (IJsonForm) obj;
        try {
            // JsonFormOptions opts = new JsonFormOptions(q);
            jsonForm.jsonIn(contentJson, JsonFormOptions.DEFAULT);
        } catch (ParseException e) {
            throw new ParseException("Failed to parse the content json: " + e.getMessage(), e);
        }

        // 2.
        JdbcRowOpEvent event = new JdbcRowOpEvent(this, //
                createNew ? JdbcRowOpType.CREATE : JdbcRowOpType.UPDATE);
        if (obj instanceof IJdbcRowOpListener) {
            IJdbcRowOpListener l = (IJdbcRowOpListener) obj;
            l.beforeRowOperation(event);
        }

        IEntityMapper<Object, Object> mapper = getEntityMapper();
        if (createNew) {
            mapper.insert(obj);
            if (obj instanceof IId<?>) {
                IId<?> entity = (IId<?>) obj;
                resp.getLogger().info("Inserted id: " + entity.id());
            }
        } else {
            long rows = mapper.update(obj);
            resp.setHeader("count", rows);
            resp.getLogger().info("Rows updated: " + rows);
        }

        if (obj instanceof IJdbcRowOpListener) {
            IJdbcRowOpListener l = (IJdbcRowOpListener) obj;
            l.afterRowOperation(event);
        }

        if (hasId) {
            IId<?> id_obj = (IId<?>) obj;
            resp.setHeader("id", id_obj.id());
        }
        resp.succeed();

        return resp;
    }

    @Override
    public void readObject(IVariantMap<String> map)
            throws LoaderException, ParseException {
        super.readObject(map);
    }

    @Override
    public void jsonIn(JsonObject o, JsonFormOptions opts)
            throws ParseException {
        String idStr = o.getString("id");
        if (idStr != null && this.id == null) {
            if (hasId) {
                this.id = parseId(idStr);
                this.createNew = id == null;
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

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder
            extends AbstractEntityCommandBuilder<Builder> {

        public Builder() {
            super(SaveCommand.class);
        }

        @Override
        public SaveCommand build() {
            return new SaveCommand(typeInfo);
        }
    }

}
