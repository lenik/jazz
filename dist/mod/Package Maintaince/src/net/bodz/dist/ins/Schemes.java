package net.bodz.dist.ins;

import java.util.HashSet;
import java.util.Set;

import net.bodz.bas.types.util.Strings;
import net.bodz.dist.nls.PackNLS;

public class Schemes {

    static abstract class _Scheme implements Scheme {

        private final String         name;
        private final String         caption;
        private final Set<Component> autoComponents;

        public _Scheme(String name, String caption, Component... autoComponents) {
            if (name == null)
                throw new NullPointerException("name");
            this.name = name;
            if (caption == null)
                caption = Strings.ucfirst(name);
            this.caption = caption;
            this.autoComponents = new HashSet<Component>(autoComponents.length);
            for (Component c : autoComponents)
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
        public boolean showConfig(Component component) {
            if (component.hasConfig())
                return !autoComponents.contains(component);
            else
                return false;
        }

    }

    public static final String MINIMUM = "minimum";
    public static final String DEFAULT = "default";
    public static final String MAXIMUM = "maximum";
    public static final String CUSTOM  = "custom";

    public static class Minimum extends _Scheme {

        public Minimum(Component... autoComponents) {
            super(MINIMUM, "Minimum", autoComponents);
        }

        @Override
        public String getDescription() {
            return PackNLS.getString("Schemes.min"); //$NON-NLS-1$
        }

        @Override
        public boolean isIncluded(Component component) {
            return component.getSelection() && component.isReadOnly();
        }

    }

    public static class Default extends _Scheme {

        public Default(Component... autoComponents) {
            super(DEFAULT, "Standard", autoComponents);
        }

        @Override
        public String getDescription() {
            return PackNLS.getString("Schemes.std"); //$NON-NLS-1$
        }

        @Override
        public boolean isIncluded(Component component) {
            return component.getSelection();
        }

    }

    public static class Maximum extends _Scheme {

        public Maximum(Component... autoComponents) {
            super(MAXIMUM, "Maximum", autoComponents);
        }

        @Override
        public String getDescription() {
            return PackNLS.getString("Schemes.full"); //$NON-NLS-1$
        }

        @Override
        public boolean isIncluded(Component component) {
            return true;
        }

    }

    public static class Custom extends _Scheme {

        public Custom(Component... autoComponents) {
            super(CUSTOM, "Custom", autoComponents);
        }

        @Override
        public String getDescription() {
            return PackNLS.getString("Schemes.custom"); //$NON-NLS-1$
        }

        @Override
        public boolean isIncluded(Component component) {
            return component.getSelection();
        }

        @Override
        public boolean showConfig(Component component) {
            return component.hasConfig();
        }

    }

}
