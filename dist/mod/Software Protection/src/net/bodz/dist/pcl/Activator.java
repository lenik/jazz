package net.bodz.dist.pcl;

import java.lang.reflect.InvocationTargetException;

import net.bodz.bas.lang.RunnableThrows;
import net.bodz.dist.pm.ProtectionModel;

public class Activator {

    ProtectionModel   pmodel;
    RunnableThrows<?> target;

    public Activator(ProtectionModel pmodel, RunnableThrows<?> target) {
        this.pmodel = pmodel;
        this.target = target;
    }

    public void activate() throws ActivateException, InvocationTargetException {

        try {
            target.run();
        } catch (Throwable e) {
            throw new InvocationTargetException(e);
        }
    }

}
