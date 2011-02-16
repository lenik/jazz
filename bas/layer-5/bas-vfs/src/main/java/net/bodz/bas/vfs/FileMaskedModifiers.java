package net.bodz.bas.vfs;

import java.io.File;
import java.io.Serializable;

import net.bodz.bas.vfs.impl.javaio.JavaioFile;

public class FileMaskedModifiers
        implements Serializable {

    private static final long serialVersionUID = 1L;

    private final int modifiers;
    private final int mask;

    public FileMaskedModifiers(int modifiers, int mask) {
        this.modifiers = modifiers;
        this.mask = mask;
    }

    public FileMaskedModifiers(String s) {
        int slash = s.indexOf('/');
        if (slash == -1) {
            this.modifiers = this.mask = FileModifier.parse(s);
        } else {
            String modifiersStr = s.substring(0, slash);
            String maskStr = s.substring(slash + 1);
            this.modifiers = FileModifier.parse(modifiersStr);
            this.mask = FileModifier.parse(maskStr);
        }
    }

    public int getModifier() {
        return this.modifiers;
    }

    public int getMask() {
        return mask;
    }

    public boolean test(int modifiers) {
        return (modifiers & mask) == this.modifiers;
    }

    public boolean test(IFile file) {
        int modifiers = file.getModifiers();
        return (modifiers & mask) == this.modifiers;
    }

    public boolean test(File file) {
        int modifiers = new JavaioFile(file).getModifiers(mask);
        return modifiers == this.modifiers;
    }

    @Override
    public int hashCode() {
        int hash = 0x512963c3;
        hash ^= modifiers;
        hash <<= 3;
        hash ^= mask;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (!FileMaskedModifiers.class.equals(obj.getClass()))
            return false;
        FileMaskedModifiers o = (FileMaskedModifiers) obj;
        if (modifiers != o.modifiers)
            return false;
        if (mask != o.mask)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return FileModifier.format(modifiers) + "/" + FileModifier.format(mask);
    }

}
