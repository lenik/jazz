package net.bodz.lily.model.base;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.bodz.bas.c.java.io.FilePath;
import net.bodz.bas.c.string.StringPred;
import net.bodz.bas.c.type.TypeParam;
import net.bodz.bas.db.ctx.DataContext;
import net.bodz.bas.db.ctx.IDataContextAware;
import net.bodz.bas.db.ibatis.IMapper;
import net.bodz.bas.db.ibatis.IMapperTemplate;
import net.bodz.bas.err.LoadException;
import net.bodz.bas.err.LoaderException;
import net.bodz.bas.fmt.json.JsonObject;
import net.bodz.bas.html.viz.IHtmlViewContext;
import net.bodz.bas.html.viz.IPathArrivalFrameAware;
import net.bodz.bas.html.viz.PathArrivalFrame;
import net.bodz.bas.http.ctx.CurrentHttpService;
import net.bodz.bas.i18n.dom.iString;
import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;
import net.bodz.bas.meta.codegen.IndexedType;
import net.bodz.bas.meta.decl.ObjectType;
import net.bodz.bas.potato.PotatoTypes;
import net.bodz.bas.potato.element.IType;
import net.bodz.bas.repr.path.IPathArrival;
import net.bodz.bas.repr.path.IPathDispatchable;
import net.bodz.bas.repr.path.ITokenQueue;
import net.bodz.bas.repr.path.PathArrival;
import net.bodz.bas.repr.path.PathDispatchException;
import net.bodz.bas.site.ajax.AjaxResult;
import net.bodz.bas.site.ajax.HttpPayload;
import net.bodz.bas.site.json.JsonVarMap;
import net.bodz.bas.site.json.JsonWrapper;
import net.bodz.bas.site.json.TableData;
import net.bodz.bas.site.vhost.VirtualHostScope;
import net.bodz.bas.std.rfc.http.AbstractCacheControl;
import net.bodz.bas.std.rfc.http.CacheControlMode;
import net.bodz.bas.std.rfc.http.CacheRevalidationMode;
import net.bodz.bas.std.rfc.http.ICacheControl;
import net.bodz.bas.t.variant.IVariantMap;
import net.bodz.lily.entity.ILazyId;
import net.bodz.lily.entity.Instantiables;
import net.bodz.lily.security.AccessControl;

