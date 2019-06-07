package net.bodz.bas.ui.dialog;

import java.util.Map;

public interface IProposalMapProvider {

    Map<?, ?> getProposalMap(Object obj, Object proposalKey);

}
