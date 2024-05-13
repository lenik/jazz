import type { double, int } from "@skeljs/core/src/lang/basetype";
import { ValidateResult } from "@skeljs/core/src/ui/types";
import CoRelationValidators from "@lily/basic/src/net/bodz/lily/concrete/CoRelationValidators";

import type Artifact from "./Artifact";
import type StdParameter from "./StdParameter";
import type _ArtifactParam_stuff_TypeInfo from "./_ArtifactParam_stuff_TypeInfo";

export class _ArtifactParam_stuff_Validators extends CoRelationValidators {

    constructor(type: _ArtifactParam_stuff_TypeInfo) {
        super(type);
    }

    get type() {
        return this._type as _ArtifactParam_stuff_TypeInfo;
    }

    validateIval(val: int) {
    }

    validateFval(val: double) {
    }

    validateSval(val: string) {
    }

    validateArtifact(val: Artifact) {
    }

    validateParameter(val: StdParameter) {
    }

}

export default _ArtifactParam_stuff_Validators;
