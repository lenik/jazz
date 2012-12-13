package net.bodz.bas.t.factory;

import net.bodz.bas.err.CreateException;

public abstract class AbstractCreator<T>
        extends AbstractCreatorX<T, CreateException>
        implements ICreator<T> {

}
