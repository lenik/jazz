package net.bodz.art.installer.util;

import net.bodz.bas.c.string.Strings;
import net.bodz.bas.lang.fn.Func0;
import net.bodz.bas.log.AbstractLogSink;
import net.bodz.bas.log.ILogSink;

public class LevelLogTerm
        extends ArrayLogTerm {

    class Term
            extends AbstractLogSink {

        private final ILogSink target;
        private String tab = "    "; //$NON-NLS-1$

        public Term(ILogSink target) {
            this.target = target;
        }

        @Override
        protected void _p(String s) {
            int n = getIndentLevel();
            if (n > 0)
                s = Strings.repeat(n, tab) + s;
            target.p(s);
        }

    }

    private Func0<Integer> level;

    public LevelLogTerm() {
        Terminal t0 = new Term(Terminals.stderr);
        Terminal t1 = new Term(Terminals.stdout);
        Terminal[] array = { t0, t1 };
        setArray(array);
    }

    public void setLevelFunction(Func0<Integer> level) {
        this.level = level;
    }

    protected int getIndentLevel() {
        if (level == null)
            return 0;
        return level.eval();
    }

    @Override
    protected int getIndexOf(int level) {
        return level <= WARN ? 0 : 1;
    }

}
