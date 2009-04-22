package net.bodz.dist.ins.util;

import net.bodz.bas.io.term.BufferedTerminal;
import net.bodz.bas.io.term.Terminal;
import net.bodz.bas.io.term.Terminals;
import net.bodz.bas.lang.Func0;
import net.bodz.bas.types.util.Strings;
import net.bodz.bas.util.ArrayLogTerm;

public class LevelLogTerm extends ArrayLogTerm {

    class Term extends BufferedTerminal {

        private final Terminal target;
        private String         tab = "    ";

        public Term(Terminal target) {
            this.target = target;
        }

        @Override
        public void p(String s) {
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
