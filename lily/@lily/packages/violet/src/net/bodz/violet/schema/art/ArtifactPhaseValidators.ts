import { ValidateResult } from "@skeljs/core/src/ui/types";

import type ArtifactPhaseTypeInfo from "./ArtifactPhaseTypeInfo";
import _ArtifactPhase_stuff_Validators from "./_ArtifactPhase_stuff_Validators";

export class ArtifactPhaseValidators extends _ArtifactPhase_stuff_Validators {

    constructor(type: ArtifactPhaseTypeInfo) {
        super(type);
    }

    get type() {
        return this._type as ArtifactPhaseTypeInfo;
    }

}

export default ArtifactPhaseValidators;
