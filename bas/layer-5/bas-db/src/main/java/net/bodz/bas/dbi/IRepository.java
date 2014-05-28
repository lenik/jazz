package net.bodz.bas.dbi;

public interface IRepository<T, K> {

    T get(K key);

    ISelection<T> query();

}

class Test {

    IRepository<Object, Long> repo;
    {
        repo.query().limit(100, 20).limit(null).read();
    }

}