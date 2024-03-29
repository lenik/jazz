import type { int } from "@skeljs/core/src/lang/basetype";
import { EntityPropertyMap, primaryKey, property } from "@skeljs/dba/src/net/bodz/lily/entity/EntityType";

import CoEntityTypeInfo from "../../concrete/CoEntityTypeInfo";
import _ApiType_stuff_Validators from "./_ApiType_stuff_Validators";

export class _ApiType_stuff_TypeInfo extends CoEntityTypeInfo {

    static SCHEMA_NAME = "lily";
    static TABLE_NAME = "apitype";

    get name() { return "net.bodz.lily.schema.vapp.ApiType"; }
    get icon() { return "fa-tag"; }

    static FIELD_ID = "id";
    static FIELD_CODE = "code";
    static FIELD_UOM = "uom";

    static N_ID = 10;
    static N_CODE = 30;
    static N_UOM = 30;

    validators = new _ApiType_stuff_Validators(this);

    declaredProperty: EntityPropertyMap = {
        id: primaryKey({ type: "int", nullable: false, precision: 10, validator: this.validators.validateId }),
        code: property({ type: "string", precision: 30, validator: this.validators.validateCode }),
        uom: property({ type: "string", nullable: false, precision: 30, validator: this.validators.validateUom }),
    }

    constructor() {
        super();
        this.declare(this.declaredProperty);
    }

}

export default _ApiType_stuff_TypeInfo;
