package net.bodz.lily.entity.manager;

import java.util.Map;

import net.bodz.bas.c.string.Strings;
import net.bodz.bas.db.ctx.DataContext;
import net.bodz.bas.db.ibatis.IEntityMapper;
import net.bodz.bas.db.ibatis.IGenericMapper;
import net.bodz.bas.err.IllegalUsageException;
import net.bodz.bas.err.LoaderException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;
import net.bodz.bas.meta.decl.Priority;
import net.bodz.bas.site.json.JsonResult;
import net.bodz.bas.t.variant.IVariantMap;
import net.bodz.lily.entity.IdFn;
import net.bodz.lily.entity.type.IEntityTypeInfo;

public abstract class AbstractEntityCommand
        implements
            IEntityCommand {

    static final Logger logger = LoggerFactory.getLogger(AbstractEntityCommand.class);

    private final int priority;
    private final String preferredName;
    protected final IEntityTypeInfo typeInfo;

    protected IEntityCommandContext context;
    protected DataContext dataContext;
    protected JsonResult resp;

    public AbstractEntityCommand(IEntityTypeInfo typeInfo) {
        Priority aPriority = getClass().getAnnotation(Priority.class);
        if (aPriority != null)
            priority = aPriority.value();
        else
            priority = 0;

        String name = getClass().getSimpleName();
        if (name.endsWith("Command"))
            name = name.substring(0, name.length() - 7);
        name = Strings.lcfirst(name);
        this.preferredName = name;
        this.typeInfo = typeInfo;
    }

    @Override
    public int getPriority() {
        return priority;
    }

    @Override
    public String getPreferredName() {
        return preferredName;
    }

    @Override
    public boolean isContentCommand() {
        return false;
    }

    @Override
    public DataContext getDataContext() {
        return dataContext;
    }

    @Override
    public void setDataContext(DataContext dataContext) {
        this.dataContext = dataContext;
    }

    @Override
    public synchronized Object execute(IEntityCommandContext executeContext) {
        this.context = executeContext;
        this.dataContext = context.getDataContext();
        this.resp = context.getResult();

        IVariantMap<String> parameters = context.getParameters();

        try {
            readObject(parameters);
        } catch (Exception e) {
            resp.fail(e, "error parse parameters: " + e.getMessage());
            return null;
        }

        try {
            if (!setUpVars()) {
                resp.fail("command is not enabled.");
                return null;
            }
        } catch (Exception e) {
            resp.fail(e, "failed before execute: " + e.getMessage());
            return null;
        }

        Object result;
        try {
            result = execute();

            if (result == null)
                return null;

            if (result != resp)
                resp.setData(result);

            return result;
        } catch (Exception e) {
            resp.fail(e, "failed to execute: " + e.getMessage());
            return null;
        } finally {
            try {
                afterExecute();
            } catch (Exception e) {
                logger.error(e, "error post-execute: " + e.getMessage());
            }
        }
    }

    /**
     * before parsing the parameters.
     */
    protected boolean setUpVars()
            throws Exception {
        return true;
    }

    protected abstract Object execute()
            throws Exception;

    protected void afterExecute()
            throws Exception {
    }

    protected <mapper_t> mapper_t mapper(Class<mapper_t> mapperClass) {
        return dataContext.getMapper(mapperClass);
    }

    @SuppressWarnings("unchecked")
    protected IGenericMapper<Object, Object> getGenericMapper() {
        Class<?> mapperClass = typeInfo.getMapperClass();
        if (IGenericMapper.class.isAssignableFrom(mapperClass))
            throw new IllegalUsageException("Not an " + IGenericMapper.class);
        return (IGenericMapper<Object, Object>) dataContext.getMapper(mapperClass);
    }

    @SuppressWarnings("unchecked")
    protected IEntityMapper<Object, Object> getEntityMapper() {
        Class<?> mapperClass = typeInfo.getMapperClass();
        if (!IEntityMapper.class.isAssignableFrom(mapperClass))
            throw new IllegalUsageException("Not an " + IEntityMapper.class);
        return (IEntityMapper<Object, Object>) dataContext.getMapper(mapperClass);
    }

    @Override
    public void readObject(IVariantMap<String> map)
            throws LoaderException, ParseException {
    }

    @Override
    public void writeObject(Map<String, Object> map) {
    }

    protected Object parseId(String idStr)
            throws ParseException {
        if (idStr == null) {
            return null;
        }

        Class<?> idType = typeInfo.getIdClass();
        if (idType == null)
            throw new IllegalUsageException("not id-capable: " + typeInfo.getEntityClass());

        Object id;
        try {
            id = IdFn.parseId(idType, idStr);
        } catch (ParseException e) {
            throw new ParseException(String.format(//
                    "error parse id \"%s\"): %s", idStr, e.getMessage()), e);
        }

        return id;
    }

}
