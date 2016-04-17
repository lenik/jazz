package net.bodz.lily.model.base;

import net.bodz.bas.c.java.io.FilePath;
import net.bodz.bas.c.string.StringPred;
import net.bodz.bas.db.ibatis.IMapperTemplate;
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
import net.bodz.bas.rtx.IQueryable;

@IndexedType
public abstract class CoObjectIndex
        implements IPathDispatchable {

    private Class<?> objectType;
    IQueryable context;

    public CoObjectIndex(IQueryable context) {
        if (context == null)
            throw new NullPointerException("context");
        this.context = context;
        this.objectType = getClass().getAnnotation(ObjectType.class).value();
    }

    public Class<?> getObjectType() {
        return objectType;
    }

    @Override
    public IPathArrival dispatch(IPathArrival previous, ITokenQueue tokens)
            throws PathDispatchException {
        String token = tokens.peek();
        IPathArrival ans = null;
        Object obj = null;

        switch (token) {
        case "new":
            try {
                obj = Instantiables._instantiate(getObjectType());
            } catch (Exception e) {
                throw new PathDispatchException(e.getMessage(), e);
            }
            ans = PathArrival.shift(previous, obj, tokens);
            break;

        default:
            String name = FilePath.stripExtension(token);
            if (StringPred.isDecimal(name)) {
                Long id = Long.parseLong(name);
                IMapperTemplate<?, ?> mapper = findMapper(getObjectType());
                if (mapper == null)
                    throw new NullPointerException("mapperTemplate");
                obj = mapper.select(id);
                if (obj != null)
                    ans = PathArrival.shift(previous, obj, tokens);
            }
        }
        return ans;
    }

    protected abstract IMapperTemplate<?, ?> findMapper(Class<?> clazz);

    @Override
    public String toString() {
        IType type = PotatoTypes.getInstance().forClass(getClass());
        if (type == null)
            throw new NullPointerException("type");
        iString label = type.getLabel();
        if (label == null)
            throw new NullPointerException("label");
        return label.toString();
    }

}
