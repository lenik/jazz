package net.bodz.bas.c.java.io;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamException;
import java.io.Serializable;

import net.bodz.bas.meta.source.SerializableForm;

@SerializableForm
public interface ISerializable
        extends Serializable {

    void writeObject(ObjectOutputStream out)
            throws IOException;

    void readObject(ObjectInputStream in)
            throws IOException, ClassNotFoundException;

    void readObjectNoData()
            throws ObjectStreamException;

    Object writeReplace()
            throws ObjectStreamException;

    Object readResolve()
            throws ObjectStreamException;

}
