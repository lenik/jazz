package net.bodz.bas.ui;

import net.bodz.bas.nls.AppNLS;

public interface Proposals {

    Proposal retry = new Proposal('r', AppNLS.getString("Proposals.retry")); //$NON-NLS-1$
    Proposal ignore = new Proposal('i', AppNLS.getString("Proposals.ignore")); //$NON-NLS-1$
    Proposal cancel = new Proposal('c', AppNLS.getString("Proposals.cancel")); //$NON-NLS-1$
    Proposal debug = new Proposal('d', AppNLS.getString("Proposals.debug")); //$NON-NLS-1$

}
