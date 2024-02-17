
import type { CoEntityType } from "@skeljs/dba/src/net/bodz/lily/concrete/CoEntityType";
import { primaryKey, property } from "@skeljs/dba/src/net/bodz/lily/entity";
import type { EntityPropertyMap } from "@skeljs/dba/src/net/bodz/lily/entity";

import type { Integer } from "../../../../../java/lang/Integer";
import { * as validators } from "./PersonValidators";

// Type Info

export class _VApiCredit_stuff_Type extends CoEntityType {

    static const SCHEMA_NAME = "lily";
    static const TABLE_NAME = "vapi_credit";

    name = "net.bodz.lily.schema.vapp.VApiCredit"
    icon = "fa-tag"

    static const FIELD_ID = "id";
    static const FIELD_APP_ID = "app";
    static const FIELD_API_ID = "api";
    static const FIELD_CREDIT = "credit";

    static const N_ID = 10;
    static const N_APP_ID = 10;
    static const N_API_ID = 10;
    static const N_CREDIT = 20;

    static declaredProperty: EntityPropertyMap = {
        id: primaryKey({ type: "int", nullable: false, precision: 10, validator: validators.validate_id }),
        credit: property({ type: "BigInteger", nullable: false, precision: 20, scale: 4, validator: validators.validate_credit }),

        api: property({ type: "net.bodz.lily.schema.vapp.ApiType", nullable: false, validator: validators.validate_api }),
        apiId: property({ type: "int", nullable: false, precision: 10, validator: validators.validate_apiId }),

        app: property({ type: "net.bodz.lily.schema.vapp.VApp", nullable: false, validator: validators.validate_app }),
        appId: property({ type: "int", nullable: false, precision: 10, validator: validators.validate_appId }),
    }

    constructor() {
        super();
        this.declare(_VApiCredit_stuff_Type.declaredProperty);
    }

}
