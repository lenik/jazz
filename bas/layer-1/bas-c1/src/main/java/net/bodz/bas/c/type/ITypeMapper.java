package net.bodz.bas.c.type;

public interface ITypeMapper {

    /**
     * @param type
     *            should not be <code>null</code>.
     * @return <code>null</code> if not found.
     */
    Class<?> map(Class<?> type);

}
