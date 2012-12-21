package net.bodz.bas.c.java.nio;

import java.nio.file.attribute.PosixFilePermission;
import java.util.EnumSet;
import java.util.Set;

public class UnitModeBits {

    public static final int OWNER_MASK = 0700;
    public static final int GROUP_MASK = 0070;
    public static final int OTHERS_MASK = 0007;

    public static final int READ_MASK = 0111;
    public static final int WRITE_MASK = 0222;
    public static final int EXECUTE_MASK = 0444;

    public static final int OWNER_READ = 0100;
    public static final int OWNER_WRITE = 0200;
    public static final int OWNER_EXECUTE = 0400;
    public static final int GROUP_READ = 0010;
    public static final int GROUP_WRITE = 0020;
    public static final int GROUP_EXECUTE = 0040;
    public static final int OTHERS_READ = 0001;
    public static final int OTHERS_WRITE = 0002;
    public static final int OTHERS_EXECUTE = 0004;

    public static int fromPermissions(Set<PosixFilePermission> permissions) {
        int mode = 0;
        for (PosixFilePermission permission : permissions) {
            switch (permission) {
            case OWNER_READ:
                mode |= OWNER_READ;
                break;
            case OWNER_WRITE:
                mode |= OWNER_WRITE;
                break;
            case OWNER_EXECUTE:
                mode |= OWNER_EXECUTE;
                break;

            case GROUP_READ:
                mode |= GROUP_READ;
                break;
            case GROUP_WRITE:
                mode |= GROUP_WRITE;
                break;
            case GROUP_EXECUTE:
                mode |= GROUP_EXECUTE;
                break;

            case OTHERS_READ:
                mode |= OTHERS_READ;
                break;
            case OTHERS_WRITE:
                mode |= OTHERS_WRITE;
                break;
            case OTHERS_EXECUTE:
                mode |= OTHERS_EXECUTE;
                break;
            }
        }
        return mode;
    }

    public static Set<PosixFilePermission> toPermissions(int mode) {
        EnumSet<PosixFilePermission> permissions = EnumSet.noneOf(PosixFilePermission.class);

        if ((mode & OWNER_READ) != 0)
            permissions.add(PosixFilePermission.OWNER_READ);
        if ((mode & OWNER_WRITE) != 0)
            permissions.add(PosixFilePermission.OWNER_WRITE);
        if ((mode & OWNER_EXECUTE) != 0)
            permissions.add(PosixFilePermission.OWNER_EXECUTE);

        if ((mode & GROUP_READ) != 0)
            permissions.add(PosixFilePermission.GROUP_READ);
        if ((mode & GROUP_WRITE) != 0)
            permissions.add(PosixFilePermission.GROUP_WRITE);
        if ((mode & GROUP_EXECUTE) != 0)
            permissions.add(PosixFilePermission.GROUP_EXECUTE);

        if ((mode & OTHERS_READ) != 0)
            permissions.add(PosixFilePermission.OTHERS_READ);
        if ((mode & OTHERS_WRITE) != 0)
            permissions.add(PosixFilePermission.OTHERS_WRITE);
        if ((mode & OTHERS_EXECUTE) != 0)
            permissions.add(PosixFilePermission.OTHERS_EXECUTE);

        return permissions;
    }

}
