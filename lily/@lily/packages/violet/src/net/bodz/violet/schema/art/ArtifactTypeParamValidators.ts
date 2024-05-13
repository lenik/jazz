import { ValidateResult } from "@skeljs/core/src/ui/types";

import type ArtifactTypeParamTypeInfo from "./ArtifactTypeParamTypeInfo";
import _ArtifactTypeParam_stuff_Validators from "./_ArtifactTypeParam_stuff_Validators";

export class ArtifactTypeParamValidators extends _ArtifactTypeParam_stuff_Validators {

    constructor(type: ArtifactTypeParamTypeInfo) {
        super(type);
    }

    get type() {
        return this._type as ArtifactTypeParamTypeInfo;
    }

}

export default ArtifactTypeParamValidators;
