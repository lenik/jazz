package net.bodz.dist.ins;

import net.bodz.bas.a.A_bas;
import net.bodz.dist.nls.PackNLS;

public class Schemes {

    static abstract class _Scheme implements Scheme {

        @Override
        public String getCaption() {
            String caption = A_bas.getDisplayName(getClass());
            return caption;
        }

        @Override
        public boolean isAutomatic() {
            return true;
        }

    }

    public static class Minimum extends _Scheme {

        @Override
        public String getDescription() {
            return PackNLS.getString("Schemes.min"); //$NON-NLS-1$
        }

        @Override
        public boolean isIncluded(IComponent component) {
            return component.getSelection() && component.isReadOnly();
        }

    }

    public static class Standard extends _Scheme {

        @Override
        public String getDescription() {
            return PackNLS.getString("Schemes.std"); //$NON-NLS-1$
        }

        @Override
        public boolean isIncluded(IComponent component) {
            return component.getSelection();
        }

    }

    public static class Maximum extends _Scheme {

        @Override
        public String getDescription() {
            return PackNLS.getString("Schemes.full"); //$NON-NLS-1$
        }

        @Override
        public boolean isIncluded(IComponent component) {
            return true;
        }

    }

    public static class Custom extends Standard {

        @Override
        public String getDescription() {
            return PackNLS.getString("Schemes.custom"); //$NON-NLS-1$
        }

        @Override
        public boolean isAutomatic() {
            return false;
        }

    }

}
