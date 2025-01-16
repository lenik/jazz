import type { int } from "skel01-core/src/lang/basetype";
import VoteRecord from "lily-basic/src/net/bodz/lily/concrete/VoteRecord";

import type Artifact from "./Artifact";
import _ArtifactVote_stuff_TypeInfo from "./_ArtifactVote_stuff_TypeInfo";

export class _ArtifactVote_stuff extends VoteRecord {

    static _typeInfo: _ArtifactVote_stuff_TypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = _ArtifactVote_stuff_TypeInfo.INSTANCE;
        return this._typeInfo;
    }

    voteScore: int;

    parent: Artifact;
    parentId: int;

    constructor(o: any) {
        super(o);
    }
}

export default _ArtifactVote_stuff;
