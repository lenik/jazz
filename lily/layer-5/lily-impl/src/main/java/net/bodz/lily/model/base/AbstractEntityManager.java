package net.bodz.lily.model.base;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.bodz.bas.c.type.TypeParam;
import net.bodz.bas.db.ctx.DataContext;
import net.bodz.bas.db.ctx.IDataContextAware;
import net.bodz.bas.db.ibatis.IEntityMapper;
import net.bodz.bas.err.DuplicatedKeyException;
import net.bodz.bas.html.viz.IHtmlViewContext;
import net.bodz.bas.html.viz.IPathArrivalFrameAware;
import net.bodz.bas.html.viz.PathArrivalFrame;
import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;
import net.bodz.bas.meta.decl.ObjectType;
import net.bodz.bas.repr.path.IPathArrival;
import net.bodz.bas.repr.path.ITokenQueue;
import net.bodz.bas.repr.path.PathArrival;
import net.bodz.bas.repr.path.PathDispatchException;
import net.bodz.bas.servlet.ctx.CurrentHttpService;
import net.bodz.bas.site.vhost.VirtualHostScope;
import net.bodz.bas.std.rfc.http.AbstractCacheControl;
import net.bodz.bas.std.rfc.http.CacheControlMode;
import net.bodz.bas.std.rfc.http.CacheRevalidationMode;
import net.bodz.bas.std.rfc.http.ICacheControl;
import net.bodz.bas.t.order.PriorityComparator;
import net.bodz.bas.t.tuple.Split;
import net.bodz.bas.t.variant.IVarMapForm;
import net.bodz.bas.t.variant.IVariantMap;
import net.bodz.lily.entity.manager.EntityCommands;
import net.bodz.lily.entity.manager.IEntityCommand;
import net.bodz.lily.entity.manager.MutableEntityCommandContext;
import net.bodz.lily.entity.type.DefaultEntityTypeInfo;
import net.bodz.lily.entity.type.IEntityTypeInfo;
import net.bodz.lily.security.AccessControl;

@AccessControl
@VirtualHostScope
public abstract class AbstractEntityManager<T, M extends IVarMapForm>
        extends AbstractCacheControl
        implements
            IEntityManager,
            IPathArrivalFrameAware,
            ICacheControl,
            IDataContextAware {

    static final Logger logger = LoggerFactory.getLogger(AbstractEntityManager.class);

    private final Class<T> entityType;
    private final IEntityTypeInfo typeInfo;
    private boolean hasId;

    Map<String, IEntityCommand> nameMap = new HashMap<>();
    Map<String, IEntityCommand> contentNameMap = new HashMap<>();
    List<IEntityCommand> otherCommands = new ArrayList<>();

    protected DataContext dataContext;

    public AbstractEntityManager() {
        ObjectType aObjectType = getClass().getAnnotation(ObjectType.class);

        if (aObjectType != null) {
            @SuppressWarnings("unchecked")
            Class<T> objectType = (Class<T>) aObjectType.value();
            this.entityType = objectType;
        } else {
            this.entityType = TypeParam.infer1(getClass(), AbstractEntityManager.class, 0);
        }
        this.typeInfo = new DefaultEntityTypeInfo(entityType);
        this.hasId = typeInfo.getIdClass() != null;

        for (IEntityCommand cmd : EntityCommands.forEntityClass(entityType)) {
            String name = cmd.getPreferredName();

            if (name != null) {
                if (cmd.isContentCommand()) {
                    IEntityCommand preexist = nameMap.get(name);
                    if (preexist != null)
                        throw new DuplicatedKeyException(name);
                    nameMap.put(name, cmd);
                } else {
                    IEntityCommand preexist = contentNameMap.get(name);
                    if (preexist != null)
                        throw new DuplicatedKeyException(name);
                    contentNameMap.put(name, cmd);
                }
            } else {
                otherCommands.add(cmd);
            }
        }

        Collections.sort(otherCommands, PriorityComparator.INSTANCE);
    }

    @Override
    public CacheControlMode getCacheControlMode() {
        return CacheControlMode.AUTO;
    }

    @Override
    public CacheRevalidationMode getCacheRevalidationMode() {
        return CacheRevalidationMode.MUST_REVALIDATE;
    }

    @Override
    public Class<?> getEntityType() {
        return entityType;
    }

    @Override
    public IEntityTypeInfo getEntityTypeInfo() {
        return typeInfo;
    }

    @Override
    public IEntityCommand getCommand(String name) {
        return nameMap.get(name);
    }

    @Override
    public IEntityCommand getContentCommand(String name) {
        return contentNameMap.get(name);
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

        IPathArrival toCommand = dispatchToCommand(previous, tokens, q);
        if (toCommand == null && hasId) {
            getEntityTypeInfo();
        }
        Object target = null;

        IEntityCommand command = nameMap.get(token);
        MutableEntityCommandContext context = new MutableEntityCommandContext(previous, tokens, q);

        Object id = null;
        String extension = null;
        if (command == null) {
            int idColumnCount = typeInfo.getIdColumnCount();

            Split idExtension = Split.nameExtension(token);

            typeInfo.parseIdColumns(idExtension.a);

            id = parseId(idExtension.a, null);
            extension = idExtension.b;
            if (id != null) {
                Class<?> mapperClass = typeInfo.getMapperClass();

                @SuppressWarnings("unchecked")
                IEntityMapper<Object, Object> mapper = (IEntityMapper<Object, Object>) dataContext
                        .getMapper(typeInfo.getMapperClass());

                Object obj = mapper.select(id);
                if (obj == null)
                    return null;

                String token1 = tokens.peek(1);
                if (token1 != null) {
                    command = contentNameMap.get(token1);
                    if (command != null) {
                        context.consume(1, this, obj);
                    }
                } else {
                    for (IEntityCommand other : otherCommands)
                        if (other.Handle(tokens)) {
                            command = other;
                            break;
                        }
                }
            }
        }

        if (command != null) {
            context.setRequest(CurrentHttpService.getRequest());
            context.setDataContext(getDataContext());
            if (id != null) {
                context.setAttribute("id", id);
                context.setAttribute("extension", extension);
            }

            try {
                target = command.execute(context);
            } catch (Exception e) {
                throw new PathDispatchException("error execute entity command: " + e.getMessage(), e);
            }

            return context.getArrival();
        }

        if (target != null)
            return PathArrival.shift(previous, this, target, tokens);
        else
            return null;
    }

    @Override
    public IPathArrival dispatchToCommand(IPathArrival previous, ITokenQueue tokens, IVariantMap<String> q)
            throws PathDispatchException {
        String token = tokens.peek();
        if (token != null) {
            IEntityCommand command = nameMap.get(token);
            if (command != null)
                return PathArrival.shift(previous, this, command, tokens);
        }
        return null;
    }

    @Override
    public IPathArrival dispatchToEntity(IPathArrival previous, ITokenQueue tokens, IVariantMap<String> q)
            throws PathDispatchException {
        return null;
    }

    @Override
    public IPathArrival dispatchToContentCommand(IPathArrival previous, ITokenQueue tokens, IVariantMap<String> q)
            throws PathDispatchException {
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

}
