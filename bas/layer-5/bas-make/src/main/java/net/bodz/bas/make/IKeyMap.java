package net.bodz.bas.make;

public interface IKeyMap {

    <K, T> IDataEntry<K, T> getDataEntry(K key);

}
