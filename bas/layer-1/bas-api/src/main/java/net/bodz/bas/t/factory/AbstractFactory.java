package net.bodz.bas.t.factory;

import net.bodz.bas.err.CreateException;

public abstract class AbstractFactory<T>
        extends AbstractFactoryX<T, CreateException>
        implements IFactory<T> {

}
