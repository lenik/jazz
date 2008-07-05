package net.bodz.bas.log;

import net.bodz.bas.io.ICharOut;

public class ALogs {

    private static class Root extends ALog {

        private LogOut root;

        public Root(LogOut root) {
            this.root = root;
        }

        @Override
        protected LogOut getRoot() {
            return root;
        }

    }

    public static ALog nil    = get(LogOuts.nil);
    public static ALog stdout = get(LogOuts.stdout);
    public static ALog stderr = get(LogOuts.stderr);

    public static ALog get(LogOut root) {
        return new Root(root);
    }

    public static ALog get(ICharOut root, String name) {
        return new Root(LogOuts.get(root, name));
    }

}
