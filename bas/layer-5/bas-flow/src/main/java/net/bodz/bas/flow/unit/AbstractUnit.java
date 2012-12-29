package net.bodz.bas.flow.unit;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import net.bodz.bas.c.string.Strings;
import net.bodz.bas.c.type.ClassLocal;
import net.bodz.bas.c.type.ClassLocals;
import net.bodz.bas.c.type.TypeName;
import net.bodz.bas.flow.stream.IReceiver;
import net.bodz.bas.flow.unit.metadata.IUnitMetadata;
import net.bodz.bas.flow.unit.metadata.UnitMetaEntryLoader;
import net.bodz.bas.sio.BCharOut;
import net.bodz.bas.sio.IPrintOut;

public abstract class AbstractUnit
        implements IUnit {

    public void send(int portIndex, Object data)
            throws IOException {
        if (portIndex < 0 || portIndex >= getOutPorts())
            throw new IndexOutOfBoundsException("out " + portIndex);
        IOutPort outPort = getOutPort(portIndex);
        outPort.send(data);
    }

    private static ClassLocal<IUnitMetadata> metas = ClassLocals.createMap(//
            IUnitMetadata.class.getCanonicalName(), UnitMetaEntryLoader.INSTANCE);

    @Override
    public IUnitMetadata getUnitMeta() {
        Class<? extends AbstractUnit> clazz = getClass();
        IUnitMetadata meta = metas.getOrLoad(clazz);
        return meta;
    }

    /**
     * default name is class name with identity hash.
     */
    protected String getName() {
        return TypeName.friendly_name(getClass()) + "@" + System.identityHashCode(this);
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
