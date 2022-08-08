package net.bodz.lily.model.base;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.bodz.bas.c.java.io.FilePath;
import net.bodz.bas.c.string.StringPred;
import net.bodz.bas.c.type.TypeParam;
import net.bodz.bas.db.ctx.DataContext;
import net.bodz.bas.db.ctx.IDataContextAware;
import net.bodz.bas.db.ibatis.IMapper;
import net.bodz.bas.db.ibatis.IMapperTemplate;
import net.bodz.bas.db.ibatis.sql.SelectOptions;
import net.bodz.bas.err.IllegalUsageException;
import net.bodz.bas.err.LoadException;
import net.bodz.bas.err.LoaderException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.html.viz.IHtmlViewContext;
import net.bodz.bas.html.viz.IPathArrivalFrameAware;
import net.bodz.bas.html.viz.PathArrivalFrame;
import net.bodz.bas.i18n.dom.iString;
import net.bodz.bas.json.JsonObject;
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
import net.bodz.bas.servlet.ctx.CurrentHttpService;
import net.bodz.bas.site.file.IFileNameListener;
import net.bodz.bas.site.file.IncomingSaver;
import net.bodz.bas.site.file.ItemFile;
import net.bodz.bas.site.json.*;
import net.bodz.bas.site.vhost.VirtualHostScope;
import net.bodz.bas.std.rfc.http.AbstractCacheControl;
import net.bodz.bas.std.rfc.http.CacheControlMode;
import net.bodz.bas.std.rfc.http.CacheRevalidationMode;
import net.bodz.bas.std.rfc.http.ICacheControl;
import net.bodz.bas.t.variant.IVariantMap;
import net.bodz.lily.entity.ILazyId;
import net.bodz.lily.entity.IdFn;
import net.bodz.lily.entity.Instantiables;
import net.bodz.lily.security.AccessControl;

