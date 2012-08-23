package net.bodz.redist.installer;

public interface Scheme {

    String getName();

    String getCaption();

    String getDescription();

    boolean isIncluded(IComponent component);

    boolean showConfig(IComponent component);

}