package net.bodz.lily.entity.ws;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Workbook;

import net.bodz.bas.c.reflect.NoSuchPropertyException;
import net.bodz.bas.c.string.StringArray;
import net.bodz.bas.c.type.TypeParam;
import net.bodz.bas.db.ctx.DataContext;
import net.bodz.bas.db.ctx.IDataContextAware;
import net.bodz.bas.db.ibatis.IEntityMapper;
import net.bodz.bas.err.DuplicatedKeyException;
import net.bodz.bas.err.FormatException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.err.ReadOnlyException;
import net.bodz.bas.html.viz.IHtmlViewContext;
import net.bodz.bas.html.viz.IPathArrivalFrameAware;
import net.bodz.bas.html.viz.PathArrivalFrame;
import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;
import net.bodz.bas.meta.decl.ObjectType;
import net.bodz.bas.repr.form.IFormDecl;
import net.bodz.bas.repr.form.PropertyChain;
import net.bodz.bas.repr.path.IPathArrival;
import net.bodz.bas.repr.path.ITokenQueue;
import net.bodz.bas.repr.path.PathArrival;
import net.bodz.bas.repr.path.PathDispatchException;
import net.bodz.bas.rtx.IQueryable;
import net.bodz.bas.site.json.TableOfPathProps;
import net.bodz.bas.site.vhost.VirtualHostScope;
import net.bodz.bas.std.rfc.http.AbstractCacheControl;
import net.bodz.bas.std.rfc.http.CacheControlMode;
import net.bodz.bas.std.rfc.http.CacheRevalidationMode;
import net.bodz.bas.std.rfc.http.ICacheControl;
import net.bodz.bas.t.file.ArrayPathFields;
import net.bodz.bas.t.tuple.Split;
import net.bodz.bas.t.variant.IVariantMap;
import net.bodz.lily.app.IDataApplication;
import net.bodz.lily.app.IDataApplicationAware;
import net.bodz.lily.concrete.StructRow;
import net.bodz.lily.entity.format.ITableSheetBuilder;
import net.bodz.lily.entity.manager.EntityCommandContext;
import net.bodz.lily.entity.manager.EntityCommands;
import net.bodz.lily.entity.manager.IEntityCommandContext;
import net.bodz.lily.entity.manager.IEntityCommandProcess;
import net.bodz.lily.entity.manager.IEntityCommandType;
import net.bodz.lily.entity.manager.ResolvedEntity;
import net.bodz.lily.entity.manager.cmd.FetchCommand;
import net.bodz.lily.entity.type.DefaultEntityTypeInfo;
import net.bodz.lily.entity.type.IEntityTypeInfo;
import net.bodz.lily.format.excel.DefaultListingExcel;
import net.bodz.lily.security.AccessControl;

