package net.bodz.bas.meta.build;

import java.io.Serializable;

/**
 * The first element must be major version number (integer).
 *
 * The second element must be minor version number (integer).
 *
 * major.minor.release.build-qualifier
 */
public interface IVersion
        extends Comparable<IVersion>, Serializable {

    int size();

    String get(int index);

    String getQualifier();

    boolean isInt(int index);

    int getInt(int index);

    class fn {

        public static IVersion minClosed(IVersion a, IVersion b) {
            if (a == null)
                return b;
            if (b == null)
                return a;
            return (a.compareTo(b) <= 0) ? a : b;
        }

        public static IVersion maxClosed(IVersion a, IVersion b) {
            if (a == null)
                return b;
            if (b == null)
                return a;
            return (a.compareTo(b) >= 0) ? a : b;
        }

        public static IVersion minOpen(IVersion a, IVersion b) {
            if (a == null || b == null)
                return null;
            return (a.compareTo(b) <= 0) ? a : b;
        }

        public static IVersion maxOpen(IVersion a, IVersion b) {
            if (a == null || b == null)
                return null;
            return (a.compareTo(b) >= 0) ? a : b;
        }

    }

}
