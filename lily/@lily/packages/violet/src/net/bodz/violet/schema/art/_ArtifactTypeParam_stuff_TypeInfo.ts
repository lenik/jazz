import { DOUBLE, INT, STRING } from "@skeljs/core/src/lang/baseinfo";
import { property } from "@skeljs/dba/src/net/bodz/lily/entity/EntityType";
import CoRelationTypeInfo from "@lily/basic/src/net/bodz/lily/concrete/CoRelationTypeInfo";

import { ArtifactType_TYPE } from "./ArtifactTypeTypeInfo";
import { StdParameter_TYPE } from "./StdParameterTypeInfo";
import _ArtifactTypeParam_stuff_Validators from "./_ArtifactTypeParam_stuff_Validators";

export class _ArtifactTypeParam_stuff_TypeInfo extends CoRelationTypeInfo {

    static SCHEMA_NAME = "violet";
    static TABLE_NAME = "arttype_parm";

    static readonly FIELD_ARTTYPE_ID = "arttype";
    static readonly FIELD_PARAMETER_ID = "parm";
    static readonly FIELD_IVAL = "ival";
    static readonly FIELD_FVAL = "fval";
    static readonly FIELD_SVAL = "sval";

    static readonly N_ARTTYPE_ID = 10;
    static readonly N_PARAMETER_ID = 10;
    static readonly N_IVAL = 10;
    static readonly N_FVAL = 17;
    static readonly N_SVAL = 250;

    readonly validators = new _ArtifactTypeParam_stuff_Validators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.violet.schema.art.ArtifactTypeParam"; }
    get icon() { return "fa-tag"; }

    override preamble() {
        super.preamble();
        this.declare({
            ival: property({ type: INT, precision: 10, validator: this.validators.validateIval }),
            fval: property({ type: DOUBLE, precision: 17, scale: 17, validator: this.validators.validateFval }),
            sval: property({ type: STRING, precision: 250, validator: this.validators.validateSval }),

            arttype: property({ type: ArtifactType_TYPE, nullable: false, validator: this.validators.validateArttype }),
            arttypeId: property({ type: INT, nullable: false, precision: 10 }),

            parameter: property({ type: StdParameter_TYPE, nullable: false, validator: this.validators.validateParameter }),
            parameterId: property({ type: INT, nullable: false, precision: 10 }),
        });
    }

    static readonly INSTANCE = new _ArtifactTypeParam_stuff_TypeInfo();

}

export default _ArtifactTypeParam_stuff_TypeInfo;

export const _ArtifactTypeParam_stuff_TYPE = _ArtifactTypeParam_stuff_TypeInfo.INSTANCE;
