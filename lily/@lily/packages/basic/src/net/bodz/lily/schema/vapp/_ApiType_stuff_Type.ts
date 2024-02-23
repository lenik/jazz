import type { integer } from "@skeljs/core/src/lang/type";
import CoEntityType from "@skeljs/dba/src/net/bodz/lily/concrete/CoEntityType";
import { EntityPropertyMap, primaryKey, property } from "@skeljs/dba/src/net/bodz/lily/entity";

import ApiTypeValidators from "./ApiTypeValidators";

export class _ApiType_stuff_Type extends CoEntityType {

    static SCHEMA_NAME = "lily";
    static TABLE_NAME = "apitype";

    name = "net.bodz.lily.schema.vapp.ApiType"
    icon = "fa-tag"

    static FIELD_ID = "id";
    static FIELD_CODE = "code";
    static FIELD_UOM = "uom";

    static N_ID = 10;
    static N_CODE = 30;
    static N_UOM = 30;

    static validators = new ApiTypeValidators();

    static declaredProperty: EntityPropertyMap = {
        id: primaryKey({ type: "integer", nullable: false, precision: 10, validator: this.validators.validateId }),
        code: property({ type: "string", precision: 30, validator: this.validators.validateCode }),
        uom: property({ type: "string", nullable: false, precision: 30, validator: this.validators.validateUom }),
    }

    constructor() {
        super();
        this.declare(_ApiType_stuff_Type.declaredProperty);
    }

}

export default _ApiType_stuff_Type;
