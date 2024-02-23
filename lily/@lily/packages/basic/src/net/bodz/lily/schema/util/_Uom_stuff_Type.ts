import type { double, integer } from "@skeljs/core/src/lang/type";
import CoEntityType from "@skeljs/dba/src/net/bodz/lily/concrete/CoEntityType";
import { EntityPropertyMap, primaryKey, property } from "@skeljs/dba/src/net/bodz/lily/entity";

import { Uom } from "./Uom";
import UomValidators from "./UomValidators";

export class _Uom_stuff_Type extends CoEntityType {

    static SCHEMA_NAME = "lily";
    static TABLE_NAME = "uom";

    name = "net.bodz.lily.schema.util.Uom"
    icon = "fa-tag"

    static FIELD_ID = "id";
    static FIELD_CODE = "code";
    static FIELD_PROP = "prop";
    static FIELD_STD_ID = "std";
    static FIELD_SCALE = "scale";

    static N_ID = 10;
    static N_CODE = 30;
    static N_PROP = 30;
    static N_STD_ID = 10;
    static N_SCALE = 17;

    static validators = new UomValidators();

    static declaredProperty: EntityPropertyMap = {
        id: primaryKey({ type: "integer", nullable: false, precision: 10, validator: this.validators.validateId }),
        code: property({ type: "string", precision: 30, validator: this.validators.validateCode }),
        prop: property({ type: "string", nullable: false, precision: 30, validator: this.validators.validateProp }),
        scale: property({ type: "double", nullable: false, precision: 17, scale: 17, validator: this.validators.validateScale }),

        std: property({ type: Uom.TYPE, validator: this.validators.validateStd }),
        stdId: property({ type: "integer", precision: 10 }),
    }

    constructor() {
        super();
        this.declare(_Uom_stuff_Type.declaredProperty);
    }

}

export default _Uom_stuff_Type;
