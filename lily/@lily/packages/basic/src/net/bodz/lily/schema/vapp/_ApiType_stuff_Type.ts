
import type { CoEntityType } from "@skeljs/dba/src/net/bodz/lily/concrete/CoEntityType";
import { primaryKey, property } from "@skeljs/dba/src/net/bodz/lily/entity";
import type { EntityPropertyMap } from "@skeljs/dba/src/net/bodz/lily/entity";

import type { Integer } from "../../../../../java/lang/Integer";
import { * as validators } from "./PersonValidators";

// Type Info

export class _ApiType_stuff_Type extends CoEntityType {

    static const SCHEMA_NAME = "lily";
    static const TABLE_NAME = "apitype";

    name = "net.bodz.lily.schema.vapp.ApiType"
    icon = "fa-tag"

    static const FIELD_ID = "id";
    static const FIELD_CODE = "code";
    static const FIELD_UOM = "uom";

    static const N_ID = 10;
    static const N_CODE = 30;
    static const N_UOM = 30;

    static declaredProperty: EntityPropertyMap = {
        id: primaryKey({ type: "int", nullable: false, precision: 10, validator: validators.validate_id }),
        code: property({ type: "string", precision: 30, validator: validators.validate_code }),
        uom: property({ type: "string", nullable: false, precision: 30, validator: validators.validate_uom }),
    }

    constructor() {
        super();
        this.declare(_ApiType_stuff_Type.declaredProperty);
    }

}
