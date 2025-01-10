import type { int } from "skel01-core/src/lang/basetype";
import { ValidateResult } from "skel01-core/src/ui/types";
import BackrefRecordValidators from "@lily/basic/src/net/bodz/lily/concrete/BackrefRecordValidators";
import type ExternalSite from "@lily/basic/src/net/bodz/lily/schema/inet/ExternalSite";

import type Artifact from "./Artifact";
import type _ArtifactBackref_stuff_TypeInfo from "./_ArtifactBackref_stuff_TypeInfo";

export class _ArtifactBackref_stuff_Validators extends BackrefRecordValidators {

    constructor(type: _ArtifactBackref_stuff_TypeInfo) {
        super(type);
    }

    get type() {
        return this._type as _ArtifactBackref_stuff_TypeInfo;
    }

    validateKey(val: string) {
    }

    validateValue(val: int) {
    }

    validateSite(val: ExternalSite) {
    }

    validateArtifact(val: Artifact) {
    }

}

export default _ArtifactBackref_stuff_Validators;
