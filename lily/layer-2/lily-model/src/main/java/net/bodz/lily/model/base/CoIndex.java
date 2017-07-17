package net.bodz.lily.model.base;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

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
import net.bodz.bas.site.vhost.VirtualHostScope;
import net.bodz.bas.std.rfc.http.AbstractCacheControl;
import net.bodz.bas.std.rfc.http.CacheControlMode;
import net.bodz.bas.std.rfc.http.CacheRevalidationMode;
import net.bodz.bas.std.rfc.http.ICacheControl;
import net.bodz.bas.t.variant.IVariantMap;
import net.bodz.bas.t.variant.VariantMaps;
import net.bodz.lily.entity.Instantiables;
import net.bodz.lily.model.base.security.AccessControl;
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
        String token = tokens.peek();
        if (token == null)
            return null;

        Object target = null;

        switch (token) {
        case "new":
            try {
                target = Instantiables._instantiate(getObjectType());
                break;
            } catch (Exception e) {
                throw new PathDispatchException(e.getMessage(), e);
            }

        case "__data__":
            try {
                HttpServletRequest req = CurrentHttpService.getRequest();
                IVariantMap<String> q = VariantMaps.fromRequest(req);
                String columns = q.getString("columns");
                if (columns == null)
                    throw new PathDispatchException("Expected request parameter columns.");

                List<String> columnList = new ArrayList<>();
                for (String col : columns.split(",")) {
                    col = col.trim();
                    if (col.isEmpty())
                        continue;
                    columnList.add(col);
                }

                M mask = maskType.newInstance();
                mask.readObject(q);

                TableData tableData = new TableData(objectType, columnList);
                List<T> list = buildDataList(q, mask);
                tableData.setList(list);

                target = tableData;
            } catch (ReflectiveOperationException e) {
                throw new PathDispatchException("Error instantiate mask of " + maskType, e);
            } catch (ParseException e) {
                throw new PathDispatchException("Error decode mask of " + maskType, e);
            }
            break;

        default:
            String fileName = FilePath.stripExtension(token);
            if (StringPred.isDecimal(fileName)) {
                Long id = Long.parseLong(fileName);
                IMapperTemplate<T, M> mapper = requireMapper();
                target = mapper.select(id);
                break;
            }
        }

        if (target != null) {
            IDataContextAware.fn.apply(dataContext, target);
            return PathArrival.shift(previous, target, tokens);
        }
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

    protected List<T> buildDataList(IVariantMap<String> q, M mask) {
        List<T> list = requireMapper().filter(mask);
        return list;
    }

}
