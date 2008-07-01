package net.bodz.bas.lang;

public interface Filter<To, Ti> {

    To filter(Ti input);

}
