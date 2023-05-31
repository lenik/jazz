package net.bodz.bas.c.type;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;

import net.bodz.bas.io.ITreeOut;

public class GenericActualClassInfoDumper
        implements
            IGenericActualClassInfoVisitor {

    protected ITreeOut out;
    boolean recursive = true;

    public GenericActualClassInfoDumper(ITreeOut out) {
        if (out == null)
            throw new NullPointerException("out");
        this.out = out;
    }

    @Override
    public boolean beginVars() {
        out.println("vars:");
        out.enter();
        return true;
    }

    @Override
    public void endVars() {
        out.leave();
    }

    @Override
    public boolean beginArgs() {
        out.println("args:");
        out.enter();
        return true;
    }

    @Override
    public void endArgs() {
        out.leave();
    }

    @Override
    public boolean beginInterfaces() {
        out.println("interfaces:");
        out.enter();
        return true;
    }

    @Override
    public void endInterfaces() {
        out.leave();
    }

    @Override
    public void beginClass(GenericActualClassInfo gclass) {
        // out.println("gclass " + gclass.getRawClass().getCanonicalName());
        out.enter();
    }

    @Override
    public void endClass() {
        out.leave();
    }

    @Override
    public void var(int index, TypeVariable<?> var) {
        out.printf("  %d. %s\n", index, var.getName(), var);
    }

    @Override
    public void arg(int index, Type arg) {
        out.printf("  %d. %s\n", index, arg.getTypeName());
        if (arg instanceof ParameterizedType) {
            ParameterizedType pt = (ParameterizedType) arg;
            Type[] argArgs = pt.getActualTypeArguments();
            String argsStr = GenericActualClassInfo.formatTypes(argArgs);
            out.printf("    args: %s\n", argsStr);
        }
    }

    @Override
    public void gsuper(GenericActualClassInfo gsuper) {
        out.printf("super: %s\n", gsuper.getRawClass().getName());
        if (recursive) {
            out.enter();
            gsuper.accept(this);
            out.leave();
        }
    }

    @Override
    public void ginterface(int index, GenericActualClassInfo ginterface) {
        Class<?> rawClass = ginterface.getRawClass();
        out.printf("  %d. %s\n", index, rawClass.getCanonicalName());
        if (recursive) {
            out.enter();
            ginterface.accept(this);
            out.leave();
        }
    }

}
