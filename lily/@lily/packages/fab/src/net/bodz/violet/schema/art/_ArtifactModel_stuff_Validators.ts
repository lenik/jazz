import type { int } from "@skeljs/core/src/lang/basetype";
import OffsetDateTime from "@skeljs/core/src/lang/time/OffsetDateTime";
import { ValidateResult } from "@skeljs/core/src/ui/types";
import CoImagedEventValidators from "@lily/basic/src/net/bodz/lily/concrete/CoImagedEventValidators";
import type Artifact from "@lily/violet/src/net/bodz/violet/schema/art/Artifact";
import type _ArtifactModel_stuff_TypeInfo from "@lily/violet/src/net/bodz/violet/schema/art/_ArtifactModel_stuff_TypeInfo";

import type ArtifactModel from "./ArtifactModel";

export class _ArtifactModel_stuff_Validators extends CoImagedEventValidators {

    constructor(type: _ArtifactModel_stuff_TypeInfo) {
        super(type);
    }

    get type() {
        return this._type as _ArtifactModel_stuff_TypeInfo;
    }

    validateValid(val: boolean) {
    }

    validateValidSince(val: OffsetDateTime) {
    }

    validateValidUntil(val: OffsetDateTime) {
    }

    validateModelName(val: string) {
    }

    validateObsolete(val: ArtifactModel) {
    }

    validateArtifact(val: Artifact) {
    }

}

export default _ArtifactModel_stuff_Validators;
