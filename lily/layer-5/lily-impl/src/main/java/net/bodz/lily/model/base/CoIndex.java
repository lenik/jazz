package net.bodz.lily.model.base;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.bodz.bas.c.java.io.FilePath;
import net.bodz.bas.c.string.StringPred;
import net.bodz.bas.db.ibatis.IMapperTemplate;
import net.bodz.bas.err.LoadException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.json.JsonFormOptions;
import net.bodz.bas.json.JsonObject;
import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;
import net.bodz.bas.meta.codegen.IndexedType;
import net.bodz.bas.repr.path.IPathArrival;
import net.bodz.bas.repr.path.ITokenQueue;
import net.bodz.bas.repr.path.PathArrival;
import net.bodz.bas.repr.path.PathDispatchException;
import net.bodz.bas.servlet.ctx.CurrentHttpService;
import net.bodz.bas.site.file.IFileNameListener;
import net.bodz.bas.site.file.IncomingSaver;
import net.bodz.bas.site.file.ItemFile;
import net.bodz.bas.site.json.HttpPayload;
import net.bodz.bas.site.json.IMutableJsonResponse;
import net.bodz.bas.site.json.JsonMap;
import net.bodz.bas.site.json.JsonResult;
import net.bodz.bas.site.json.JsonVarMap;
import net.bodz.bas.site.json.JsonWrapper;
import net.bodz.bas.site.vhost.VirtualHostScope;
import net.bodz.bas.t.variant.IVariantMap;
import net.bodz.lily.entity.ILazyId;
import net.bodz.lily.entity.IdFn;
import net.bodz.lily.security.AccessControl;

@AccessControl
@IndexedType(includeAbstract = false)
@VirtualHostScope
public abstract class CoIndex<T extends CoObject, M extends CoObjectMask>
        extends GenericIndex<T, M> {

    static final Logger logger = LoggerFactory.getLogger(CoIndex.class);

    private Class<?> idType;

    public CoIndex() {
        idType = IdFn._getIdType(getObjectType());
    }

    public Class<?> getIdType() {
        return idType;
    }

    protected <mapper_t extends IMapperTemplate<T, M>> mapper_t requireMapper() {
        return super._requireMapper();
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
        else
            return super.dispatch(previous, tokens, q);
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
                copy.id_(null);
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
            // JsonFormOptions opts = new JsonFormOptions(q);
            obj.jsonIn(content, JsonFormOptions.WEB);
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

    protected T load(Object id) {
        IMapperTemplate<T, M> mapper = requireMapper();
        T instance = mapper.select(id);
        return instance;
    }

    @Override
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
        boolean create = obj.id() == null;
        if (create) {
            mapper.insert(obj);
            resp.getLogger().info("Inserted id: " + obj.id());
        } else {
            long rows = mapper.update(obj);
            resp.setHeader("count", rows);
            resp.getLogger().info("Rows updated: " + rows);
        }
        resp.setHeader("id", obj.id());
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
            Object id = obj.id();
            if (id == null) {
                IMapperTemplate<T, M> mapper = requireMapper();
                mapper.insert(obj);
                id = obj.id();
            }
            return id;
        }

    }

}
