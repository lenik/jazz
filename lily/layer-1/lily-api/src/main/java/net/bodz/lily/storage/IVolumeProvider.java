package net.bodz.lily.storage;

public interface IVolumeProvider {

    IVolume getVolume(String id);

    default IVolume getVolume(Class<?> entityClass) {
        String simpleName = entityClass.getSimpleName();
        String id = "entity:" + simpleName;
        return getVolume(id);
    }

}
