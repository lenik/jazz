package net.bodz.lily.concrete;

import jakarta.servlet.http.HttpServletRequest;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.bas.db.ctx.IDataContextAware;
import net.bodz.bas.meta.decl.NotNull;
import net.bodz.bas.potato.PotatoTypes;
import net.bodz.bas.potato.element.IMethod;
import net.bodz.bas.potato.element.IProperty;
import net.bodz.bas.potato.element.IType;
import net.bodz.bas.potato.element.PropertyReadException;
import net.bodz.bas.repr.path.INoPathRef;
import net.bodz.bas.repr.path.IPathArrival;
import net.bodz.bas.repr.path.IPathDispatchable;
import net.bodz.bas.repr.path.ITokenQueue;
import net.bodz.bas.repr.path.PathArrival;
import net.bodz.bas.repr.path.PathDispatchException;
import net.bodz.bas.servlet.ctx.CurrentHttpService;
import net.bodz.bas.t.variant.IVariantMap;
import net.bodz.bas.t.variant.VariantMaps;

public abstract class CoObjectController<T extends CoObject>
        implements IPathDispatchable, INoPathRef, IDataContextAware {

    protected IType controllerType;

    protected HttpServletRequest request;
    protected IVariantMap<String> params;
    protected DataContext dataContext;

    protected T obj;

    public CoObjectController(T obj) {
        this.controllerType = PotatoTypes.getInstance().loadType(getClass());
        this.request = CurrentHttpService.getRequestOpt();
        this.params = VariantMaps.fromRequestOpt(request);
        this.obj = obj;
    }

    @NotNull
    @Override
    public DataContext getDataContext() {
        return dataContext;
    }

    @Override
    public void setDataContext(@NotNull DataContext dataContext) {
        this.dataContext = dataContext;
    }

    @Override
    public T getTarget() {
        return obj;
    }

    @Override
    public IPathArrival dispatch(IPathArrival previous, ITokenQueue tokens, IVariantMap<String> q)
            throws PathDispatchException {
        String token = tokens.peek();
        if (token == null)
            return null;

        IProperty property = controllerType.getProperty(token);
        if (property != null) {
            try {
                Object value = property.read(this);
                return PathArrival.shift(previous, this, value, tokens);
            } catch (PropertyReadException e) {
                throw new PathDispatchException(String.format(//
                        "Failed to get controller property %s: %s", property, e.getMessage()), e);
            }
        }

        IMethod method = controllerType.getMethod(token);
        if (method != null) {
            try {
                Object result = method.invoke(this);
                return PathArrival.shift(previous, this, result, tokens);
            } catch (ReflectiveOperationException e) {
                throw new PathDispatchException(String.format(//
                        "Failed to invoke controller method %s: %s", method, e.getMessage()), e);
            }
        }

        return null;
    }

}
