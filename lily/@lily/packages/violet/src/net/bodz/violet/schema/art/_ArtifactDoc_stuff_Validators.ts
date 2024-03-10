import { ValidateResult } from "@skeljs/core/src/ui/types";
import DocRecordValidators from "@lily/basic/src/net/bodz/lily/concrete/DocRecordValidators";

import type Artifact from "./Artifact";
import type _ArtifactDoc_stuff_TypeInfo from "./_ArtifactDoc_stuff_TypeInfo";

export class _ArtifactDoc_stuff_Validators extends DocRecordValidators {

    constructor(type: _ArtifactDoc_stuff_TypeInfo) {
        super(type);
    }

    get type() {
        return this._type as _ArtifactDoc_stuff_TypeInfo;
    }

    validateFormArguments(val: string) {
    }

    validateArtifact(val: Artifact) {
    }

}

export default _ArtifactDoc_stuff_Validators;
