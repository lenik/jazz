
import type { CoEntityType } from "@skeljs/dba/src/net/bodz/lily/concrete/CoEntityType";
import { primaryKey, property } from "@skeljs/dba/src/net/bodz/lily/entity";
import type { EntityPropertyMap } from "@skeljs/dba/src/net/bodz/lily/entity";

import type { Integer } from "../../../../../java/lang/Integer";
import { * as validators } from "./PersonValidators";

// Type Info

export class _ParameterValue_stuff_Type extends CoEntityType {

    static const SCHEMA_NAME = "lily";
    static const TABLE_NAME = "_parmval";

    name = "net.bodz.lily.schema.meta.ParameterValue"
    icon = "fa-tag"

    static const FIELD_ID = "id";
    static const FIELD_CODE = "code";
    static const FIELD_PARAMETER_ID = "parm";
    static const FIELD_VAL = "val";

    static const N_ID = 10;
    static const N_CODE = 30;
    static const N_PARAMETER_ID = 10;
    static const N_VAL = 2147483647;

    static declaredProperty: EntityPropertyMap = {
        id: primaryKey({ type: "int", nullable: false, precision: 10, validator: validators.validate_id }),
        code: property({ type: "string", precision: 30, validator: validators.validate_code }),
        val: property({ type: "string", nullable: false, validator: validators.validate_val }),

        parameter: property({ type: "net.bodz.lily.schema.meta.ParameterDef", nullable: false, validator: validators.validate_parameter }),
        parameterId: property({ type: "int", nullable: false, precision: 10, validator: validators.validate_parameterId }),
    }

    constructor() {
        super();
        this.declare(_ParameterValue_stuff_Type.declaredProperty);
    }

}
