import type { int } from "skel01-core/src/lang/basetype";
import { ValidateResult } from "skel01-core/src/ui/types";
import VoteRecordValidators from "lily-basic/src/net/bodz/lily/concrete/VoteRecordValidators";

import type Artifact from "./Artifact";
import type _ArtifactVote_stuff_TypeInfo from "./_ArtifactVote_stuff_TypeInfo";

export class _ArtifactVote_stuff_Validators extends VoteRecordValidators {

    constructor(type: _ArtifactVote_stuff_TypeInfo) {
        super(type);
    }

    get type() {
        return this._type as _ArtifactVote_stuff_TypeInfo;
    }

    validateVoteScore(val: int) {
    }

    validateParent(val: Artifact) {
    }

}

export default _ArtifactVote_stuff_Validators;
