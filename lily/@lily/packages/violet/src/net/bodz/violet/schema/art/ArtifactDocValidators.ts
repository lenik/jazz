import { ValidateResult } from "skel01-core/src/ui/types";

import type ArtifactDocTypeInfo from "./ArtifactDocTypeInfo";
import _ArtifactDoc_stuff_Validators from "./_ArtifactDoc_stuff_Validators";

export class ArtifactDocValidators extends _ArtifactDoc_stuff_Validators {

    constructor(type: ArtifactDocTypeInfo) {
        super(type);
    }

    get type() {
        return this._type as ArtifactDocTypeInfo;
    }

}

export default ArtifactDocValidators;
