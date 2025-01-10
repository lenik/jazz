import { ValidateResult } from "skel01-core/src/ui/types";

import type ArtifactBackrefTypeInfo from "./ArtifactBackrefTypeInfo";
import _ArtifactBackref_stuff_Validators from "./_ArtifactBackref_stuff_Validators";

export class ArtifactBackrefValidators extends _ArtifactBackref_stuff_Validators {

    constructor(type: ArtifactBackrefTypeInfo) {
        super(type);
    }

    get type() {
        return this._type as ArtifactBackrefTypeInfo;
    }

}

export default ArtifactBackrefValidators;
