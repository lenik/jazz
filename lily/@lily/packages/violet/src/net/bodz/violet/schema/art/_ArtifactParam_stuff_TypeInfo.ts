import { DOUBLE, INT, STRING } from "skel01-core/src/lang/baseinfo";
import type { int } from "skel01-core/src/lang/basetype";
import { property } from "skel01-dba/src/net/bodz/lily/entity/EntityType";
import CoRelationTypeInfo from "@lily/basic/src/net/bodz/lily/concrete/CoRelationTypeInfo";

import { Artifact_TYPE } from "./ArtifactTypeInfo";
import { StdParameter_TYPE } from "./StdParameterTypeInfo";
import _ArtifactParam_stuff_Validators from "./_ArtifactParam_stuff_Validators";

export class _ArtifactParam_stuff_TypeInfo extends CoRelationTypeInfo {

    static SCHEMA_NAME = "violet";
    static TABLE_NAME = "art_parm";

    static readonly FIELD_ARTIFACT_ID = "art";
    static readonly FIELD_PARAMETER_ID = "parm";
    static readonly FIELD_IVAL = "ival";
    static readonly FIELD_FVAL = "fval";
    static readonly FIELD_SVAL = "sval";

    static readonly N_ARTIFACT_ID = 10;
    static readonly N_PARAMETER_ID = 10;
    static readonly N_IVAL = 10;
    static readonly N_FVAL = 17;
    static readonly N_SVAL = 250;

    readonly validators = new _ArtifactParam_stuff_Validators(this);

    constructor() {
        super(INT);
    }

    get name() { return "net.bodz.violet.schema.art.ArtifactParam"; }
    get icon() { return "fa-tag"; }

    override preamble() {
        super.preamble();
        this.declare({
            ival: property({ type: INT, precision: 10, validator: this.validators.validateIval }),
            fval: property({ type: DOUBLE, precision: 17, scale: 17, validator: this.validators.validateFval }),
            sval: property({ type: STRING, precision: 250, validator: this.validators.validateSval }),

            artifact: property({ type: Artifact_TYPE, nullable: false, validator: this.validators.validateArtifact }),
            artifactId: property({ type: INT, nullable: false, precision: 10 }),

            parameter: property({ type: StdParameter_TYPE, nullable: false, validator: this.validators.validateParameter }),
            parameterId: property({ type: INT, nullable: false, precision: 10 }),
        });
    }

    static readonly INSTANCE = new _ArtifactParam_stuff_TypeInfo();

}

export default _ArtifactParam_stuff_TypeInfo;

export const _ArtifactParam_stuff_TYPE = _ArtifactParam_stuff_TypeInfo.INSTANCE;
