import type { integer } from "@skeljs/core/src/lang/type";
import CoEntityTypeInfo from "@skeljs/dba/src/net/bodz/lily/concrete/CoEntityTypeInfo";
import { EntityPropertyMap, primaryKey, property } from "@skeljs/dba/src/net/bodz/lily/entity";

import _ParameterValue_stuff_Validators from "./_ParameterValue_stuff_Validators";

export class _ParameterValue_stuff_TypeInfo extends CoEntityTypeInfo {

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

    static validators = new _ParameterValue_stuff_Validators();

    static declaredProperty: EntityPropertyMap = {
        id: primaryKey({ type: "integer", nullable: false, precision: 10, validator: this.validators.validateId }),
        code: property({ type: "string", precision: 30, validator: this.validators.validateCode }),
        val: property({ type: "string", nullable: false, validator: this.validators.validateVal }),

        parameter: property({ type: net.bodz.lily.schema.meta.ParameterDefTypeInfo, nullable: false, validator: this.validators.validateParameter }),
        parameterId: property({ type: "integer", nullable: false, precision: 10 }),
    }

    constructor() {
        super();
        this.declare(_ParameterValue_stuff_TypeInfo.declaredProperty);
    }

}

export default _ParameterValue_stuff_TypeInfo;
