package net.bodz.lily.storage;

public interface IVolumeProvider {

    IVolume getVolume(String volumeId);

    default IVolume getEntityVolume(String entityName) {
        String id = "entity:" + entityName;
        return getVolume(id);
    }

    default IVolume getEntityVolume(Class<?> entityClass) {
        String simpleName = entityClass.getSimpleName();
        return getEntityVolume(simpleName);
    }

    default IVolume getIncomingVolume(String entityName) {
        String id = "incoming:" + entityName;
        return getVolume(id);
    }

    default IVolume getIncomingVolume(Class<?> entityClass) {
        String simpleName = entityClass.getSimpleName();
        return getIncomingVolume(simpleName);
    }

}
