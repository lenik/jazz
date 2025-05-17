package net.bodz.lily.entity.ws;

import java.net.URL;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import jakarta.servlet.http.HttpServletRequest;

import org.apache.poi.ss.usermodel.Workbook;

import net.bodz.bas.c.java.io.FileURL;
import net.bodz.bas.c.reflect.NoSuchPropertyException;
import net.bodz.bas.c.string.StringArray;
import net.bodz.bas.c.type.TypeParam;
import net.bodz.bas.db.ctx.DataContext;
import net.bodz.bas.db.ctx.IDataContextAware;
import net.bodz.bas.db.ibatis.IEntityMapper;
import net.bodz.bas.db.ibatis.sql.Orders;
import net.bodz.bas.db.ibatis.sql.SelectOptions;
import net.bodz.bas.err.FormatException;
import net.bodz.bas.err.IllegalUsageException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.err.ReadOnlyException;
import net.bodz.bas.html.viz.IHtmlViewContext;
import net.bodz.bas.html.viz.IPathArrivalFrameAware;
import net.bodz.bas.html.viz.PathArrivalFrame;
import net.bodz.bas.io.res.builtin.URLResource;
import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;
import net.bodz.bas.meta.decl.NotNull;
import net.bodz.bas.meta.decl.ObjectType;
import net.bodz.bas.potato.element.IProperty;
import net.bodz.bas.potato.element.IType;
import net.bodz.bas.repr.form.IFormDecl;
import net.bodz.bas.repr.form.PropertyChain;
import net.bodz.bas.repr.path.IPathArrival;
import net.bodz.bas.repr.path.ITokenQueue;
import net.bodz.bas.repr.path.PathArrival;
import net.bodz.bas.repr.path.PathDispatchException;
import net.bodz.bas.rtx.IQueryable;
import net.bodz.bas.servlet.ctx.CurrentHttpService;
import net.bodz.bas.site.json.TableOfPathProps;
import net.bodz.bas.site.vhost.VirtualHostScope;
import net.bodz.bas.std.rfc.http.AbstractCacheControl;
import net.bodz.bas.std.rfc.http.CacheControlMode;
import net.bodz.bas.std.rfc.http.CacheRevalidationMode;
import net.bodz.bas.std.rfc.http.ICacheControl;
import net.bodz.bas.t.file.ArrayPathFields;
import net.bodz.bas.t.file.IPathFields;
import net.bodz.bas.t.variant.IVarMapForm;
import net.bodz.bas.t.variant.IVariantMap;
import net.bodz.lily.app.DataApps;
import net.bodz.lily.app.IDataApplication;
import net.bodz.lily.app.IDataApplicationAware;
import net.bodz.lily.criteria.ICriteriaBuilder;
import net.bodz.lily.entity.format.ITableSheetBuilder;
import net.bodz.lily.entity.manager.EntityCommands;
import net.bodz.lily.entity.manager.IEntityCommandContext;
import net.bodz.lily.entity.manager.IEntityCommandProcess;
import net.bodz.lily.entity.manager.IEntityCommandType;
import net.bodz.lily.entity.manager.ResolvedEntity;
import net.bodz.lily.entity.manager.cmd.ContentDeleteCommand;
import net.bodz.lily.entity.manager.cmd.ContentSaveCommand;
import net.bodz.lily.entity.manager.cmd.DataFetchResult;
import net.bodz.lily.entity.manager.cmd.FetchCommand;
import net.bodz.lily.entity.manager.cmd.IDataFetcher;
import net.bodz.lily.entity.type.DefaultEntityTypeInfo;
import net.bodz.lily.entity.type.IEntityTypeInfo;
import net.bodz.lily.format.excel.DefaultListingExcel;
import net.bodz.lily.meta.DefaultLimit;
import net.bodz.lily.security.AccessControl;
import net.bodz.lily.storage.IVolume;
import net.bodz.lily.storage.IVolumeItem;

