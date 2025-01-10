import type { int } from "skel01-core/src/lang/basetype";
import { ValidateResult } from "skel01-core/src/ui/types";

import type ArtifactTypeTypeInfo from "./ArtifactTypeTypeInfo";
import _ArtifactType_stuff_Validators from "./_ArtifactType_stuff_Validators";

export class ArtifactTypeValidators extends _ArtifactType_stuff_Validators {

    constructor(type: ArtifactTypeTypeInfo) {
        super(type);
    }

    get type() {
        return this._type as ArtifactTypeTypeInfo;
    }

}

export default ArtifactTypeValidators;
