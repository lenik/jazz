import { ValidateResult } from "skel01-core/src/ui/types";
import CoPhaseValidators from "@lily/basic/src/net/bodz/lily/concrete/CoPhaseValidators";

import type _ArtifactPhase_stuff_TypeInfo from "./_ArtifactPhase_stuff_TypeInfo";

export class _ArtifactPhase_stuff_Validators extends CoPhaseValidators {

    constructor(type: _ArtifactPhase_stuff_TypeInfo) {
        super(type);
    }

    get type() {
        return this._type as _ArtifactPhase_stuff_TypeInfo;
    }

}

export default _ArtifactPhase_stuff_Validators;
