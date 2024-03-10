import { ValidateResult } from "@skeljs/core/src/ui/types";

import type ArtifactVoteTypeInfo from "./ArtifactVoteTypeInfo";
import _ArtifactVote_stuff_Validators from "./_ArtifactVote_stuff_Validators";

export class ArtifactVoteValidators extends _ArtifactVote_stuff_Validators {

    constructor(type: ArtifactVoteTypeInfo) {
        super(type);
    }

    get type() {
        return this._type as ArtifactVoteTypeInfo;
    }

}

export default ArtifactVoteValidators;
