package net.bodz.bas.repr.viz;

public interface IViewBuilderFactory {

    <T> IViewBuilder<T> getViewBuilder(Class<? extends T> type);

    /**
     * TODO class/annotation/parameters...
     */
    // <T> IViewBuilder<T> getViewBuilder(Class<? extends T> type, Map<String, ?> parameters);

}
