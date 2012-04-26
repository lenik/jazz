package net.bodz.bas.model;

import net.bodz.bas.err.CreateException;

public abstract class AbstractCreator<T>
        extends AbstractCreatorX<T, CreateException>
        implements ICreator<T> {

}
