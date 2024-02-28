import type { double, int } from "@skeljs/core/src/lang/basetype";
import { EntityPropertyMap, primaryKey, property } from "@skeljs/dba/src/net/bodz/lily/entity/EntityType";

import CoEntityTypeInfo from "../../concrete/CoEntityTypeInfo";
import UomTypeInfo from "./UomTypeInfo";
import _Uom_stuff_Validators from "./_Uom_stuff_Validators";

export class _Uom_stuff_TypeInfo extends CoEntityTypeInfo {

    static SCHEMA_NAME = "lily";
    static TABLE_NAME = "uom";

    get name() { return "net.bodz.lily.schema.util.Uom"; }
    get icon() { return "fa-tag"; }

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

    validators = new _Uom_stuff_Validators(this);

    declaredProperty: EntityPropertyMap = {
        id: primaryKey({ type: "int", nullable: false, precision: 10, validator: this.validators.validateId }),
        code: property({ type: "string", precision: 30, validator: this.validators.validateCode }),
        prop: property({ type: "string", nullable: false, precision: 30, validator: this.validators.validateProp }),
        scale: property({ type: "double", nullable: false, precision: 17, scale: 17, validator: this.validators.validateScale }),

        std: property({ type: UomTypeInfo, validator: this.validators.validateStd }),
        stdId: property({ type: "int", precision: 10 }),
    }

    constructor() {
        super();
        this.declare(this.declaredProperty);
    }

}

export default _Uom_stuff_TypeInfo;
