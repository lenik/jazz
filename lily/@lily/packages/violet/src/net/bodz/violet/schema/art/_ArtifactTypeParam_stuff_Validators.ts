import type { double, int } from "skel01-core/src/lang/basetype";
import { ValidateResult } from "skel01-core/src/ui/types";
import CoRelationValidators from "@lily/basic/src/net/bodz/lily/concrete/CoRelationValidators";

import type ArtifactType from "./ArtifactType";
import type StdParameter from "./StdParameter";
import type _ArtifactTypeParam_stuff_TypeInfo from "./_ArtifactTypeParam_stuff_TypeInfo";

export class _ArtifactTypeParam_stuff_Validators extends CoRelationValidators {

    constructor(type: _ArtifactTypeParam_stuff_TypeInfo) {
        super(type);
    }

    get type() {
        return this._type as _ArtifactTypeParam_stuff_TypeInfo;
    }

    validateIval(val: int) {
    }

    validateFval(val: double) {
    }

    validateSval(val: string) {
    }

    validateArttype(val: ArtifactType) {
    }

    validateParameter(val: StdParameter) {
    }

}

export default _ArtifactTypeParam_stuff_Validators;
