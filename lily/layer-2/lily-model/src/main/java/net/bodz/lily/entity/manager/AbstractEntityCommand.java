package net.bodz.lily.entity.manager;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.bodz.bas.c.string.Strings;
import net.bodz.bas.db.ctx.DataContext;
import net.bodz.bas.db.ibatis.IEntityMapper;
import net.bodz.bas.db.ibatis.IGenericMapper;
import net.bodz.bas.err.IllegalUsageException;
import net.bodz.bas.err.LoaderException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.json.IJsonForm;
import net.bodz.bas.fmt.json.JsonFn;
import net.bodz.bas.fmt.json.JsonFormOptions;
import net.bodz.bas.json.JsonObject;
import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;
import net.bodz.bas.meta.decl.Priority;
import net.bodz.bas.repr.path.DefaultTokenProcessor;
import net.bodz.bas.repr.path.IPathArrival;
import net.bodz.bas.repr.path.ITokenQueue;
import net.bodz.bas.repr.path.PathArrival;
import net.bodz.bas.repr.path.PathDispatchException;
import net.bodz.bas.servlet.ctx.CurrentHttpService;
import net.bodz.bas.site.json.JsonResult;
import net.bodz.bas.t.tuple.Split;
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

    protected DataContext dataContext;
    protected ResolvedEntity resolvedEntity;
    protected JsonResult result;

    protected HttpServletRequest request;
    protected HttpServletResponse response;
    protected ITokenQueue tokens;
    protected int consumedTokenCount;
    protected IVariantMap<String> parameters;

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
    public ResolvedEntity getResolvedEntity() {
        return resolvedEntity;
    }

    @Override
    public void setResolvedEntity(ResolvedEntity resolvedEntity) {
        this.resolvedEntity = resolvedEntity;
    }

    @Override
    public boolean checkValid(IPathArrival previous, ITokenQueue tokens, IVariantMap<String> q)
            throws PathDispatchException {
        return true;
    }

    protected Boolean isReturningJsonResult() {
        return null;
    }

    @Override
    public synchronized IPathArrival dispatch(IPathArrival previous, ITokenQueue tokens, IVariantMap<String> q)
            throws PathDispatchException {
        if (!checkValid(previous, tokens, q))
            return null;

        this.request = CurrentHttpService.getRequest();
        this.response = CurrentHttpService.getResponse();

        this.tokens = new DefaultTokenProcessor(previous, tokens);
        this.consumedTokenCount = 0;
        this.parameters = q;

        if (result == null)
            result = new JsonResult();

        try {
            readObject(q);
        } catch (Exception e) {
            result.fail(e, "error parse parameters: " + e.getMessage());
            return null;
        }

        String contentTypeCharset = request.getContentType();
        String contentType = Split.chop(contentTypeCharset, ';').a;
        contentType = contentType == null ? "" : contentType.trim();

        if (this instanceof IJsonForm //
                && contentType.equalsIgnoreCase("application/json")) {
            String json;
            try {
                BufferedReader reader = request.getReader();
                StringBuilder sb = new StringBuilder();
                char[] cbuf = new char[4096];
                int cc;
                while ((cc = reader.read(cbuf)) != -1) {
                    sb.append(cbuf, 0, cc);
                }
                reader.close();
                json = sb.toString();
            } catch (IOException e) {
                throw new PathDispatchException("failed to read POST payload: " + e.getMessage(), e);
            }

            try {
                IJsonForm jsonForm = (IJsonForm) this;
                JsonObject jo = JsonFn.parseObject(json);
                jsonForm.jsonIn(jo, JsonFormOptions.WEB);
            } catch (ParseException e) {
                throw new PathDispatchException(String.format(//
                        "error parse json %s: %s", json, e.getMessage()), e);
            }
        }

        IPathArrival arrival = dispatchImpl(previous, tokens, q);
        cleanup();
        return arrival;
    }

    protected IPathArrival dispatchImpl(IPathArrival previous, ITokenQueue tokens, IVariantMap<String> q)
            throws PathDispatchException {
        Object target;
        try {
            target = execute();
        } catch (Exception e) {
            throw new PathDispatchException("error run the command: " + e.getMessage(), e);
        }

        PathArrival arrival = PathArrival.shift(consumedTokenCount, previous, this, target, tokens);

        Boolean returningJsonResult = isReturningJsonResult();
        if (returningJsonResult == null)
            returningJsonResult = !(target instanceof IJsonForm);

        if (returningJsonResult)
            arrival = PathArrival.shift(0, arrival, this, result, tokens);

        return arrival;
    }

    protected void cleanup() {
    }

    @Override
    public JsonResult getResult() {
        return result;
    }

    public void setResult(JsonResult result) {
        this.result = result;
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
