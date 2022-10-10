package net.bodz.lily.model.base;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.bodz.bas.c.type.TypeParam;
import net.bodz.bas.db.ctx.DataContext;
import net.bodz.bas.db.ctx.IDataContextAware;
import net.bodz.bas.db.ibatis.IEntityMapper;
import net.bodz.bas.err.DuplicatedKeyException;
import net.bodz.bas.err.ParseException;
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
import net.bodz.lily.entity.manager.ResolvedEntity;
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
                if (!cmd.isContentCommand()) {
                    IEntityCommand preexist = nameMap.get(name);
                    if (preexist != null)
                        throw new DuplicatedKeyException(name);
                    nameMap.put(name, cmd);
                    if (name.equals("list"))
                        nameMap.put("__data__", cmd);
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

    @SuppressWarnings("unchecked")
    IEntityMapper<Object, Object> getMapper() {
        Class<?> mapperClass = getEntityTypeInfo().getMapperClass();
        return (IEntityMapper<Object, Object>) dataContext.getMapper(mapperClass);
    }

    @Override
    public IPathArrival dispatch(IPathArrival previous, ITokenQueue tokens, IVariantMap<String> q)
            throws PathDispatchException {
        String token = tokens.peek();
        if (token == null)
            return null;

        IEntityCommand command = nameMap.get(token);
        if (command != null) {
            previous = PathArrival.shift(previous, this, command, tokens);
            command.setDataContext(getDataContext());
            try {
                return command.dispatch(previous, tokens, q);
            } catch (PathDispatchException e) {
                throw new PathDispatchException(String.format(//
                        "error execute entity command %s: %s", token, e.getMessage()), e);
            }
        }

        IPathArrival arrival;
        if (hasId && (arrival = dispatchToEntity(previous, tokens, q)) != null) {
            previous = arrival;
            ResolvedEntity resolvedEntity = (ResolvedEntity) arrival.getTarget();

            command = contentNameMap.get(token);
            if (command != null) {
                previous = PathArrival.shift(previous, this, command, tokens);
                command.setDataContext(getDataContext());
                command.setResolvedEntity(resolvedEntity);

                try {
                    return command.dispatch(previous, tokens, q);
                } catch (PathDispatchException e) {
                    throw new PathDispatchException(String.format(//
                            "error execute entity content command %s: %s", token, e.getMessage()), e);
                }
            }

            for (IEntityCommand other : otherCommands)
                if (other.checkValid(previous, tokens, q)) {
                    previous = PathArrival.shift(0, previous, this, command, tokens);
                    other.setDataContext(dataContext);
                    other.setResolvedEntity(resolvedEntity);

                    try {
                        return other.dispatch(previous, tokens, q);
                    } catch (PathDispatchException e) {
                        throw new PathDispatchException(String.format(//
                                "error execute entity other-command: %s", e.getMessage()), e);
                    }
                }

        } // hasId && dispatchToEntity

        return null;
    }

    /**
     * @return <code>null</code> if needs more tokens, or no matching record to the specified id.
     */
    @Override
    public IPathArrival dispatchToEntity(IPathArrival previous, ITokenQueue tokens, IVariantMap<String> q)
            throws PathDispatchException {
        int cc = typeInfo.getIdColumnCount();
        assert cc >= 1;

        String[] sv = tokens.peek(cc);
        if (sv == null)
            return null;

        Split name_ext = Split.nameExtension(sv[cc - 1]);
        sv[cc - 1] = name_ext.a;

        Object[] idvec;
        try {
            idvec = typeInfo.parseIdColumns(sv);
        } catch (ParseException e) {
            throw new PathDispatchException(String.format(//
                    "error parse id fields %s: %s", //
                    Arrays.asList(sv), e.getMessage()), e);
        }

        Object id;
        try {
            id = typeInfo.newId(idvec);
        } catch (ReflectiveOperationException e) {
            throw new PathDispatchException(String.format(//
                    "error create id from %s: %s", //
                    Arrays.asList(idvec), e.getMessage()), e);
        }

        IEntityMapper<Object, Object> mapper = getMapper();

        Object obj = mapper.select(id);
        if (obj == null)
            return null;

        ResolvedEntity info = new ResolvedEntity();
        info.idFieldStrings = sv;
        info.idFields = idvec;
        info.id = id;
        info.entity = obj;
        info.preferredExtension = name_ext.b;
        return PathArrival.shift(cc, previous, this, info, tokens);
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
