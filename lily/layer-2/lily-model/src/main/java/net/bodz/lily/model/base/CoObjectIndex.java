package net.bodz.lily.model.base;

import net.bodz.bas.c.java.io.FilePath;
import net.bodz.bas.c.string.StringPred;
import net.bodz.bas.db.ctx.DataContext;
import net.bodz.bas.db.ctx.IDataContextAware;
import net.bodz.bas.db.ibatis.IMapperTemplate;
import net.bodz.bas.err.IllegalUsageException;
import net.bodz.bas.html.viz.HtmlViewOptions;
import net.bodz.bas.http.ctx.RequestScope;
import net.bodz.bas.i18n.dom.iString;
import net.bodz.bas.meta.codegen.IndexedType;
import net.bodz.bas.meta.decl.ObjectType;
import net.bodz.bas.potato.PotatoTypes;
import net.bodz.bas.potato.element.IType;
import net.bodz.bas.repr.path.IPathArrival;
import net.bodz.bas.repr.path.IPathDispatchable;
import net.bodz.bas.repr.path.ITokenQueue;
import net.bodz.bas.repr.path.PathArrival;
import net.bodz.bas.repr.path.PathDispatchException;
import net.bodz.bas.std.rfc.mime.ContentTypes;
import net.bodz.lily.entity.Instantiables;

@RequestScope
@IndexedType
public abstract class CoObjectIndex
        implements IPathDispatchable, IDataContextAware {

    private Class<?> objectType;
    private HtmlViewOptions viewOptions = new HtmlViewOptions();
    private boolean pathEnd;

    protected DataContext dataContext;

    public CoObjectIndex() {
        this.objectType = getClass().getAnnotation(ObjectType.class).value();
    }

    public Class<?> getObjectType() {
        return objectType;
    }

    public HtmlViewOptions getViewOptions() {
        return viewOptions;
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
        if (token == null || pathEnd)
            return null;

        switch (token) {
        case "new":
            try {
                Object obj = Instantiables._instantiate(getObjectType());
                return PathArrival.shift(previous, obj, tokens);
            } catch (Exception e) {
                throw new PathDispatchException(e.getMessage(), e);
            }

        case "index.html":
            return PathArrival.shift(previous, this, tokens);

        case "data.json":
            viewOptions.setContentType(ContentTypes.text_javascript);
            viewOptions.setOrigin(true);
            return PathArrival.shift(previous, this, tokens);

        case "iframe.html":
            viewOptions.setOrigin(true);
            return PathArrival.shift(previous, this, tokens);
        }

        String name = FilePath.stripExtension(token);
        if (StringPred.isDecimal(name)) {
            Long id = Long.parseLong(name);

            Class<?> entityClass = getObjectType();
            Class<IMapperTemplate<?, Object>> mapperClass = IMapper.fn.requireMapperClass(entityClass);
            IMapperTemplate<?, Object> mapper = getDataContext().getMapper(mapperClass);
            if (mapper == null)
                throw new IllegalUsageException("No mapper for " + entityClass);

            Object obj = mapper.select(id);
            if (obj != null)
                return PathArrival.shift(previous, obj, tokens);
        }

        return null;
    }

    @Override
    public String toString() {
        IType type = PotatoTypes.getInstance().forClass(getClass());
        iString label = type.getLabel();
        if (label == null)
            throw new IllegalUsageException("Index title (label) isn't specified on " + getClass());
        return label.toString();
    }

}
