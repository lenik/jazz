package net.bodz.lily.model.base;

import java.util.HashMap;
import java.util.Map;

import net.bodz.bas.c.type.TypeParam;
import net.bodz.bas.db.ctx.DataContext;
import net.bodz.bas.db.ctx.IDataContextAware;
import net.bodz.bas.err.DuplicatedKeyException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.html.viz.IHtmlViewContext;
import net.bodz.bas.html.viz.IPathArrivalFrameAware;
import net.bodz.bas.html.viz.PathArrivalFrame;
import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;
import net.bodz.bas.meta.decl.ObjectType;
import net.bodz.bas.repr.path.IPathArrival;
import net.bodz.bas.repr.path.IPathDispatchable;
import net.bodz.bas.repr.path.ITokenQueue;
import net.bodz.bas.repr.path.PathArrival;
import net.bodz.bas.repr.path.PathDispatchException;
import net.bodz.bas.servlet.ctx.CurrentHttpService;
import net.bodz.bas.site.vhost.VirtualHostScope;
import net.bodz.bas.std.rfc.http.AbstractCacheControl;
import net.bodz.bas.std.rfc.http.CacheControlMode;
import net.bodz.bas.std.rfc.http.CacheRevalidationMode;
import net.bodz.bas.std.rfc.http.ICacheControl;
import net.bodz.bas.t.tuple.Split;
import net.bodz.bas.t.variant.IVarMapForm;
import net.bodz.bas.t.variant.IVariantMap;
import net.bodz.lily.entity.IdFn;
import net.bodz.lily.entity.manager.EntityCommands;
import net.bodz.lily.entity.manager.IEntityCommand;
import net.bodz.lily.entity.manager.MutableEntityCommandContext;
import net.bodz.lily.entity.manager.ResolveCommand;
import net.bodz.lily.entity.type.DefaultEntityTypeInfo;
import net.bodz.lily.entity.type.IEntityTypeInfo;
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

    private final Class<T> objectType;
    private final IEntityTypeInfo typeInfo;

    Map<String, IEntityCommand> commandMap = new HashMap<>();
    ResolveCommand resolveCommand;

    protected DataContext dataContext;

    public GenericIndex() {
        ObjectType aObjectType = getClass().getAnnotation(ObjectType.class);

        if (aObjectType != null) {
            @SuppressWarnings("unchecked")
            Class<T> objectType = (Class<T>) aObjectType.value();
            this.objectType = objectType;
        } else {
            this.objectType = TypeParam.infer1(getClass(), GenericIndex.class, 0);
        }
        this.typeInfo = new DefaultEntityTypeInfo(objectType);

        commandMap = new HashMap<>();
        for (IEntityCommand cmd : EntityCommands.forEntityClass(objectType)) {
            String name = cmd.getPreferredName();
            IEntityCommand preexist = commandMap.get(name);
            if (preexist != null)
                throw new DuplicatedKeyException(name);
            commandMap.put(name, cmd);
            if (cmd instanceof ResolveCommand)
                resolveCommand = (ResolveCommand) cmd;
        }
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

        IEntityCommand command = commandMap.get(token);
        Object id = null;
        String extension = null;
        if (command == null && resolveCommand != null) {
            Split idExtension = Split.nameExtension(token);
            id = parseId(idExtension.a, null);
            extension = idExtension.b;
            if (id != null)
                command = resolveCommand;
        }
        if (command != null) {
            MutableEntityCommandContext context = new MutableEntityCommandContext();
            context.setDataContext(getDataContext());
            context.setRequest(CurrentHttpService.getRequest());
            context.setParameters(q);
            if (id != null) {
                context.setAttribute("id", id);
                context.setAttribute("extension", extension);
            }
            try {
                target = command.execute(context);
            } catch (Exception e) {
                throw new PathDispatchException("error execute entity command: " + e.getMessage(), e);
            }
        }

        if (target != null)
            return PathArrival.shift(previous, this, target, tokens);
        else
            return null;
    }

    public Object parseId(String idStr, Object fallback) {
        Class<?> idClass = typeInfo.getIdClass();
        if (idClass == null)
            return false;
        try {
            Object id = IdFn.parseId(idClass, idStr);
            if (id == null)
                id = fallback;
            return id;
        } catch (ParseException e) {
            return false;
        }
    }

    @Override
    public void enter(IHtmlViewContext ctx, PathArrivalFrame frame) {
        ctx.setVariable("index", this);
    }

    @Override
    public void leave(IHtmlViewContext ctx, PathArrivalFrame frame) {
        ctx.setVariable("index", null);
    }

}
