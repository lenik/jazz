package net.bodz.xml.models.pdb;

public interface Row {
    
    Table getTable();

    Object[] getCells();

    void setCells(Object[] cells);

    boolean has(int index);

    boolean has(String name);

    Object get(int index);

    Object get(String name);

    void set(int index, Object cell);

    void set(String name, Object cell);

}
