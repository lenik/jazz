package net.bodz.lily.model.base;

import java.io.BufferedReader;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;

import net.bodz.bas.c.java.io.FilePath;
import net.bodz.bas.c.string.StringPred;
import net.bodz.bas.c.type.TypeParam;
import net.bodz.bas.db.ctx.DataContext;
import net.bodz.bas.db.ctx.IDataContextAware;
import net.bodz.bas.db.ibatis.IMapper;
import net.bodz.bas.db.ibatis.IMapperTemplate;
import net.bodz.bas.err.IllegalUsageException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.html.viz.IHtmlViewContext;
import net.bodz.bas.html.viz.IPathArrivalFrameAware;
import net.bodz.bas.html.viz.PathArrivalFrame;
import net.bodz.bas.http.ctx.CurrentHttpService;
import net.bodz.bas.i18n.dom.iString;
import net.bodz.bas.io.res.builtin.ReaderSource;
import net.bodz.bas.io.res.tools.StreamReading;
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
import net.bodz.bas.site.vhost.VirtualHostScope;
import net.bodz.bas.std.rfc.http.AbstractCacheControl;
import net.bodz.bas.std.rfc.http.CacheControlMode;
import net.bodz.bas.std.rfc.http.CacheRevalidationMode;
import net.bodz.bas.std.rfc.http.ICacheControl;
import net.bodz.bas.t.variant.IVariantMap;
import net.bodz.bas.t.variant.VariantMaps;
import net.bodz.lily.entity.Instantiables;
import net.bodz.lily.model.base.security.AccessControl;
import net.bodz.lily.util.ajax.JsonVarMap;
import net.bodz.lily.util.ajax.JsonWrapper;
import net.bodz.lily.util.ajax.TableData;

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
    public IPathArrival dispatch(IPathArrival previous, ITokenQueue tokens)
            throws PathDispatchException {
        HttpServletRequest req = CurrentHttpService.getRequest();
        IVariantMap<String> q = VariantMaps.fromRequest(req);
        AjaxResult result = new AjaxResult();

        String token = tokens.peek();
        if (token == null)
            return null;

        Object target = null;

        switch (token) {
        case "delete":
            target = result;
            {
                String ids = q.getString("id");
                if (ids == null) {
                    result.msgbox("Id isn't specified.");
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
                        result.setSuccess(false);
                        result.msgbox("Not deleted: " + fails);
                    }
                }
            }
            break;

        case "__data__":
            TableData tableData = new TableData(objectType);

            String columns = q.getString("columns");
            if (columns == null)
                throw new PathDispatchException("Expected request parameter columns.");
            tableData.parseColumnList(columns);

            String formats = q.getString("formats");
            if (formats != null)
                tableData.parseFormats(formats);

            try {
                M mask = maskType.newInstance();
                mask.readObject(q);
                List<T> list = buildDataList(q, mask);
                tableData.setList(list);

                target = tableData;
            } catch (ReflectiveOperationException e) {
                throw new PathDispatchException("Error instantiate mask of " + maskType, e);
            } catch (ParseException e) {
                throw new PathDispatchException("Error decode mask of " + maskType, e);
            }
            break;

        case "save":
            target = result;
            JSONObject root;
            {
                try {
                    BufferedReader reader = req.getReader();
                    String json = new ReaderSource(reader).to(StreamReading.class).readString();
                    root = new JSONObject(json);
                } catch (Exception e) {
                    throw new PathDispatchException("Failed to parse json: " + e.getMessage(), e);
                }

                T obj;
                boolean create = root.isNull("id");
                if (create) {
                    try {
                        obj = create();
                    } catch (ReflectiveOperationException e) {
                        throw new PathDispatchException("Failed to instantiate: " + e.getMessage(), e);
                    }
                } else {
                    long id = root.getLong("id");
                    obj = load(id);
                }

                try {
                    obj.readObject(new JsonVarMap(root));
                } catch (ParseException e) {
                    throw new PathDispatchException("Failed to apply json: " + e.getMessage(), e);
                }
                IMapperTemplate<T, M> mapper = requireMapper();
                if (create) {
                    long id = mapper.insert(obj);
                    result.message("Inserted id: " + id);
                } else {
                    long rows = mapper.update(obj);
                    result.message("Rows updated: " + rows);
                }
            }
            break;

        case "new":
            try {
                target = JsonWrapper.wrap(create(), "data").params(q).withNull().withFalse();
            } catch (Exception e) {
                throw new PathDispatchException(e.getMessage(), e);
            }
            break;

        default:
            String fileName = FilePath.stripExtension(token);
            if (StringPred.isDecimal(fileName)) {
                Long id = Long.parseLong(fileName);
                IMapperTemplate<T, M> mapper = requireMapper();
                target = mapper.select(id);
                target = JsonWrapper.wrap(target, "data").params(q).withNull().withFalse();
            }
            break;
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
        IType type = PotatoTypes.getInstance().forClass(getClass());
        iString label = type.getLabel();
        if (label == null) {
            // logger.warn("Index title (label) isn't specified on " + getClass());
            return getClass().getName();
        }
        return label + "/";
    }

    protected IMapperTemplate<T, M> requireMapper() {
        Class<T> entityClass = getObjectType();
        Class<IMapperTemplate<T, M>> mapperClass = IMapper.fn.requireMapperClass(entityClass);
        IMapperTemplate<T, M> mapper = getDataContext().getMapper(mapperClass);
        if (mapper == null)
            throw new IllegalUsageException("No mapper for " + entityClass);
        return mapper;

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

    protected List<T> buildDataList(IVariantMap<String> q, M mask) {
        List<T> list = requireMapper().filter(mask);
        return list;
    }

}
