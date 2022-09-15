package net.bodz.lily.model.base;

import java.util.List;

import net.bodz.bas.c.type.TypeParam;
import net.bodz.bas.db.ctx.DataContext;
import net.bodz.bas.db.ctx.IDataContextAware;
import net.bodz.bas.db.ibatis.IGenericMapper;
import net.bodz.bas.db.ibatis.IMapper;
import net.bodz.bas.db.ibatis.sql.SelectOptions;
import net.bodz.bas.err.IllegalUsageException;
import net.bodz.bas.err.LoadException;
import net.bodz.bas.err.LoaderException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.html.viz.IHtmlViewContext;
import net.bodz.bas.html.viz.IPathArrivalFrameAware;
import net.bodz.bas.html.viz.PathArrivalFrame;
import net.bodz.bas.i18n.dom.iString;
import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;
import net.bodz.bas.meta.decl.ObjectType;
import net.bodz.bas.potato.PotatoTypes;
import net.bodz.bas.potato.element.IType;
import net.bodz.bas.repr.path.IPathArrival;
import net.bodz.bas.repr.path.IPathDispatchable;
import net.bodz.bas.repr.path.ITokenQueue;
import net.bodz.bas.repr.path.PathArrival;
import net.bodz.bas.repr.path.PathDispatchException;
import net.bodz.bas.site.json.JsonResult;
import net.bodz.bas.site.json.JsonWrapper;
import net.bodz.bas.site.json.TableOfPathProps;
import net.bodz.bas.site.vhost.VirtualHostScope;
import net.bodz.bas.std.rfc.http.AbstractCacheControl;
import net.bodz.bas.std.rfc.http.CacheControlMode;
import net.bodz.bas.std.rfc.http.CacheRevalidationMode;
import net.bodz.bas.std.rfc.http.ICacheControl;
import net.bodz.bas.t.variant.IVarMapForm;
import net.bodz.bas.t.variant.IVariantMap;
import net.bodz.lily.entity.Instantiables;
import net.bodz.lily.security.AccessControl;

@AccessControl
@VirtualHostScope
public abstract class GenericIndex<T, M extends IVarMapForm>
        extends AbstractCacheControl
        implements
            IPathDispatchable,
            IPathArrivalFrameAware,
            ICacheControl,
            IDataContextAware {

    static final Logger logger = LoggerFactory.getLogger(GenericIndex.class);

    private Class<T> objectType;
    private Class<M> maskType;
    protected DataContext dataContext;

    public GenericIndex() {
        ObjectType aObjectType = getClass().getAnnotation(ObjectType.class);
        if (aObjectType != null) {
            @SuppressWarnings("unchecked")
            Class<T> objectType = (Class<T>) aObjectType.value();
            this.objectType = objectType;
        } else {
            objectType = TypeParam.infer1(getClass(), GenericIndex.class, 0);
        }
        maskType = TypeParam.infer1(getClass(), GenericIndex.class, 1);
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
        String token = tokens.peek();
        if (token == null)
            return null;

        Object target = null;
        try {
            switch (token) {
            case "__data__":
                try {
                    target = listHandler(q);
                } catch (RequestHandlerException e) {
                    target = new JsonResult().fail(e, "Failed to handle list request: " + e.getMessage());
                }
                break;
            case "__count__":
                try {
                    target = countHandler(q);
                } catch (RequestHandlerException e) {
                    target = new JsonResult().fail(e, "Failed to handle list request: " + e.getMessage());
                }
                break;
            default:
            }
        } catch (LoadException e) {
            throw new PathDispatchException("Failed to read request payload: " + e.getMessage(), e);
        }

        if (target != null)
            return PathArrival.shift(previous, this, target, tokens);
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
            return getClass().getCanonicalName();
        }
        return label + "/";
    }

    protected TableOfPathProps listHandler(IVariantMap<String> q)
            throws RequestHandlerException {
        TableOfPathProps tableData = new TableOfPathProps(objectType);
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

            if (tableData.isWantTotalCount()) {
                long totalCount = _requireMapper().count(mask);
                tableData.setTotalCount(totalCount);
            }
        } catch (ReflectiveOperationException e) {
            throw new RequestHandlerException("Error instantiate mask of " + maskType, e);
        } catch (LoaderException | ParseException e) {
            throw new RequestHandlerException("Error decode mask of " + maskType, e);
        }
        return tableData;
    }

    protected JsonWrapper countHandler(IVariantMap<String> q)
            throws RequestHandlerException {
        try {
            M mask = maskType.newInstance();
            mask.readObject(q);

            long n = _requireMapper().count(mask);
            return JsonWrapper.wrap(n, "data");

        } catch (ReflectiveOperationException e) {
            throw new RequestHandlerException("Error instantiate mask of " + maskType, e);
        } catch (LoaderException | ParseException e) {
            throw new RequestHandlerException("Error decode mask of " + maskType, e);
        }
    }

    @SuppressWarnings("unchecked")
    protected <mapper_t extends IGenericMapper<T, M>> mapper_t _requireMapper() {
        Class<T> entityClass = getObjectType();
        Class<?> mapperClass = IMapper.fn.requireMapperClass(entityClass);
        return (mapper_t) getDataContext().requireMapper(mapperClass);
    }

    protected T create()
            throws ReflectiveOperationException {
        T instance = Instantiables._instantiate(getObjectType());
        if (instance == null)
            throw new IllegalUsageException("null instantiated.");
        return instance;
    }

    protected T postLoad(T obj) {
        return obj;
    }

    protected List<T> buildDataList(IVariantMap<String> q, M mask) {
        SelectOptions opts = new SelectOptions();
        opts.readObject(q);
        IGenericMapper<T, M> mapper = _requireMapper();
        List<T> list = mapper.filter(mask, opts);
        return list;
    }

}
