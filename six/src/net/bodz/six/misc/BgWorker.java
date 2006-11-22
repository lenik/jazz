package net.bodz.six.misc;

public interface BgWorker {

    /**
     * @return true if something has been done, or false if nothing to do. This
     *         may affect the scheduling.
     */
    boolean activate(BgScheduler.Preference preference);

}
