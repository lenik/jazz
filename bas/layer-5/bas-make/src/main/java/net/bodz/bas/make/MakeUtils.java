package net.bodz.bas.make;

import java.util.IdentityHashMap;
import java.util.Map;

import net.bodz.bas.meta.decl.NotNull;
import net.bodz.bas.t.list.ArrayStack;
import net.bodz.bas.t.list.IStack;
import net.bodz.bas.typer.std.ValidationException;

public class MakeUtils {

    IMakefile makefile;

    public MakeUtils(@NotNull IMakefile makefile) {
        this.makefile = makefile;
    }

    public void checkCircularDep(@NotNull IMakeTarget target)
            throws ValidationException {
        IStack<IMakeTarget> contextPath = new ArrayStack<>();
        Map<IMakeTarget, IMakeTarget[]> kMap = new IdentityHashMap<>();
        _checkCircularDep(target, contextPath, kMap);
    }

    void _checkCircularDep(@NotNull IMakeTarget target, IStack<IMakeTarget> contextPath, Map<IMakeTarget, IMakeTarget[]> kMap)
            throws ValidationException {
        IMakeTarget[] pathVec = (IMakeTarget[]) contextPath.toArray(new IMakeTarget[0]);
        if (kMap.containsKey(target)) {
            String name = target.getDisplayName();
            IMakeTarget[] pathVec2 = kMap.get(target);
            String ap1 = _appearPath(pathVec);
            String ap2 = _appearPath(pathVec2);
            String msg = String.format("circular reference of %s appeared in %s, previous appeared in %s", name, ap1, ap2);
            throw new ValidationException(msg);
        }

        ValidationException firstException = null;
        IMakeRule<IMakeTarget> goodSourceRule = null;

        kMap.put(target, pathVec);
        contextPath.push(target);
        {
next_rule:
            for (IMakeRule<IMakeTarget> rule : makefile.getRules(target)) {
                for (IMakeTarget source : rule.getSources()) {
                    try {
                        _checkCircularDep(source, contextPath, kMap);
                    } catch (ValidationException e) {
                        if (firstException == null)
                            firstException = e;
                        continue next_rule;
                    }
                }
                goodSourceRule = rule;
                break;
            }
        }
        contextPath.pop();
        kMap.remove(target);

        if (goodSourceRule == null && firstException != null)
            throw firstException;
    }

    static String _appearPath(IMakeTarget[] path) {
        StringBuilder buf = new StringBuilder(path.length * 40);
        int i = 0;
        for (IMakeTarget a : path) {
            if (i != 0)
                buf.append(" -> ");
            String name = a.getDisplayName();
            buf.append(name);
            i++;
        }
        return buf.toString();
    }

}
