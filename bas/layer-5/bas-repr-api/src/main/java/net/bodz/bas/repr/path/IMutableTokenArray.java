package net.bodz.bas.repr.path;

import net.bodz.bas.meta.decl.NotNull;

public interface IMutableTokenArray
        extends ITokenArray {

    void enter();

    void escape();

    void resize(int newSize);

    void compact();

    void set(int index, @NotNull String token);

    void add(String token);

    void add(int index, String token);

    void delete(int index);

    // void splice(int start, int end);

}
