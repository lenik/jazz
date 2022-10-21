package net.bodz.bas.repr.form;

public class PropertyFilters {

    public static final MaxDetailLevel maxDetailLevel(int n) {
        return new MaxDetailLevel(n);
    }

    static class MaxDetailLevel
            implements PropertyFilter {

        final int maxDetailLevel;

        public MaxDetailLevel(int maxDetailLevel) {
            this.maxDetailLevel = maxDetailLevel;
        }

        @Override
        public boolean accept(IFormProperty fieldDecl) {
            return fieldDecl.getDetailLevel() <= maxDetailLevel;
        }

    }

}
