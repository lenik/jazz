package net.bodz.xml.xmod.modpdb;

public interface Fields {

    int size();

    Field get(int index);

    Object[] select(Object[] row);

    Object[] select(Row row);

    Row map(Row row);

}
