import type { integer } from "@skeljs/core/src/lang/type";
import CoEntityType from "@skeljs/dba/src/net/bodz/lily/concrete/CoEntityType";
import { EntityPropertyMap, primaryKey, property } from "@skeljs/dba/src/net/bodz/lily/entity";

import { ParameterDef } from "./ParameterDef";
import ParameterValueValidators from "./ParameterValueValidators";

export class _ParameterValue_stuff_Type extends CoEntityType {

    static SCHEMA_NAME = "lily";
    static TABLE_NAME = "_parmval";

    name = "net.bodz.lily.schema.meta.ParameterValue"
    icon = "fa-tag"

    static FIELD_ID = "id";
    static FIELD_CODE = "code";
    static FIELD_PARAMETER_ID = "parm";
    static FIELD_VAL = "val";

    static N_ID = 10;
    static N_CODE = 30;
    static N_PARAMETER_ID = 10;
    static N_VAL = 2147483647;

    static validators = new ParameterValueValidators();

    static declaredProperty: EntityPropertyMap = {
        id: primaryKey({ type: "integer", nullable: false, precision: 10, validator: this.validators.validateId }),
        code: property({ type: "string", precision: 30, validator: this.validators.validateCode }),
        val: property({ type: "string", nullable: false, validator: this.validators.validateVal }),

        parameter: property({ type: ParameterDef.TYPE, nullable: false, validator: this.validators.validateParameter }),
        parameterId: property({ type: "integer", nullable: false, precision: 10 }),
    }

    constructor() {
        super();
        this.declare(_ParameterValue_stuff_Type.declaredProperty);
    }

}

export default _ParameterValue_stuff_Type;
