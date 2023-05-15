package net.bodz.lily.app;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.bas.site.ISiteRoot;
import net.bodz.lily.storage.IVolume;
import net.bodz.lily.storage.IVolumeProvider;

public class DataApplication
        implements
            IDataApplication {

    DataContext dataContext;
    IVolumeProvider volumeProvider;
    ISiteRoot defaultSite;

    public DataApplication() {
    }

    public DataApplication(DataContext dataContext, IVolumeProvider volumeProvider) {
        if (dataContext == null)
            throw new NullPointerException("dataContext");
        if (volumeProvider == null)
            throw new NullPointerException("volumeProvider");
        this.dataContext = dataContext;
        this.volumeProvider = volumeProvider;
    }

    @Override
    public DataContext getDataContext() {
        return dataContext;
    }

    @Override
    public void setDataContext(DataContext dataContext) {
        this.dataContext = dataContext;
    }

    public IVolumeProvider getVolumeProvider() {
        return volumeProvider;
    }

    public void setVolumeProvider(IVolumeProvider volumeProvider) {
        this.volumeProvider = volumeProvider;
    }

    @Override
    public IVolume getVolume(String id) {
        if (volumeProvider == null)
            throw new IllegalStateException("volume provider isn't available.");
        return volumeProvider.getVolume(id);
    }

    @Override
    public ISiteRoot getDefaultSite() {
        return defaultSite;
    }

    public void setDefaultSite(ISiteRoot defaultSite) {
        this.defaultSite = defaultSite;
    }

}
