package net.bodz.lily.entity.manager;

import net.bodz.bas.content.IReset;
import net.bodz.bas.content.ResetPolicy;
import net.bodz.bas.err.LoaderException;
import net.bodz.bas.err.NoSuchKeyException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.json.IJsonForm;
import net.bodz.bas.fmt.json.JsonFormOptions;
import net.bodz.bas.site.json.JsonWrapper;
import net.bodz.bas.t.object.ICloneable;
import net.bodz.bas.t.variant.IVariantMap;
import net.bodz.lily.entity.IId;
import net.bodz.lily.entity.ws.RequestHandlerException;

@ForEntityType(IJsonForm.class)
public class GetDefaultCommand
        extends AbstractEntityCommandType {

    public static final String NAME = "default";
    public static final String[] NAMES = { NAME, "new" };

    @Override
    public String getPreferredName() {
        return NAME;
    }

    @Override
    public String[] getCommandNames() {
        return NAMES;
    }

    @Override
    public CreateProcess createProcess(IEntityCommandContext context) {
        return new CreateProcess(this, context);
    }

}

class CreateProcess
        extends AbstractEntityCommandProcess<GetDefaultCommand> {

    final boolean cloneable;
    final boolean resetable;

    Object templateId;
    JsonFormOptions jsonFormOptions;

    public CreateProcess(GetDefaultCommand type, IEntityCommandContext context) {
        super(type, context);

        Class<?> entityClass = context.getTypeInfo().getEntityClass();
        cloneable = ICloneable.class.isAssignableFrom(entityClass);
        resetable = IReset.class.isAssignableFrom(entityClass);
    }

    public boolean isTemplateSupported() {
        return cloneable && resetable;
    }

    public Object getTemplateId() {
        return templateId;
    }

    public void setTemplateId(Object templateId) {
        this.templateId = templateId;
    }

    public String getTemplateIdString() {
        return templateId == null ? null : templateId.toString();
    }

    public void setTemplateIdString(String idStr) {
        try {
            templateId = context.parseId(idStr);
        } catch (ParseException e) {
            throw new IllegalArgumentException(e.getMessage(), e);
        }
    }

    @Override
    public Object execute()
            throws Exception {
        Object obj = null;

        if (templateId != null && cloneable) {
            Object template = getEntityMapper().select(templateId);
            if (template != null) {
                ICloneable c = (ICloneable) template;
                IReset copy = (IReset) c.clone();

                copy.reset(ResetPolicy.CLEAN.getContentSelection());
                obj = copy;
            } else
                throw new NoSuchKeyException("template id isn't existed: " + templateId);
        }

        if (obj == null)
            try {
                obj = create();
            } catch (ReflectiveOperationException e) {
                throw new RequestHandlerException("failed to create: " + e.getMessage(), e);
            }

        JsonWrapper wrapper = JsonWrapper.wrap(obj, "data");
        wrapper.setOptions(jsonFormOptions);
        return wrapper;
    }

    protected Object create()
            throws ReflectiveOperationException {
        Class<?> clazz = typeInfo.getEntityClass();
        IId<?> instance = (IId<?>) clazz.getConstructor().newInstance();
        return instance;
    }

    @Override
    public void readObject(IVariantMap<String> map)
            throws LoaderException, ParseException {
        super.readObject(map);

        String idStr = map.getString("templateId");
        if (idStr != null)
            setTemplateIdString(idStr);

        jsonFormOptions = new JsonFormOptions();
        jsonFormOptions.readObject(map);
    }

}
