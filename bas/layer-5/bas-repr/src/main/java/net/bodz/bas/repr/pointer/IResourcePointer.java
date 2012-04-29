package net.bodz.bas.repr.pointer;

public interface IResourcePointer {

    /**
     * @param context
     *            Non-<code>null</code> context URL.
     * @return Non-<code>null</code> instantiated resource URL.
     */
    String instantiate(String context);

}
