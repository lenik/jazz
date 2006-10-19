package net.bodz.sysy;

public interface Version {

    
    /**
     * Any comment associated with this version. 
     * 
     * @return
     */
    String comment();

    /**
     * Difference between this version and the parent version.
     * 
     * @return Difference
     */
    Difference different();

}