@AccessControl
@IndexedType(includeAbstract = true)
@VirtualHostScope
public abstract class CoIndex<T extends CoObject, M extends CoObjectMask>
        extends AbstractCacheControl
        implements
            IPathDispatchable,
            IPathArrivalFrameAware,
            ICacheControl,
            IDataContextAware {

    static final Logger logger = LoggerFactory.getLogger(CoIndex.class);

    private Class<T> objectType;
    private Class<?> idType;
    private Class<M> maskType;
    protected DataContext dataContext;

    public CoIndex() {
        ObjectType aObjectType = getClass().getAnnotation(ObjectType.class);
        if (aObjectType != null) {
            @SuppressWarnings("unchecked")
            Class<T> objectType = (Class<T>) aObjectType.value();
            this.objectType = objectType;
        } else {
            objectType = TypeParam.infer1(getClass(), CoIndex.class, 0);
        }
        idType = IdFn._getIdType(objectType);
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

    public Class<?> getIdType() {
        return idType;
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
                try {
                    target = listHandler(q);
                } catch (RequestHandlerException e) {
                    target = new JsonResult().fail(e, "Failed to handle list request: " + e.getMessage());
                }
                break;
            case "delete":
                try {
                    target = deleteHandler(q);
                } catch (RequestHandlerException e) {
                    target = new JsonResult().fail(e, "Failed to handle delete request: " + e.getMessage());
                }
                break;
            case "new":
                try {
                    target = newHandler(q);
                } catch (RequestHandlerException e) {
                    target = new JsonResult().fail(e, "Failed to handle new-form request: " + e.getMessage());
                }
                break;
            case "save":
                JsonObject json = HttpPayload.getJsonPayload(req);
                try {
                    target = saveHandler(q, json);
                } catch (RequestHandlerException e) {
                    target = new JsonResult().fail(e, "Failed to handle save request: " + e.getMessage());
                }
                break;
            default:
                String fileName = FilePath.stripExtension(token);
                if (StringPred.isDecimal(fileName)) {
                    String id = fileName;
                    try {
                        target = loadHandler(id, q);
                    } catch (Exception e) {
                        throw new PathDispatchException("Failed to load handler: " + e.getMessage(), e);
                    }
                }
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
            return getClass().getName();
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
        } catch (ReflectiveOperationException e) {
            throw new RequestHandlerException("Error instantiate mask of " + maskType, e);
        } catch (LoaderException | ParseException e) {
            throw new RequestHandlerException("Error decode mask of " + maskType, e);
        }
        return tableData;
    }

    protected JsonWrapper newHandler(IVariantMap<String> q)
            throws RequestHandlerException {
        T obj = null;
        String idStr = q.getString("templateId");
        if (idStr != null) {
            Object id;
            try {
                id = IdFn.parseId(idType, idStr);
            } catch (ParseException e) {
                throw new RequestHandlerException("invalid id specifier(" + idStr + "): " + e.getMessage(), e);
            }
            T template = load(id);
            if (template != null) {
                @SuppressWarnings("unchecked")
                T copy = (T) template.clone();
                copy.setId_(null);
                copy.setVersion(0);
                copy.touch();
                obj = copy;
            } else
                throw new RequestHandlerException("template id isn't existed: " + id);
        }
        if (obj == null)
            try {
                obj = create();
            } catch (ReflectiveOperationException e) {
                throw new RequestHandlerException("failed to create: " + e.getMessage(), e);
            }
        JsonWrapper wrapper = JsonWrapper.wrap(obj, "data").withNull().withFalse();
        wrapper.readObject(q);
        return wrapper;
    }

    protected JsonWrapper loadHandler(String idStr, IVariantMap<String> q) {
        Object id;
        try {
            id = IdFn.parseId(idType, idStr);
        } catch (ParseException e) {
            throw new IllegalArgumentException("invalid id specifier(" + idStr + "): " + e.getMessage(), e);
        }

        IMapperTemplate<T, M> mapper = requireMapper();
        T obj = mapper.select(id);
        obj = postLoad(obj);
        JsonWrapper wrapper = JsonWrapper.wrap(obj, "data").withNull().withFalse();
        wrapper.readObject(q);
        return wrapper;
    }

    protected JsonResult saveHandler(IVariantMap<String> q, JsonObject content)
            throws RequestHandlerException {
        JsonResult resp = new JsonResult();

        T obj;
        long id = content.getLong("id", -1);
        boolean create = id == -1;
        if (create) {
            try {
                obj = create();
            } catch (ReflectiveOperationException e) {
                throw new RequestHandlerException("Failed to instantiate: " + e.getMessage(), e);
            }
        } else {
            assert id != -1;
            obj = load(id);
            if (obj == null)
                throw new RequestHandlerException(String.format(//
                        "Edit non-existing record with id=%s.", id));
        }

        JsonVarMap contentMap = new JsonVarMap(content);
        try {
            obj.readObject(content);
        } catch (ParseException e) {
            throw new RequestHandlerException("Failed to apply json: " + e.getMessage(), e);
        }

        save(contentMap, obj, resp);
        return resp;
    }

    protected JsonResult deleteHandler(IVariantMap<String> q)
            throws RequestHandlerException {
        JsonResult resp = new JsonResult();
        String ids = q.getString("id");
        if (ids == null) {
            resp.fail("Id isn't specified.");
        } else {
            IMapperTemplate<T, M> mapper = requireMapper();
            StringBuilder fails = new StringBuilder();
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
                resp.fail("Not deleted: " + fails);
            }
        }
        return resp;
    }

    @SuppressWarnings("unchecked")
    protected <mapper_t extends IMapperTemplate<T, M>> mapper_t requireMapper() {
        Class<T> entityClass = getObjectType();
        Class<IMapperTemplate<T, M>> mapperClass = IMapper.fn.requireMapperClass(entityClass);
        return (mapper_t) getDataContext().requireMapper(mapperClass);
    }

    protected T create()
            throws ReflectiveOperationException {
        T instance = Instantiables._instantiate(getObjectType());
        if (instance == null)
            throw new IllegalUsageException("null instantiated.");
        return instance;
    }

    protected T load(Object id) {
        IMapperTemplate<T, M> mapper = requireMapper();
        T instance = mapper.select(id);
        return instance;
    }

    protected T postLoad(T obj) {
        return obj;
    }

    protected void preSave(IVariantMap<String> q, final T obj, IMutableJsonResponse resp)
            throws IOException {
        if (obj instanceof IExternalFileUsage) {
            IExternalFileUsage u = (IExternalFileUsage) obj;
            List<ItemFile> files = u.getExternalFiles();
            uploadItems(obj, files);
        }

        JsonMap properties = obj.getProperties();
        if (properties instanceof IExternalFileUsage) {
            IExternalFileUsage u = (IExternalFileUsage) properties;
            List<ItemFile> files = u.getExternalFiles();
            uploadItems(obj, files);
        }
    }

    void uploadItems(T obj, List<ItemFile> files) {
        String schema = getObjectType().getSimpleName();
        IncomingSaver handler = new IncomingSaver(schema, obj, new InsertOnRequire());
        handler.fileName(new IFileNameListener() {
            @Override
            public void onFileNameChange(File oldName, File newName) {
                String relativePath = FilePath.getRelativePath(newName, oldName);
                renameUrlAsFileChange(obj, oldName, newName, relativePath);
            }
        });
        handler.accept(files);
    }

    protected void renameUrlAsFileChange(T obj, File oldName, File newName, String relativePath) {
    }

    protected void save(IVariantMap<String> q, T obj, IMutableJsonResponse resp) {
        try {
            preSave(q, obj, resp);
        } catch (IOException e) {
            resp.fail(e, "pre-save failed: " + e.getMessage());
            return;
        }

        IMapperTemplate<T, M> mapper = requireMapper();
        boolean create = obj.getId() == null;
        if (create) {
            mapper.insert(obj);
            resp.getLogger().info("Inserted id: " + obj.getId());
        } else {
            long rows = mapper.update(obj);
            resp.setHeader("count", rows);
            resp.getLogger().info("Rows updated: " + rows);
        }
        resp.setHeader("id", obj.getId());
        resp.succeed();
    }

    protected class InsertOnRequire
            implements
                ILazyId {

        public InsertOnRequire() {
        }

        @Override
        public Object require(Object context) {
            @SuppressWarnings("unchecked")
            T obj = (T) context;
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
        SelectOptions opts = new SelectOptions();
        opts.readObject(q);
        List<T> list = requireMapper().filter(mask, opts);
        return list;
    }

}
