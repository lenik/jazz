
import type { CoEntityType } from "@skeljs/dba/src/net/bodz/lily/concrete/CoEntityType";
import { primaryKey, property } from "@skeljs/dba/src/net/bodz/lily/entity";
import type { EntityPropertyMap } from "@skeljs/dba/src/net/bodz/lily/entity";

import type { Integer } from "../../../../../java/lang/Integer";
import { * as validators } from "./PersonValidators";

// Type Info

export class _Uom_stuff_Type extends CoEntityType {

    static const SCHEMA_NAME = "lily";
    static const TABLE_NAME = "uom";

    name = "net.bodz.lily.schema.util.Uom"
    icon = "fa-tag"

    static const FIELD_ID = "id";
    static const FIELD_CODE = "code";
    static const FIELD_PROP = "prop";
    static const FIELD_STD_ID = "std";
    static const FIELD_SCALE = "scale";

    static const N_ID = 10;
    static const N_CODE = 30;
    static const N_PROP = 30;
    static const N_STD_ID = 10;
    static const N_SCALE = 17;

    static declaredProperty: EntityPropertyMap = {
        id: primaryKey({ type: "int", nullable: false, precision: 10, validator: validators.validate_id }),
        code: property({ type: "string", precision: 30, validator: validators.validate_code }),
        prop: property({ type: "string", nullable: false, precision: 30, validator: validators.validate_prop }),
        scale: property({ type: "double", nullable: false, precision: 17, scale: 17, validator: validators.validate_scale }),

        std: property({ type: "net.bodz.lily.schema.util.Uom", validator: validators.validate_std }),
        stdId: property({ type: "integer", precision: 10, validator: validators.validate_stdId }),
    }

    constructor() {
        super();
        this.declare(_Uom_stuff_Type.declaredProperty);
    }

}
