package net.bodz.lily.entity.manager;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.bas.db.ctx.IDataContextAware;
import net.bodz.bas.db.ibatis.IEntityMapper;
import net.bodz.bas.db.ibatis.IGenericMapper;
import net.bodz.bas.err.IllegalUsageException;
import net.bodz.bas.err.LoaderException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.json.IJsonForm;
import net.bodz.bas.fmt.json.JsonFn;
import net.bodz.bas.fmt.json.JsonFormOptions;
import net.bodz.bas.html.servlet.NoRender;
import net.bodz.bas.json.JsonObject;
import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;
import net.bodz.bas.repr.path.DefaultTokenProcessor;
import net.bodz.bas.repr.path.IPathArrival;
import net.bodz.bas.repr.path.ITokenQueue;
import net.bodz.bas.repr.path.PathArrival;
import net.bodz.bas.repr.path.PathDispatchException;
import net.bodz.bas.servlet.ctx.CurrentHttpService;
import net.bodz.bas.site.json.JsonResult;
import net.bodz.bas.t.tuple.Split;
import net.bodz.bas.t.variant.IVariantMap;
import net.bodz.lily.app.IDataApplication;
import net.bodz.lily.entity.type.IEntityTypeInfo;

public abstract class AbstractEntityCommandProcess<type_t extends IEntityCommandType>
        implements
            IDataContextAware,
            IEntityCommandProcess {

    static final Logger logger = LoggerFactory.getLogger(AbstractEntityCommandProcess.class);

    protected final type_t type;
    protected IEntityTypeInfo typeInfo;

    protected final IEntityCommandContext context;
    protected final IDataApplication dataApp;
    protected DataContext dataContext;
    protected String[] pathInfo;
    protected ResolvedEntity resolvedEntity;
    protected JsonResult result;

    protected HttpServletRequest request;
    protected HttpServletResponse response;
    protected ITokenQueue tokens;
    protected int consumedTokenCount;
    protected IVariantMap<String> parameters;

    public AbstractEntityCommandProcess(type_t type, IEntityCommandContext context) {
        this.type = type;
        this.typeInfo = context.getTypeInfo();

        if (type == null)
            throw new NullPointerException("type");
        if (typeInfo == null)
            throw new NullPointerException("typeInfo");

        this.context = context;
        this.dataApp = context.getDataApp();
        this.dataContext = dataApp.getDataContext();

        this.resolvedEntity = context.getResolvedEntity();
    }

    public type_t getCommandType() {
        return type;
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
    public String[] getPathInfo() {
        return pathInfo;
    }

    @Override
    public void setPathInfo(String[] names) {
        this.pathInfo = names;
    }

    @Override
    public ResolvedEntity getResolvedEntity() {
        return resolvedEntity;
    }

    protected Boolean isReturningJsonResult() {
        return null;
    }

    protected void cleanup() {
    }

    @Override
    public synchronized IPathArrival dispatch(IPathArrival previous, ITokenQueue tokens, IVariantMap<String> q)
            throws PathDispatchException {
        if (!type.checkPathValid(previous, tokens, q))
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
        if (target == NoRender.INSTANCE)
            return arrival;

        Boolean returningJsonResult = isReturningJsonResult();
        if (returningJsonResult == null) {
            if (target instanceof IJsonForm)
                returningJsonResult = false;
            else
                returningJsonResult = true;
        }

        if (returningJsonResult)
            arrival = PathArrival.shift(0, arrival, this, result, tokens);

        return arrival;
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

}