@AccessControl
@VirtualHostScope
public abstract class AbstractEntityController<T>
        extends AbstractCacheControl
        implements
            IEntityController,
            IEntityCommandContext,
            IQueryable,
            IPathArrivalFrameAware,
            ICacheControl,
            IDataApplicationAware,
            IDataContextAware,
            ITableSheetBuilder {

    static final Logger logger = LoggerFactory.getLogger(AbstractEntityController.class);

    private final Class<T> entityType;
    private final IEntityTypeInfo typeInfo;
    private boolean hasId;

    CommandLocator locator = new CommandLocator();

    Map<String, String> friendlyColumnNameMap = new HashMap<>();

    public AbstractEntityController() {
        ObjectType aObjectType = getClass().getAnnotation(ObjectType.class);

        if (aObjectType != null) {
            @SuppressWarnings("unchecked")
            Class<T> objectType = (Class<T>) aObjectType.value();
            this.entityType = objectType;
        } else {
            this.entityType = TypeParam.infer1(getClass(), AbstractEntityController.class, 0);
        }
        this.typeInfo = new DefaultEntityTypeInfo(entityType);
        this.hasId = typeInfo.getIdClass() != null;

        for (IEntityCommandType cmd : EntityCommands.forEntityClass(entityType))
            addCommand(cmd, false);
    }

    @Override
    public CacheControlMode getCacheControlMode() {
        return CacheControlMode.AUTO;
    }

    @Override
    public CacheRevalidationMode getCacheRevalidationMode() {
        return CacheRevalidationMode.MUST_REVALIDATE;
    }

    protected void addCommand(IEntityCommandType cmd, boolean replaceExisting) {
        if (! replaceExisting) {
            if (locator.checkOverlap(cmd))
                throw new DuplicatedKeyException();
        }
        locator.add(cmd);
    }

    @Override
    public Class<?> getEntityType() {
        return entityType;
    }

    @Override
    public IEntityTypeInfo getEntityTypeInfo() {
        return typeInfo;
    }

    protected void friendlyColumnNames(String fieldProp, String... columnNames) {
        for (String columnName : columnNames)
            friendlyColumnNameMap.put(columnName, fieldProp);
    }

    @Override
    public PropertyChain resolveFieldProp(String columnName) {
        String fieldProp = friendlyColumnNameMap.get(columnName);
        if (fieldProp == null)
            fieldProp = columnName;

        IEntityTypeInfo typeInfo = getEntityTypeInfo();
        IFormDecl formDecl = typeInfo.getFormDecl();
        try {
            List<PropertyChain> chains = formDecl.resolvePattern(fieldProp);
            if (chains.size() != 1)
                throw new IllegalArgumentException("wildcard isn't allowed.");
            return chains.get(0);
        } catch (NoSuchPropertyException | ParseException e) {
            return null;
        }
    }

    IEntityCommandProcess createProcess(IEntityCommandType type, ResolvedEntity resolvedEntity) {
        EntityCommandContext context = newCommandContext();
        IEntityCommandProcess process = type.createProcess(context, resolvedEntity);
        return process;
    }

    IPathArrival processDispatch(IEntityCommandType type, ResolvedEntity resolvedEntity, //
            IPathArrival previous, ITokenQueue tokens, IVariantMap<String> q)
            throws PathDispatchException {
        return processDispatch(type, resolvedEntity, previous, tokens, q, 1);
    }

    IPathArrival processDispatch(IEntityCommandType type, ResolvedEntity resolvedEntity, //
            IPathArrival previous, ITokenQueue tokens, IVariantMap<String> q, int consumedTokenCount)
            throws PathDispatchException {
        IEntityCommandProcess process = createProcess(type, resolvedEntity);

        String[] pathInfo = tokens.peek(consumedTokenCount);
        process.setCommandPath(new ArrayPathFields(pathInfo));
        if (type.isContentCommand()) {
            pathInfo = previous.getConsumedTokens();
            process.setContentPath(new ArrayPathFields(pathInfo));
        }

        process.setQueryContext(this);

        previous = PathArrival.shift(consumedTokenCount, previous, this, process, tokens);
        try {
            return process.dispatch(previous, tokens, q);
        } catch (PathDispatchException e) {
            String commandTypeStr = "command";
            if (type.isContentCommand())
                commandTypeStr = "content command";
            if (consumedTokenCount == 0)
                commandTypeStr = commandTypeStr + "*";

            String pathInfoStr = StringArray.join("/", pathInfo);
            String message = String.format(//
                    "error executing entity %s %s [%s]: %s", //
                    commandTypeStr, // [content] command
                    type.getUniqueId(), //
                    pathInfoStr, // [path]
                    e.getMessage());
            throw new PathDispatchException(message, e);
        }
    }

    protected EntityCommandContext newCommandContext() {
        EntityCommandContext context = new EntityCommandContext();
        context.setDataApp(getDataApp());
        context.setTypeInfo(typeInfo);
        return context;
    }

    /**
     * @return Non-<code>null</code> value.
     */
    @Override
    public abstract IDataApplication getDataApp();

    @Override
    public DataContext getDataContext() {
        return getDataApp().getDataContext();
    }

    @Override
    public void setDataContext(DataContext dataContext) {
        throw new ReadOnlyException();
    }

    @SuppressWarnings("unchecked")
    <_E> IEntityMapper<_E> getMapper() {
        DataContext dataContext = getDataContext();
        Class<?> mapperClass = getEntityTypeInfo().getMapperClass();
        return (IEntityMapper<_E>) dataContext.getMapper(mapperClass);
    }

    @Override
    public IPathArrival dispatch(IPathArrival previous, ITokenQueue tokens, IVariantMap<String> q)
            throws PathDispatchException {
        String token = tokens.peek();
        if (token == null)
            return null;

        IEntityCommandType command = locator.findName(token);
        if (command != null)
            return processDispatch(command, null, previous, tokens, q);

        IPathArrival arrival;
        if (hasId && (arrival = dispatchToEntity(previous, tokens, q)) != null) {
            previous = arrival;
            ResolvedEntity resolvedEntity = (ResolvedEntity) arrival.getTarget();

            token = tokens.peek();
            if (token == null)
                command = locator.get(FetchCommand.ID);
            else
                command = locator.findContentName(token);

            if (command != null)
                return processDispatch(command, resolvedEntity, previous, tokens, q, //
                        token == null ? 0 : 1);

            for (IEntityCommandType other : locator.getOthers())
                if (other.checkPathValid(previous, tokens, q))
                    return processDispatch(command, resolvedEntity, previous, tokens, q, 0);

            if (token != null)
                return arrival;
        } // hasId && dispatchToEntity

        return null;
    }

    /**
     * @return <code>null</code> if needs more tokens, or no matching record to the specified id.
     */
    @Override
    public IPathArrival dispatchToEntity(IPathArrival previous, ITokenQueue tokens, IVariantMap<String> q)
            throws PathDispatchException {
        int idColumnCount = typeInfo.getIdColumnCount();
        assert idColumnCount >= 1;

        String[] heads = tokens.peek(idColumnCount);
        if (heads == null)
            return null;

        Split name_ext = Split.nameExtension(heads[idColumnCount - 1]);
        heads[idColumnCount - 1] = name_ext.a;

        Object[] idvec;
        try {
            idvec = typeInfo.parseIdColumns(heads);
        } catch (ParseException e) {
            throw new PathDispatchException(String.format(//
                    "error parse id fields %s: %s", //
                    Arrays.asList(heads), e.getMessage()), e);
        }

        Object id;
        try {
            id = typeInfo.newId(idvec);
        } catch (ReflectiveOperationException e) {
            throw new PathDispatchException(String.format(//
                    "error create id from %s: %s", //
                    Arrays.asList(idvec), e.getMessage()), e);
        }

        IEntityMapper<T> mapper = getMapper();

        T obj;
        try {
            obj = mapper.select(id);
        } catch (Exception e) {
            String err = String.format("Error select %s from %s: %s", id, getEntityType().getSimpleName(),
                    e.getMessage());
            logger.error(e, err);
            return null;
        }

        if (obj == null) {
            obj = outOfTruth(id);
            if (obj == null)
                return null;
        }

        // if (tokens.available() > idColumnCount)
        // return PathArrival.shift(idColumnCount, previous, this, obj, tokens);

        ResolvedEntity target = new ResolvedEntity();
        target.idFieldStrings = heads;
        target.idFields = idvec;
        target.id = id;
        target.entity = (StructRow) obj;
        target.preferredExtension = name_ext.b;
        return PathArrival.shift(idColumnCount, previous, this, target, tokens);
    }

    protected T outOfTruth(Object id) {
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
    public void buildTableSheet(TableOfPathProps tableData, Workbook book, IVariantMap<String> q)
            throws FormatException {
        DefaultListingExcel dl = new DefaultListingExcel(tableData, typeInfo);
        dl.build();
    }

}
