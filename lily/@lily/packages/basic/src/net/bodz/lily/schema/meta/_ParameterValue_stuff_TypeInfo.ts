import { INT, STRING } from "skel01-core/src/lang/baseinfo";
import { property } from "skel01-dba/src/net/bodz/lily/entity/EntityType";

import AbstractDefinitionTypeInfo from "./AbstractDefinitionTypeInfo";
import { ParameterDef_TYPE } from "./ParameterDefTypeInfo";
import _ParameterValue_stuff_Validators from "./_ParameterValue_stuff_Validators";

export class _ParameterValue_stuff_TypeInfo extends AbstractDefinitionTypeInfo {

    static SCHEMA_NAME = "lily";
    static TABLE_NAME = "_parmval";

    static readonly FIELD_PARAMETER_ID = "parm";
    static readonly FIELD_VAL = "val";

    static readonly N_PARAMETER_ID = 10;
    static readonly N_VAL = 2147483647;

    readonly validators = new _ParameterValue_stuff_Validators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.lily.schema.meta.ParameterValue"; }
    get icon() { return "fa-tag"; }

    override preamble() {
        super.preamble();
        this.declare({
            val: property({ type: STRING, nullable: false, validator: this.validators.validateVal }),

            parameter: property({ type: ParameterDef_TYPE, nullable: false, validator: this.validators.validateParameter }),
            parameterId: property({ type: INT, nullable: false, precision: 10 }),
        });
    }

}

export default _ParameterValue_stuff_TypeInfo;

export const _ParameterValue_stuff_TYPE = _ParameterValue_stuff_TypeInfo.INSTANCE;
