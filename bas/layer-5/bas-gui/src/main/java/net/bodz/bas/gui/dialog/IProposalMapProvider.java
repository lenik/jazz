package net.bodz.bas.gui.dialog;

import java.util.Map;

public interface IProposalMapProvider {

    Map<?, ?> getProposalMap(Object obj, Object proposalKey);

}