@AccessControl
@IndexedType
@VirtualHostScope
public class CoIndex<T extends CoObject, M extends CoObjectMask>
        extends AbstractCacheControl
        implements IPathDispatchable, IPathArrivalFrameAware, ICacheControl, IDataContextAware {

    static final Logger logger = LoggerFactory.getLogger(CoIndex.class);

    private Class<T> objectType;
    private Class<M> maskType;
    protected DataContext dataContext;

    public CoIndex() {
        ObjectType aObjectType = getClass().getAnnotation(ObjectType.class);
        if (aObjectType != null)
            objectType = (Class<T>) aObjectType.value();
        else
            objectType = TypeParam.infer1(getClass(), CoIndex.class, 0);
        maskType = TypeParam.infer1(getClass(), CoIndex.class, 1);
    }

    @Override
    public CacheControlMode getCacheControlMode() {
        return CacheControlMode.AUTO;
    }

    @Override
    public CacheRevalidationMode getCacheRevalidationMode() {
        return CacheRevalidationMode.MUST_REVALIDATE;
    }

    public Class<T> getObjectType() {
        return objectType;
    }

    @Override
    public DataContext getDataContext() {
        if (dataContext == null)
            throw new IllegalStateException("DataContext isn't set.");
        return dataContext;
    }

    @Override
    public void setDataContext(DataContext dataContext) {
        this.dataContext = dataContext;
        if (dataContext != null)
            afterDataContextSet();
    }

    protected void afterDataContextSet() {
    }

    @Override
    public IPathArrival dispatch(IPathArrival previous, ITokenQueue tokens, IVariantMap<String> q)
            throws PathDispatchException {
        HttpServletRequest req = CurrentHttpService.getRequest();

        String token = tokens.peek();
        if (token == null)
            return null;

        Object target = null;
        try {
            switch (token) {
            case "__data__":
                target = listHandler(q);
                break;
            case "delete":
                target = deleteHandler(q);
                break;
            case "new":
                target = newHandler(q);
                break;
            case "save":
                JsonObject json = HttpPayload.getJsonPayload(req);
                target = saveHandler(q, json);
                break;
            default:
                String fileName = FilePath.stripExtension(token);
                if (StringPred.isDecimal(fileName))
                    target = loadHandler(fileName, q);
            }
        } catch (LoadException e) {
            throw new PathDispatchException("Failed to read request payload: " + e.getMessage(), e);
        } catch (RequestHandlerException e) {
            throw new PathDispatchException("Failed to handle request: " + e.getMessage(), e);
        }

        if (target != null)
            return PathArrival.shift(previous, target, tokens);
        return null;
    }

    @Override
    public void enter(IHtmlViewContext ctx, PathArrivalFrame frame) {
        ctx.setVariable("index", this);
    }

    @Override
    public void leave(IHtmlViewContext ctx, PathArrivalFrame frame) {
        ctx.setVariable("index", null);
    }

    @Override
    public String toString() {
        IType type = PotatoTypes.getInstance().loadType(getClass());
        iString label = type.getLabel();
        if (label == null) {
            // logger.warn("Index title (label) isn't specified on " + getClass());
            return getClass().getName();
        }
        return label + "/";
    }

    protected Object listHandler(IVariantMap<String> q)
            throws RequestHandlerException {
        TableData tableData = new TableData(objectType);
        try {
            tableData.readObject(q);
        } catch (Exception e) {
            throw new RequestHandlerException(e.getMessage(), e);
        }

        try {
            M mask = maskType.newInstance();
            mask.readObject(q);
            List<T> list = buildDataList(q, mask);
            tableData.setList(list);
        } catch (ReflectiveOperationException e) {
            throw new RequestHandlerException("Error instantiate mask of " + maskType, e);
        } catch (LoaderException e) {
            throw new RequestHandlerException("Error decode mask of " + maskType, e);
        }
        return tableData;
    }

    protected Object newHandler(IVariantMap<String> q)
            throws RequestHandlerException {
        try {
            T obj = create();
            JsonWrapper wrapper = JsonWrapper.wrap(obj, "data").params(q).withNull().withFalse();
            return wrapper;
        } catch (Exception e) {
            throw new RequestHandlerException(e.getMessage(), e);
        }
    }

    protected Object loadHandler(String _id, IVariantMap<String> q) {
        Long id = Long.parseLong(_id);
        IMapperTemplate<T, M> mapper = requireMapper();
        Object obj = mapper.select(id);
        JsonWrapper wrapper = JsonWrapper.wrap(obj, "data").params(q).withNull().withFalse();
        return wrapper;
    }

    protected AjaxResult saveHandler(IVariantMap<String> q, JsonObject content)
            throws RequestHandlerException {
        AjaxResult result = new AjaxResult();

        T obj;
        Long id = content.getLong("id");
        boolean create = id == null;
        if (create) {
            try {
                obj = create();
            } catch (ReflectiveOperationException e) {
                throw new RequestHandlerException("Failed to instantiate: " + e.getMessage(), e);
            }
        } else {
            assert id != null;
            obj = load(id);
        }

        JsonVarMap contentMap = new JsonVarMap(content);
        try {
            obj.readObject(contentMap);
        } catch (LoaderException e) {
            throw new RequestHandlerException("Failed to apply json: " + e.getMessage(), e);
        }

        save(contentMap, obj, result);
        return result;
    }

    protected Object deleteHandler(IVariantMap<String> q)
            throws RequestHandlerException {
        AjaxResult result = new AjaxResult();
        String ids = q.getString("id");
        if (ids == null) {
            result.fail("Id isn't specified.");
        } else {
            IMapperTemplate<T, M> mapper = requireMapper();
            StringBuilder fails = new StringBuilder();
            result.setSuccess(true);
            for (String idStr : ids.split(",")) {
                try {
                    long id = Long.parseLong(idStr);
                    if (!mapper.delete(id))
                        fails.append(idStr + ",");
                } catch (NumberFormatException e) {
                    fails.append(idStr + ",");
                }
            }
            if (fails.length() > 0) {
                fails.setLength(fails.length() - 1);
                result.fail("Not deleted: " + fails);
            }
        }
        return result;
    }

    protected <mapper_t extends IMapperTemplate<T, M>> mapper_t requireMapper() {
        Class<T> entityClass = getObjectType();
        Class<IMapperTemplate<T, M>> mapperClass = IMapper.fn.requireMapperClass(entityClass);
        return (mapper_t) getDataContext().requireMapper(mapperClass);
    }

    protected T create()
            throws ReflectiveOperationException {
        T instance = Instantiables._instantiate(getObjectType());
        return instance;
    }

    protected T load(long id) {
        IMapperTemplate<T, M> mapper = requireMapper();
        T instance = mapper.select(id);
        return instance;
    }

    protected void save(IVariantMap<String> q, T obj, AjaxResult result) {
        _save(obj, result);
    }

    private void _save(T obj, AjaxResult result) {
        IMapperTemplate<T, M> mapper = requireMapper();
        boolean create = obj.getId() == null;
        if (create) {
            mapper.insert(obj);
            result.println("Inserted id: " + obj.getId());
        } else {
            long rows = mapper.update(obj);
            result.println("Rows updated: " + rows);
        }
    }

    protected ILazyId lazyId(T obj) {
        return new LazyId(obj);
    }

    class LazyId
            implements ILazyId {

        T obj;

        public LazyId(T obj) {
            this.obj = obj;
        }

        @Override
        public Object require() {
            Object id = obj.getId();
            if (id == null) {
                IMapperTemplate<T, M> mapper = requireMapper();
                mapper.insert(obj);
                id = obj.getId();
            }
            return id;
        }

    }

    protected List<T> buildDataList(IVariantMap<String> q, M mask) {
        List<T> list = requireMapper().filter(mask);
        return list;
    }

}
