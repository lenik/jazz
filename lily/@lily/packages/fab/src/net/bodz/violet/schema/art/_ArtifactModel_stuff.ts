import type { int } from "@skeljs/core/src/lang/basetype";
import ZonedDateTime from "@skeljs/core/src/lang/time/ZonedDateTime";
import CoImagedEvent from "@lily/basic/src/net/bodz/lily/concrete/CoImagedEvent";
import type Artifact from "@lily/violet/src/net/bodz/violet/schema/art/Artifact";
import _ArtifactModel_stuff_TypeInfo from "@lily/violet/src/net/bodz/violet/schema/art/_ArtifactModel_stuff_TypeInfo";

import type ArtifactModel from "./ArtifactModel";

export class _ArtifactModel_stuff extends CoImagedEvent<int> {

    static _typeInfo: _ArtifactModel_stuff_TypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = _ArtifactModel_stuff_TypeInfo.INSTANCE;
        return this._typeInfo;
    }

    valid: boolean;
    validSince?: ZonedDateTime;
    validUntil?: ZonedDateTime;
    modelName?: string;

    obsolete?: ArtifactModel;
    obsoleteId?: int;

    artifact: Artifact;
    artifactId: int;

    constructor(o: any) {
        super(o);
    }
}

export default _ArtifactModel_stuff;
