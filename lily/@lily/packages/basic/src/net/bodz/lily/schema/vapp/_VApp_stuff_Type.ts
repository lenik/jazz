
import type { CoEntityType } from "@skeljs/dba/src/net/bodz/lily/concrete/CoEntityType";
import { primaryKey, property } from "@skeljs/dba/src/net/bodz/lily/entity";
import type { EntityPropertyMap } from "@skeljs/dba/src/net/bodz/lily/entity";

import type { Integer } from "../../../../../java/lang/Integer";
import { * as validators } from "./PersonValidators";

// Type Info

export class _VApp_stuff_Type extends CoEntityType {

    static const SCHEMA_NAME = "lily";
    static const TABLE_NAME = "vapp";

    name = "net.bodz.lily.schema.vapp.VApp"
    icon = "fa-tag"

    static const FIELD_ID = "id";
    static const FIELD_CODE = "code";
    static const FIELD_REQ_ID = "req";
    static const FIELD_CATEGORY_ID = "cat";
    static const FIELD_SECRET = "secret";

    static const N_ID = 10;
    static const N_CODE = 30;
    static const N_REQ_ID = 10;
    static const N_CATEGORY_ID = 10;
    static const N_SECRET = 2147483647;

    static declaredProperty: EntityPropertyMap = {
        id: primaryKey({ type: "int", nullable: false, precision: 10, validator: validators.validate_id }),
        code: property({ type: "string", precision: 30, validator: validators.validate_code }),
        secret: property({ type: "string", nullable: false, validator: validators.validate_secret }),

        category: property({ type: "net.bodz.lily.schema.vapp.VAppCat", validator: validators.validate_category }),
        categoryId: property({ type: "integer", precision: 10, validator: validators.validate_categoryId }),

        req: property({ type: "net.bodz.lily.schema.vapp.VAppRequest", validator: validators.validate_req }),
        reqId: property({ type: "integer", precision: 10, validator: validators.validate_reqId }),
    }

    constructor() {
        super();
        this.declare(_VApp_stuff_Type.declaredProperty);
    }

}
