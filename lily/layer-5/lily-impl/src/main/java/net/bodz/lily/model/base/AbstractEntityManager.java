package net.bodz.lily.model.base;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import net.bodz.bas.c.java.util.Dates;
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
import net.bodz.bas.t.order.PriorityComparator;
import net.bodz.bas.t.tuple.Split;
import net.bodz.bas.t.variant.IVarMapForm;
import net.bodz.bas.t.variant.IVariantMap;
import net.bodz.lily.app.IDataApplication;
import net.bodz.lily.app.IDataApplicationAware;
import net.bodz.lily.entity.manager.*;
import net.bodz.lily.entity.type.DefaultEntityTypeInfo;
import net.bodz.lily.entity.type.IEntityTypeInfo;
import net.bodz.lily.security.AccessControl;

@AccessControl
@VirtualHostScope
public abstract class AbstractEntityManager<T, M extends IVarMapForm>
        extends AbstractCacheControl
        implements
            IEntityManager,
            IQueryable,
            IPathArrivalFrameAware,
            ICacheControl,
            IDataApplicationAware,
            IDataContextAware,
            ITableSheetBuilder {

    static final Logger logger = LoggerFactory.getLogger(AbstractEntityManager.class);

    private final Class<T> entityType;
    private final IEntityTypeInfo typeInfo;
    private boolean hasId;

    Map<String, IEntityCommandType> nameMap = new HashMap<>();
    Map<String, IEntityCommandType> contentNameMap = new HashMap<>();
    List<IEntityCommandType> otherCommands = new ArrayList<>();

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

        for (IEntityCommandType cmd : EntityCommands.forEntityClass(entityType))
            addCommand(cmd);

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

    void addCommand(IEntityCommandType cmd) {
        String preferredName = cmd.getPreferredName();

        if (preferredName != null) {
            Map<String, IEntityCommandType> map;
            if (cmd.isContentCommand())
                map = contentNameMap;
            else
                map = nameMap;

            for (String name : cmd.getCommandNames()) {
                IEntityCommandType any = map.get(name);
                if (any != null) {
                    throw new DuplicatedKeyException(name, any);
                }
                map.put(name, cmd);
            }
        } else {
            otherCommands.add(cmd);
        }
    }

    @Override
    public Class<?> getEntityType() {
        return entityType;
    }

    @Override
    public IEntityTypeInfo getEntityTypeInfo() {
        return typeInfo;
    }

    IEntityCommandProcess createProcess(IEntityCommandType type, ResolvedEntity resolvedEntity) {
        EntityCommandContext context = newCommandContext();
        if (resolvedEntity != null)
            context.setResolvedEntity(resolvedEntity);
        IEntityCommandProcess process = type.createProcess(context);
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
                    "error executing entity %s %s [%s]: %s", commandTypeStr, type.getPreferredName(), pathInfoStr,
                    e.getMessage());
            throw new PathDispatchException(message, e);
        }
    }

    EntityCommandContext newCommandContext() {
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
    IEntityMapper<Object, Object> getMapper() {
        DataContext dataContext = getDataContext();
        Class<?> mapperClass = getEntityTypeInfo().getMapperClass();
        return (IEntityMapper<Object, Object>) dataContext.getMapper(mapperClass);
    }

    @Override
    public IPathArrival dispatch(IPathArrival previous, ITokenQueue tokens, IVariantMap<String> q)
            throws PathDispatchException {
        String token = tokens.peek();
        if (token == null)
            return null;

        IEntityCommandType command = nameMap.get(token);
        if (command == null) {
            if (token.startsWith("__data"))
                command = nameMap.get(ListCommand.NAME);
        }
        if (command != null)
            return processDispatch(command, null, previous, tokens, q);

        IPathArrival arrival;
        if (hasId && (arrival = dispatchToEntity(previous, tokens, q)) != null) {
            previous = arrival;
            ResolvedEntity resolvedEntity = (ResolvedEntity) arrival.getTarget();

            token = tokens.peek();
            if (token == null)
                command = contentNameMap.get(ResolveCommand.NAME);
            else
                command = contentNameMap.get(token);

            if (command != null)
                return processDispatch(command, resolvedEntity, previous, tokens, q, //
                        token == null ? 0 : 1);

            for (IEntityCommandType other : otherCommands)
                if (other.checkPathValid(previous, tokens, q))
                    return processDispatch(command, resolvedEntity, previous, tokens, q, 0);

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

        IEntityMapper<Object, Object> mapper = getMapper();

        Object obj = mapper.select(id);
        if (obj == null)
            return null;

        ResolvedEntity info = new ResolvedEntity();
        info.idFieldStrings = heads;
        info.idFields = idvec;
        info.id = id;
        info.entity = obj;
        info.preferredExtension = name_ext.b;
        return PathArrival.shift(idColumnCount, previous, this, info, tokens);
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
    public void buildTableSheet(TableOfPathProps tableData, Workbook book)
            throws FormatException {
        List<?> list = tableData.getList();

        Sheet sheet = book.createSheet();

        String dateStr = Dates.YYYY_MM_DD.format(System.currentTimeMillis());
        String typeName = typeInfo.getEntityClass().getSimpleName();
        String title = String.format("%s %s (%d rows)", dateStr, typeName, list.size());
        book.setSheetName(0, title);

        // fillTable(sheet, tableData);
        Row header = sheet.createRow(0);
        List<String> columns = tableData.getColumnList();

        int nCol = columns.size();
        for (int i = 0; i < nCol; i++) {
            Cell cell = header.createCell(i);
            String col = columns.get(i);
            cell.setCellValue(col);
        }

        int rowIndex = 0;
        for (Object o : list) {
            List<?> line;
            try {
                line = tableData.convert(o, columns);
            } catch (ReflectiveOperationException e) {
                throw new FormatException("Error convert object to cell array: " + e.getMessage(), e);
            }

            Row row = sheet.createRow(++rowIndex);
            for (int i = 0; i < nCol; i++) {
                Object value = line.get(i);
                Cell cell = row.createCell(i);
                if (value != null)
                    cell.setCellValue(value.toString());
            }
        }
    }

}
