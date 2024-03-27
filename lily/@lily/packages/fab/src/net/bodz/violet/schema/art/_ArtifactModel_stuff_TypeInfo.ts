import { BOOLEAN, INT, STRING } from "@skeljs/core/src/lang/baseinfo";
import type { int } from "@skeljs/core/src/lang/basetype";
import OffsetDateTime from "@skeljs/core/src/lang/time/OffsetDateTime";
import { property } from "@skeljs/dba/src/net/bodz/lily/entity/EntityType";
import CoImagedEventTypeInfo from "@lily/basic/src/net/bodz/lily/concrete/CoImagedEventTypeInfo";
import Artifact from "@lily/violet/src/net/bodz/violet/schema/art/Artifact";
import _ArtifactModel_stuff_Validators from "@lily/violet/src/net/bodz/violet/schema/art/_ArtifactModel_stuff_Validators";

import ArtifactModel from "./ArtifactModel";

export class _ArtifactModel_stuff_TypeInfo extends CoImagedEventTypeInfo {

    static SCHEMA_NAME = "violet";
    static TABLE_NAME = "artmodel";

    static readonly FIELD_VALID = "valid";
    static readonly FIELD_VALID_SINCE = "validsince";
    static readonly FIELD_VALID_UNTIL = "validuntil";
    static readonly FIELD_OBSOLETE_ID = "obsolete";
    static readonly FIELD_ARTIFACT_ID = "art";
    static readonly FIELD_MODEL_NAME = "model";

    static readonly N_VALID = 1;
    static readonly N_VALID_SINCE = 35;
    static readonly N_VALID_UNTIL = 35;
    static readonly N_OBSOLETE_ID = 10;
    static readonly N_ARTIFACT_ID = 10;
    static readonly N_MODEL_NAME = 40;

    readonly validators = new _ArtifactModel_stuff_Validators(this);

    constructor() {
        super(INT);
    }

    get name() { return "net.bodz.violet.schema.art.ArtifactModel"; }
    get icon() { return "fa-tag"; }

    override preamble() {
        super.preamble();
        this.declare({
            valid: property({ type: BOOLEAN, nullable: false, precision: 1, validator: this.validators.validateValid }),
            validSince: property({ type: OffsetDateTime.TYPE, precision: 35, scale: 6, validator: this.validators.validateValidSince }),
            validUntil: property({ type: OffsetDateTime.TYPE, precision: 35, scale: 6, validator: this.validators.validateValidUntil }),
            modelName: property({ type: STRING, precision: 40, validator: this.validators.validateModelName }),

            obsolete: property({ type: this, validator: this.validators.validateObsolete }),
            obsoleteId: property({ type: INT, precision: 10 }),

            artifact: property({ type: Artifact.TYPE, nullable: false, validator: this.validators.validateArtifact }),
            artifactId: property({ type: INT, nullable: false, precision: 10 }),
        });
    }

    static readonly INSTANCE = new _ArtifactModel_stuff_TypeInfo();

}

export default _ArtifactModel_stuff_TypeInfo;
