package net.bodz.dist.ins;

public class Schemes {

    static abstract class _Scheme implements Scheme {

        @Override
        public String getCaption() {
            String simpleName = getClass().getSimpleName();
            return simpleName;
        }

        @Override
        public boolean isAutomatic() {
            return true;
        }

    }

    public static class Minimum extends _Scheme {

        @Override
        public String getDescription() {
            return "Only installs the required components";
        }

        @Override
        public boolean isIncluded(IComponent component) {
            return component.isSelected() && component.isReadOnly();
        }

    }

    public static class Standard extends _Scheme {

        @Override
        public String getDescription() {
            return "Installs most recommended components";
        }

        @Override
        public boolean isIncluded(IComponent component) {
            return component.isSelected();
        }

    }

    public static class Maximum extends _Scheme {

        @Override
        public String getDescription() {
            return "Install all components";
        }

        @Override
        public boolean isIncluded(IComponent component) {
            return true;
        }

    }

    public static class Custom extends Standard {

        @Override
        public String getDescription() {
            return "Custom";
        }

        @Override
        public boolean isAutomatic() {
            return false;
        }

    }

}
