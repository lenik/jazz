package net.bodz.bas.c.type;

import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;

import net.bodz.bas.t.model.AbstractDecorator;

public abstract class DecoratedActualClassInfoVisitor
        extends AbstractDecorator<IGenericActualClassInfoVisitor>
        implements
            IGenericActualClassInfoVisitor {

    private static final long serialVersionUID = 1L;

    public DecoratedActualClassInfoVisitor(IGenericActualClassInfoVisitor _orig) {
        super(_orig);
    }

    @Override
    public void beginClass(GenericActualClassInfo gclass) {
        getWrapped().beginClass(gclass);
    }

    @Override
    public void endClass() {
        getWrapped().endClass();
    }

    @Override
    public boolean beginVars() {
        return getWrapped().beginVars();
    }

    @Override
    public void var(int index, TypeVariable<?> var) {
        getWrapped().var(index, var);
    }

    @Override
    public void endVars() {
        getWrapped().endVars();
    }

    @Override
    public boolean beginArgs() {
        return getWrapped().beginArgs();
    }

    @Override
    public void arg(int index, Type arg) {
        getWrapped().arg(index, arg);
    }

    @Override
    public void endArgs() {
        getWrapped().endArgs();
    }

    @Override
    public void gsuper(GenericActualClassInfo gsuper) {
        getWrapped().gsuper(gsuper);
    }

    @Override
    public boolean beginInterfaces() {
        return getWrapped().beginInterfaces();
    }

    @Override
    public void ginterface(int index, GenericActualClassInfo ginterface) {
        getWrapped().ginterface(index, ginterface);
    }

    @Override
    public void endInterfaces() {
        getWrapped().endInterfaces();
    }

}