@AccessControl
@VirtualHostScope
public abstract class AbstractEntityController<T>
        extends AbstractCacheControl
        implements IEntityController,
                   IEntityCommandContext,
                   IQueryable,
                   IPathArrivalFrameAware,
                   ICacheControl,
                   IDataApplicationAware,
                   IDataContextAware,
                   IDataFetcher,
                   ITableSheetBuilder {

    static final Logger logger = LoggerFactory.getLogger(AbstractEntityController.class);

    private final Class<T> entityType;
    private final IEntityTypeInfo typeInfo;
    private final boolean hasId;

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

    protected void overrideCommand(IEntityCommandType cmd) {
        addCommand(cmd, true);
    }

    void addCommand(IEntityCommandType cmd) {
        addCommand(cmd, false);
    }

    void addCommand(IEntityCommandType cmd, boolean replaceExisting) {
        if (!replaceExisting) {
            locator.checkConflict(cmd);
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
            return chains.getFirst();
        } catch (NoSuchPropertyException | ParseException e) {
            return null;
        }
    }

    IEntityCommandProcess createProcess(IEntityCommandType type, ResolvedEntity resolvedEntity) {
        IEntityCommandContext context = newCommandContext();
        IEntityCommandProcess process = type.createProcess(context, resolvedEntity);
        return process;
    }

    IPathArrival dispatchToCommand(IEntityCommandType type, ResolvedEntity resolvedEntity, //
            IPathArrival previous, ITokenQueue tokens, IVariantMap<String> q)
            throws PathDispatchException {
        return dispatchToCommand(type, resolvedEntity, previous, tokens, q, 1);
    }

    protected IPathArrival dispatchToCommand(IEntityCommandType type, ResolvedEntity resolvedEntity, //
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
            return dispatchToProcess(process, previous, tokens, q);
        } catch (PathDispatchException e) {
            String commandTypeStr = "command";
            if (type.isContentCommand())
                commandTypeStr = "content command";
            if (consumedTokenCount == 0)
                commandTypeStr = commandTypeStr + "*";

            String pathInfoStr = StringArray.join("/", pathInfo);
            String message = String.format(//
                    "error executing entity %s %s (path: %s): %s", //
                    commandTypeStr, // [content] command
                    type.getUniqueId(), //
                    pathInfoStr, // [path]
                    e.getMessage());
            throw new PathDispatchException(message, e);
        }
    }

    protected IPathArrival dispatchToProcess(IEntityCommandProcess process, //
            IPathArrival previous, ITokenQueue tokens, IVariantMap<String> q)
            throws PathDispatchException {
        IEntityCommandType type = process.getCommandType();
        ResolvedEntity re = process.getResolvedEntity();
//        System.err.printfln(//
        logger.infof(//
                "%s:%s<%s%s> q=%s", //
                type.getUniqueId(), //
                process.getClass().getSimpleName(), //
                getEntityType().getSimpleName(), //
                re == null ? "" : ("@" + re.id), //
                q);
        return process.dispatch(previous, tokens, q);
    }

    protected IEntityCommandContext newCommandContext() {
        return this;
    }

    /**
     * @return Non-<code>null</code> value.
     */
    @Override
    public abstract IDataApplication getDataApp();

    @NotNull
    @Override
    public DataContext getDataContext() {
        return getDataApp().getDataContext();
    }

    @Override
    public void setDataContext(@NotNull DataContext dataContext) {
        throw new ReadOnlyException();
    }

    @SuppressWarnings("unchecked")
    protected <mapper_t extends IEntityMapper<?>> mapper_t getMapper() {
        DataContext dataContext = getDataContext();
        Class<?> mapperClass = getEntityTypeInfo().getMapperClass();
        return (mapper_t) dataContext.getMapper(mapperClass);
    }

    @Override
    public IPathArrival dispatch(IPathArrival previous, ITokenQueue tokens, IVariantMap<String> q)
            throws PathDispatchException {
        HttpServletRequest request = CurrentHttpService.getRequest();
        String method = request.getMethod();

        String token = tokens.peek();
        if (token == null)
            return null;

        IPathArrival arrival;

        Collection<IEntityCommandType> commands = locator.findName(token);
        if (commands != null)
            for (IEntityCommandType command : commands)
                if (command.isAcceptedMethod(method)) {
                    arrival = dispatchToCommand(command, null, previous, tokens, q);
                    if (arrival != null)
                        return arrival;
                }

        //
        switch (token) {
            case "incoming":
                IDataApplication app = DataApps.fromRequest(request);
                IVolume incomingVolume = app.getIncomingVolume(getEntityType());
                String[] vec = tokens.peek(tokens.available());
                vec = Arrays.copyOfRange(vec, 1, vec.length);
                IPathFields pathFields = new ArrayPathFields(vec);
                IVolumeItem item = incomingVolume.getFile(pathFields);
                Path localFile = item.toPath();
                URL resource = FileURL.toURL(localFile);
                return PathArrival.shift(tokens.available(), previous, this, new URLResource(resource), tokens);
        }
        // app.getEntityVolume(getEntityType());

        if (hasId && (arrival = dispatchToEntity(previous, tokens, q)) != null) {
            previous = arrival;
            ResolvedEntity resolvedEntity = (ResolvedEntity) arrival.getTarget();

            token = tokens.peek();
            if (token == null) {
                IEntityCommandType command = null;
                switch (method) {
                    case "GET":
                        command = locator.get(FetchCommand.ID);
                        break;
                    case "PUT":
                    case "POST":
                    case "PATCH":
                        command = locator.get(ContentSaveCommand.ID);
                        break;
                    case "DELETE":
                        command = locator.get(ContentDeleteCommand.ID);
                        break;
                }
                if (command != null)
                    commands = List.of(command);
                else
                    commands = Collections.emptySet();
            } else {
                commands = locator.findContentName(token);
            }

            if (commands != null)
                for (IEntityCommandType command : commands)
                    if (command.isAcceptedMethod(method)) {
                        arrival = dispatchToCommand(command, resolvedEntity, previous, tokens, q, //
                                token == null ? 0 : 1);
                        if (arrival != null)
                            return arrival;
                    }

            for (IEntityCommandType other : locator.getOthers())
                if (other.isAcceptedMethod(method))
                    if (other.checkPathValid(previous, tokens, q))
                        arrival = dispatchToCommand(other, resolvedEntity, previous, tokens, q, 0);

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
        if (tokens.available() == 0)
            return null;

        ResolveMethods<T> methods = new ResolveMethods<>(typeInfo, getMapper());

        IPathArrival arrival = methods.byIdPath(previous, tokens, q);
        if (arrival != null)
            return arrival;

        arrival = methods.byFieldPath(previous, tokens, q);
        if (arrival != null)
            return arrival;

        OutOfTruthEntity o = outOfTruth(tokens, q);
        if (o != null) {
            return PathArrival.shift(o.consumedTokens.length, //
                    previous, this, o.toResolved(), tokens);
        }

        return null;
    }

    protected OutOfTruthEntity outOfTruth(ITokenQueue tokens, IVariantMap<String> q) {
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
    public DataFetchResult fetchDataList(IVariantMap<String> q, boolean wantCount)
            throws ParseException {
        SelectOptions selectOptions = newSelectOptions();
        selectOptions.readObject(q);

        ICriteriaBuilder<?> criteria = getEntityTypeInfo().newCriteriaBuilder();
        if (criteria instanceof IVarMapForm) {
            IVarMapForm form = (IVarMapForm) criteria;
            form.readObject(q);
        }

        Orders propertyOrders = selectOptions.getOrders();
        if (propertyOrders != null) {
            Orders columnOrders = propertyOrders.mapv(this::findColumnForProperty);
            selectOptions.setOrders(columnOrders);
        }

        List<?> list = fetchDataList(q, criteria, selectOptions);

        DataFetchResult result = new DataFetchResult();
        result.range = list;
        if (wantCount) {
            if (selectOptions.isCountNeeded()) {
                long count = fetchDataCount(q, criteria, selectOptions);
                result.count = count;
            } else {
                result.count = list.size();
            }
        }
        return result;
    }

    protected List<?> fetchDataList(IVariantMap<String> q, ICriteriaBuilder<?> criteria, SelectOptions selectOptions)
            throws ParseException {
        IEntityMapper<?> mapper = getMapper();
        return mapper.filter(criteria.get(), selectOptions);
    }

    protected long fetchDataCount(IVariantMap<String> q, ICriteriaBuilder<?> criteria, SelectOptions selectOptions)
            throws ParseException {
        IEntityMapper<?> mapper = getMapper();
        return mapper.count(criteria.get(), selectOptions);
    }

    protected SelectOptions newSelectOptions() {
        SelectOptions options = new SelectOptions();

        Long defaultLimit = getDefaultLimit();
        if (defaultLimit != null) {
            options.setLimit(defaultLimit);
        }

        return options;
    }

    protected Long getDefaultLimit() {
        DefaultLimit aDefaultLimit = typeInfo.getEntityClass().getAnnotation(DefaultLimit.class);
        if (aDefaultLimit != null) {
            long defaultLimit = aDefaultLimit.value();
            return defaultLimit;
        }
        return null;
    }

    String[] findColumnForProperty(String propertyName) {
        if (propertyName.contains(".")) // alias.column
            return new String[] { propertyName };

        IProperty keyProperty = typeInfo.getPrimaryKeyProperty(propertyName);
        if (keyProperty != null) {
            String[] keyColumns = typeInfo.getPrimaryKeyColumns();
            return keyColumns;
        }

        if (typeInfo.isColumnPresent(propertyName))
            return new String[] { propertyName };

        IType type = typeInfo.getPotatoType();
        IProperty property = type.getProperty(propertyName);
        if (property == null) {
            throw new IllegalArgumentException("no such property: " + propertyName);
        }

        Column aColumn = property.getAnnotation(Column.class);
        if (aColumn != null) {
            String[] columnNames = { aColumn.name() };
            return columnNames;
        }

        if (property.isAnnotationPresent(ManyToOne.class) //
                || property.isAnnotationPresent(OneToOne.class)) {
            JoinColumn aJoinColumn = property.getAnnotation(JoinColumn.class);
            if (aJoinColumn == null) {
                JoinColumns aJoinColumns = property.getAnnotation(JoinColumns.class);
                JoinColumn[] aJoinColumnv = aJoinColumns.value();
                String[] foreignColumnNames = new String[aJoinColumnv.length];
                for (int i = 0; i < foreignColumnNames.length; i++)
                    foreignColumnNames[i] = aJoinColumnv[i].name();
                return foreignColumnNames;
            } else {
                String[] foreignColumnNames = { aJoinColumn.name() };
                return foreignColumnNames;
            }
        }

        throw new IllegalUsageException("No column info on property: " + propertyName);
    }

    @Override
    public void buildTableSheet(TableOfPathProps tableData, Workbook book, IVariantMap<String> q)
            throws FormatException {
        DefaultListingExcel dl = new DefaultListingExcel(tableData, typeInfo);
        dl.buildWorkbook(book);
    }

}
