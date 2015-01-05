package net.bodz.bas.repr.form;

public class FieldDeclFilters {

    public static final MaxDetailLevel maxDetailLevel(int n) {
        return new MaxDetailLevel(n);
    }

    static class MaxDetailLevel
            implements IFieldDeclFilter {

        final int maxDetailLevel;

        public MaxDetailLevel(int maxDetailLevel) {
            this.maxDetailLevel = maxDetailLevel;
        }

        @Override
        public boolean accept(IFieldDecl fieldDecl) {
            return fieldDecl.getDetailLevel() <= maxDetailLevel;
        }

    }

}
