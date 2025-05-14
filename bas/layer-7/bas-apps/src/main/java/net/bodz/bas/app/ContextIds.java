package net.bodz.bas.app;

import java.util.Collection;

import net.bodz.bas.c.autowire.ProjectList;
import net.bodz.bas.db.ctx.DefaultContextIds;
import net.bodz.bas.db.ctx.IDefaultContextIdsResolver;
import net.bodz.bas.io.ITreeOut;
import net.bodz.bas.io.Stdio;
import net.bodz.bas.meta.build.ProgramName;
import net.bodz.bas.program.skel.BasicCLI;

/**
 * Context Id debug helper
 */
@ProgramName("ctx-id")
public class ContextIds
        extends BasicCLI {

    @Override
    protected void mainImpl(String... args)
            throws Exception {
        ITreeOut out = Stdio.cout.indented();

        for (IDefaultContextIdsResolver resolver : DefaultContextIds.getResolvers()) {
            out.println("Resolver " + resolver);
            out.println("Priority: " + resolver.getPriority());
            out.println("Max-Level: " + resolver.getMaxLevel());
            Collection<String> set = resolver.resolveContextIds();
            if (set != null) {
                out.enter();
                for (String contextId : set) {
                    out.println("Context-Id: " + contextId);
                }
                out.leave();
            }
            out.println();
        }

        out.println("ProjectList: ");
        out.enter();
        ProjectList.INSTANCE.dump(out);
        out.leave();
    }

    public static void main(String[] args)
            throws Exception {
        new ContextIds().execute(args);
    }

}
