package net.bodz.pkg.installer;

import java.util.HashSet;
import java.util.Set;

import net.bodz.bas.c.string.Strings;
import net.bodz.bas.i18n.nls.II18nCapable;

public class Schemes {

    static abstract class _Scheme
            implements Scheme, II18nCapable {

        private final String name;
        private final String caption;
        private final Set<IComponent> autoComponents;

        public _Scheme(String name, String caption, IComponent... autoComponents) {
            if (name == null)
                throw new NullPointerException("name");
            this.name = name;
            if (caption == null)
                caption = Strings.ucfirst(name);
            this.caption = caption;
            this.autoComponents = new HashSet<IComponent>(autoComponents.length);
            for (IComponent c : autoComponents)
                this.autoComponents.add(c);
        }

        public String getName() {
            return name;
        }

        @Override
        public String getCaption() {
            return caption;
        }

        @Override
        public boolean showConfig(IComponent component) {
            if (component.hasConfig())
                return !autoComponents.contains(component);
            else
                return false;
        }

    }

    public static final String MINIMUM = "minimum";
    public static final String DEFAULT = "default";
    public static final String MAXIMUM = "maximum";
    public static final String CUSTOM = "custom";

    public static class Minimum
            extends _Scheme {

        public Minimum(IComponent... autoComponents) {
            super(MINIMUM, tr._("Minimum"), autoComponents);
        }

        @Override
        public String getDescription() {
            return tr._("Only installs the required components");
        }

        @Override
        public boolean isIncluded(IComponent component) {
            return component.isSelected() && component.isReadOnly();
        }

    }

    public static class Default
            extends _Scheme {

        public Default(IComponent... autoComponents) {
            super(DEFAULT, tr._("Standard"), autoComponents);
        }

        @Override
        public String getDescription() {
            return tr._("Installs most recommended components");
        }

        @Override
        public boolean isIncluded(IComponent component) {
            return component.isSelected();
        }

    }

    public static class Maximum
            extends _Scheme {

        public Maximum(IComponent... autoComponents) {
            super(MAXIMUM, tr._("Maximum"), autoComponents);
        }

        @Override
        public String getDescription() {
            return tr._("Install all components");
        }

        @Override
        public boolean isIncluded(IComponent component) {
            return true;
        }

    }

    public static class Custom
            extends _Scheme {

        public Custom(IComponent... autoComponents) {
            super(CUSTOM, tr._("Custom"), autoComponents);
        }

        @Override
        public String getDescription() {
            return tr._("Custom components to satisfy what you need");
        }

        @Override
        public boolean isIncluded(IComponent component) {
            return component.isSelected();
        }

        @Override
        public boolean showConfig(IComponent component) {
            return component.hasConfig();
        }

    }

}
