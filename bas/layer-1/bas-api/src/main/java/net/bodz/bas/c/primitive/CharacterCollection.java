package net.bodz.bas.c.primitive;

import java.util.Collection;

public class CharacterCollection {

    public static char[] toArray(Collection<Character> collection) {
        int size = collection.size();
        char[] array = new char[size];
        int i = 0;
        for (Character boxed : collection)
            array[i++] = boxed;
        return array;
    }

}
