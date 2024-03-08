import { INT, STRING } from "@skeljs/core/src/lang/baseinfo";
import type { int } from "@skeljs/core/src/lang/basetype";
import { primaryKey, property } from "@skeljs/dba/src/net/bodz/lily/entity/EntityType";

import CoEntityTypeInfo from "../../concrete/CoEntityTypeInfo";
import ParameterDef from "./ParameterDef";
import _ParameterValue_stuff_Validators from "./_ParameterValue_stuff_Validators";

export class _ParameterValue_stuff_TypeInfo extends CoEntityTypeInfo {

    static SCHEMA_NAME = "lily";
    static TABLE_NAME = "_parmval";

    get name() { return "net.bodz.lily.schema.meta.ParameterValue"; }
    get icon() { return "fa-tag"; }

    static FIELD_ID = "id";
    static FIELD_CODE = "code";
    static FIELD_PARAMETER_ID = "parm";
    static FIELD_VAL = "val";

    static N_ID = 10;
    static N_CODE = 30;
    static N_PARAMETER_ID = 10;
    static N_VAL = 2147483647;

    validators = new _ParameterValue_stuff_Validators(this);

    override preamble() {
        super.preamble();
        this.declare({
            id: primaryKey({ type: INT, nullable: false, precision: 10, validator: this.validators.validateId }),
            code: property({ type: STRING, precision: 30, validator: this.validators.validateCode }),
            val: property({ type: STRING, nullable: false, validator: this.validators.validateVal }),

            parameter: property({ type: ParameterDef.TYPE, nullable: false, validator: this.validators.validateParameter }),
            parameterId: property({ type: INT, nullable: false, precision: 10 }),
        });
    }

    constructor() {
        super();
    }

}

export default _ParameterValue_stuff_TypeInfo;
