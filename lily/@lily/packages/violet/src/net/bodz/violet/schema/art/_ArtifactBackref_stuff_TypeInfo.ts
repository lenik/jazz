import { INT, STRING } from "@skeljs/core/src/lang/baseinfo";
import { property } from "@skeljs/dba/src/net/bodz/lily/entity/EntityType";
import BackrefRecordTypeInfo from "@lily/basic/src/net/bodz/lily/concrete/BackrefRecordTypeInfo";
import { ExternalSite_TYPE } from "@lily/basic/src/net/bodz/lily/schema/inet/ExternalSiteTypeInfo";

import { Artifact_TYPE } from "./ArtifactTypeInfo";
import _ArtifactBackref_stuff_Validators from "./_ArtifactBackref_stuff_Validators";

export class _ArtifactBackref_stuff_TypeInfo extends BackrefRecordTypeInfo {

    static SCHEMA_NAME = "violet";
    static TABLE_NAME = "art_backref";

    static readonly FIELD_ARTIFACT_ID = "art";
    static readonly FIELD_SITE_ID = "site";
    static readonly FIELD_KEY = "key";
    static readonly FIELD_VALUE = "value";

    static readonly N_ARTIFACT_ID = 10;
    static readonly N_SITE_ID = 10;
    static readonly N_KEY = 30;
    static readonly N_VALUE = 10;

    readonly validators = new _ArtifactBackref_stuff_Validators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.violet.schema.art.ArtifactBackref"; }
    get icon() { return "fa-tag"; }

    override preamble() {
        super.preamble();
        this.declare({
            key: property({ type: STRING, precision: 30, validator: this.validators.validateKey }),
            value: property({ type: INT, nullable: false, precision: 10, validator: this.validators.validateValue }),

            site: property({ type: ExternalSite_TYPE, nullable: false, validator: this.validators.validateSite }),
            siteId: property({ type: INT, nullable: false, precision: 10 }),

            artifact: property({ type: Artifact_TYPE, nullable: false, validator: this.validators.validateArtifact }),
            artifactId: property({ type: INT, nullable: false, precision: 10 }),
        });
    }

    static readonly INSTANCE = new _ArtifactBackref_stuff_TypeInfo();

}

export default _ArtifactBackref_stuff_TypeInfo;

export const _ArtifactBackref_stuff_TYPE = _ArtifactBackref_stuff_TypeInfo.INSTANCE;
