package net.bodz.dist.ins;


public interface Scheme {

    String getName();

    String getCaption();

    String getDescription();

    boolean isIncluded(Component component);

    boolean showConfig(Component component);

}
