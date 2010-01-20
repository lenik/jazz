package net.bodz.bas.flow;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import net.bodz.bas.annotation.util.InheritableAnnotation;
import net.bodz.bas.annotations.MetaClass;
import net.bodz.bas.collection.util.ClassLocal;
import net.bodz.bas.exceptions.IllegalUsageError;
import net.bodz.bas.flow.util.Naming;
import net.bodz.bas.jdk6compat.jdk7emul.Jdk7Reflect;
import net.bodz.bas.jdk6compat.jdk7emul.ReflectiveOperationException;
import net.bodz.bas.sio.BCharOut;
import net.bodz.bas.sio.ILineCharOut;
import net.bodz.bas.text.util.Strings;

public abstract class _Unit
        implements Unit {

    public void send(int portIndex, Object data)
            throws IOException {
        if (portIndex < 0 || portIndex >= getOutPorts())
            throw new IndexOutOfBoundsException("out " + portIndex);
        OutPort outPort = getOutPort(portIndex);
        outPort.send(data);
    }

    private static ClassLocal<UnitMeta> metas;
    static {
        metas = new ClassLocal<UnitMeta>();
    }

    @Override
    public UnitMeta getUnitMeta() {
        Class<? extends _Unit> clazz = getClass();
        UnitMeta meta = metas.get(clazz);
        if (meta == null) {
            Class<?> metaClass = (Class<?>) InheritableAnnotation.getValue(clazz, MetaClass.class);
            if (metaClass != null)
                try {
                    meta = (UnitMeta) Jdk7Reflect.newInstance(metaClass);
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

    protected UnitMeta createUnitMeta() {
        return new _UnitMeta(getName());
    }

    public void dumpGraph(ILineCharOut out, int indent, Set<Unit> loops) {
        String prefix = Strings.repeat(indent * 4, ' ');
        out.println(prefix + getName());

        int outPorts = getOutPorts();
        for (int i = 0; i < outPorts; i++) {
            OutPort outPort = getOutPort(i);
            out.print(prefix + "  " + outPort.getOutPortMeta().getName() //   
                    + "[" + i + "]");
            Receiver dst = outPort.getDst();
            if (dst != null)
                out.print(" -> ");
            out.println();

            if (dst instanceof InPort) {
                InPort dstIn = (InPort) dst;
                Unit dstUnit = dstIn.getUnit();
                if (dstUnit instanceof _Unit) {
                    _Unit _dstUnit = (_Unit) dstUnit;
                    if (loops == null)
                        loops = new HashSet<Unit>();
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
