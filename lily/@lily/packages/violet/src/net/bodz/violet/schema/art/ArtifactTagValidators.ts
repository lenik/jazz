import { ValidateResult } from "skel01-core/src/ui/types";

import type ArtifactTagTypeInfo from "./ArtifactTagTypeInfo";
import _ArtifactTag_stuff_Validators from "./_ArtifactTag_stuff_Validators";

export class ArtifactTagValidators extends _ArtifactTag_stuff_Validators {

    constructor(type: ArtifactTagTypeInfo) {
        super(type);
    }

    get type() {
        return this._type as ArtifactTagTypeInfo;
    }

}

export default ArtifactTagValidators;
