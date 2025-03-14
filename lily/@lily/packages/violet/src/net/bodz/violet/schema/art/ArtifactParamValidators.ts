import type { int } from "skel01-core/src/lang/basetype";
import { ValidateResult } from "skel01-core/src/ui/types";

import type ArtifactParamTypeInfo from "./ArtifactParamTypeInfo";
import _ArtifactParam_stuff_Validators from "./_ArtifactParam_stuff_Validators";

export class ArtifactParamValidators extends _ArtifactParam_stuff_Validators {

    constructor(type: ArtifactParamTypeInfo) {
        super(type);
    }

    get type() {
        return this._type as ArtifactParamTypeInfo;
    }

}

export default ArtifactParamValidators;
