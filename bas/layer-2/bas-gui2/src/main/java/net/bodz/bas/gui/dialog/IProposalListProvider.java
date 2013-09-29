package net.bodz.bas.gui.dialog;

import java.util.List;

public interface IProposalListProvider {

    List<?> getProposalList(Object obj, Object proposalKey);

}
