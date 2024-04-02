import { INT, STRING } from "@skeljs/core/src/lang/baseinfo";
import { property } from "@skeljs/dba/src/net/bodz/lily/entity/EntityType";
import DocRecordTypeInfo from "@lily/basic/src/net/bodz/lily/concrete/DocRecordTypeInfo";

import { Artifact_TYPE } from "./ArtifactTypeInfo";
import _ArtifactDoc_stuff_Validators from "./_ArtifactDoc_stuff_Validators";

export class _ArtifactDoc_stuff_TypeInfo extends DocRecordTypeInfo {

    static SCHEMA_NAME = "violet";
    static TABLE_NAME = "art_doc";

    static readonly FIELD_FORM_ARGUMENTS = "formargs";
    static readonly FIELD_ARTIFACT_ID = "art";

    static readonly N_FORM_ARGUMENTS = 2147483647;
    static readonly N_ARTIFACT_ID = 10;

    readonly validators = new _ArtifactDoc_stuff_Validators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.violet.schema.art.ArtifactDoc"; }
    get icon() { return "fa-tag"; }

    override preamble() {
        super.preamble();
        this.declare({
            formArguments: property({ type: STRING, validator: this.validators.validateFormArguments }),

            artifact: property({ type: Artifact_TYPE, nullable: false, validator: this.validators.validateArtifact }),
            artifactId: property({ type: INT, nullable: false, precision: 10 }),
        });
    }

    static readonly INSTANCE = new _ArtifactDoc_stuff_TypeInfo();

}

export default _ArtifactDoc_stuff_TypeInfo;

export const _ArtifactDoc_stuff_TYPE = _ArtifactDoc_stuff_TypeInfo.INSTANCE;
