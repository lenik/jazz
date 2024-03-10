import { ValidateResult } from "@skeljs/core/src/ui/types";

import type ArtifactParameterTypeInfo from "./ArtifactParameterTypeInfo";
import _ArtifactParameter_stuff_Validators from "./_ArtifactParameter_stuff_Validators";

export class ArtifactParameterValidators extends _ArtifactParameter_stuff_Validators {

    constructor(type: ArtifactParameterTypeInfo) {
        super(type);
    }

    get type() {
        return this._type as ArtifactParameterTypeInfo;
    }

}

export default ArtifactParameterValidators;
