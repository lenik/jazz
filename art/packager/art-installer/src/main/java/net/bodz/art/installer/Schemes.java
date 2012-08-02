package net.bodz.art.installer;

import static net.bodz.art.installer.nls.PackNLS.PackNLS;

import java.util.HashSet;
import java.util.Set;

import net.bodz.bas.c.string.Strings;

public class Schemes {

    static abstract class _Scheme
            implements Scheme {

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
            super(MINIMUM, PackNLS.getString("Schemes.minimum"), autoComponents);
        }

        @Override
        public String getDescription() {
            return PackNLS.getString("Schemes.min");
        }

        @Override
        public boolean isIncluded(IComponent component) {
            return component.getSelection() && component.isReadOnly();
        }

    }

    public static class Default
            extends _Scheme {

        public Default(IComponent... autoComponents) {
            super(DEFAULT, PackNLS.getString("Schemes.standard"), autoComponents);
        }

        @Override
        public String getDescription() {
            return PackNLS.getString("Schemes.std");
        }

        @Override
        public boolean isIncluded(IComponent component) {
            return component.getSelection();
        }

    }

    public static class Maximum
            extends _Scheme {

        public Maximum(IComponent... autoComponents) {
            super(MAXIMUM, PackNLS.getString("Schemes.maximum"), autoComponents);
        }

        @Override
        public String getDescription() {
            return PackNLS.getString("Schemes.full");
        }

        @Override
        public boolean isIncluded(IComponent component) {
            return true;
        }

    }

    public static class Custom
            extends _Scheme {

        public Custom(IComponent... autoComponents) {
            super(CUSTOM, PackNLS.getString("Schemes.custom2"), autoComponents);
        }

        @Override
        public String getDescription() {
            return PackNLS.getString("Schemes.custom");
        }

        @Override
        public boolean isIncluded(IComponent component) {
            return component.getSelection();
        }

        @Override
        public boolean showConfig(IComponent component) {
            return component.hasConfig();
        }

    }

}
