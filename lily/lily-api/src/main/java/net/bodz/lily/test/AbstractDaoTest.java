package net.bodz.lily.test;

import java.util.Random;

import org.junit.Assert;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.bas.db.ctx.DataHub;
import net.bodz.bas.db.test.DaoTestConfig;
import net.bodz.bas.err.IllegalConfigException;
import net.bodz.lily.util.mapper.TableProfiles;

public abstract class AbstractDaoTest
        extends Assert {

    protected DataContext context = findPreferredDataContext();
    protected TableProfiles tables = new TableProfiles(context);

    protected final Random random = new Random();

    protected DataContext getContext() {
        return context;
    }

    protected DataContext findPreferredDataContext() {
        DataHub dataContexts = DataHub.getPreferredHub();
        if (dataContexts == null)
            throw new IllegalConfigException("No data contexts installed.");
        return dataContexts.getTest();
    }

    public DaoTestConfig getConfig() {
        return DaoTestConfig.global;
    }

}
