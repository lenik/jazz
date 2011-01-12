package net.bodz.bas.flow;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import net.bodz.bas.collection.util.ClassLocal;
import net.bodz.bas.flow.util.Naming;
import net.bodz.bas.jdk6compat.jdk7emul.Jdk7Reflect;
import net.bodz.bas.jdk6compat.jdk7emul.ReflectiveOperationException;
import net.bodz.bas.meta.tags.MetaClass;
import net.bodz.bas.sio.BCharOut;
import net.bodz.bas.sio.IPrintOut;
import net.bodz.bas.string.Strings;
import net.bodz.bas.util.Nullables;
import net.bodz.bas.util.exception.IllegalUsageError;

public abstract class AbstractUnit
        implements IUnit {

    public void send(int portIndex, Object data)
            throws IOException {
        if (portIndex < 0 || portIndex >= getOutPorts())
            throw new IndexOutOfBoundsException("out " + portIndex);
        IOutPort outPort = getOutPort(portIndex);
        outPort.send(data);
    }

    private static ClassLocal<IUnitMeta> metas;
    static {
        metas = new ClassLocal<IUnitMeta>();
    }

    @Override
    public IUnitMeta getUnitMeta() {
        Class<? extends AbstractUnit> clazz = getClass();
        IUnitMeta meta = metas.get(clazz);
        if (meta == null) {
            Class<?> metaClass = Nullables.getAnnotation(clazz, MetaClass.class).value();
            if (metaClass != null)
                try {
                    meta = (IUnitMeta) Jdk7Reflect.newInstance(metaClass);
                } catch (ReflectiveOperationException e) {
                    throw new IllegalUsageError("Can't create instance for MetaClass: " + metaClass, e);
                }
            else
                meta = createUnitMeta();
            metas.put(clazz, meta);
        }
        return meta;
    }

    /**
     * default name is class name with identity hash.
     */
    protected String getName() {
        return Naming.getDefaultName(this) + "@" + System.identityHashCode(this);
    }

    protected IUnitMeta createUnitMeta() {
        return new AbstractUnitMeta(getName());
    }

    public void dumpGraph(IPrintOut out, int indent, Set<IUnit> loops) {
        String prefix = Strings.repeat(indent * 4, ' ');
        out.println(prefix + getName());

        int outPorts = getOutPorts();
        for (int i = 0; i < outPorts; i++) {
            IOutPort outPort = getOutPort(i);
            out.print(prefix + "  " + outPort.getOutPortMeta().getName() //   
                    + "[" + i + "]");
            IReceiver dst = outPort.getDst();
            if (dst != null)
                out.print(" -> ");
            out.println();

            if (dst instanceof IInPort) {
                IInPort dstIn = (IInPort) dst;
                IUnit dstUnit = dstIn.getUnit();
                if (dstUnit instanceof AbstractUnit) {
                    AbstractUnit _dstUnit = (AbstractUnit) dstUnit;
                    if (loops == null)
                        loops = new HashSet<IUnit>();
                    else if (loops.contains(_dstUnit)) {
                        out.print(prefix + "    (ref) ");
                        out.println(_dstUnit.getName());
                        return;
                    }
                    loops.add(this);
                    _dstUnit.dumpGraph(out, indent + 1, loops);
                } else {
                    out.print(prefix + "    ");
                    String dstType = dstUnit.getClass().getName();
                    String dstName = dstUnit.getUnitMeta().getName();
                    out.println(dstName + " (" + dstType + ")");
                }
            }
        }
    }

    @Override
    public String toString() {
        BCharOut buffer = new BCharOut();
        dumpGraph(buffer, 0, null);
        return buffer.toString();
    }

}
