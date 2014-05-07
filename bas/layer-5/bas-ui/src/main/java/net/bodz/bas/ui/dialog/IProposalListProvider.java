package net.bodz.bas.ui.dialog;

import java.util.List;

public interface IProposalListProvider {

    List<?> getProposalList(Object obj, Object proposalKey);

}
