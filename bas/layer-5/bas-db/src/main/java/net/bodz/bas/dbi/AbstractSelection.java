package net.bodz.bas.dbi;

public abstract class AbstractSelection<T, K>
        implements ISelection<T, K> {

    private IRepository<T, K> repository;

    protected int firstIndex;
    protected int maxCount;

    public AbstractSelection(IRepository<T, K> repository) {
        if (repository == null)
            throw new NullPointerException("repository");
        this.repository = repository;
    }

    @Override
    public IRepository<T, K> getRepository() {
        return repository;
    }

    @Override
    public boolean isEmpty()
            throws RuntimeDataAccessException {
        return size() != 0;
    }

    @Override
    public int delete()
            throws DataAccessException {
        int count = 0;
        for (K key : keySet()) {
            count += repository.delete(key);
        }
        return count;
    }

    @Override
    public ISelection<T, K> limit(int first, int max) {
        this.firstIndex = first;
        this.maxCount = max;
        return this;
    }

}
