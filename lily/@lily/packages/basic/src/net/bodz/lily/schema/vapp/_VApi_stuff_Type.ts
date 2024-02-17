
import type { CoEntityType } from "@skeljs/dba/src/net/bodz/lily/concrete/CoEntityType";
import { primaryKey, property } from "@skeljs/dba/src/net/bodz/lily/entity";
import type { EntityPropertyMap } from "@skeljs/dba/src/net/bodz/lily/entity";

import type { Long } from "../../../../../java/lang/Long";
import { * as validators } from "./PersonValidators";

// Type Info

export class _VApi_stuff_Type extends CoEntityType {

    static const SCHEMA_NAME = "lily";
    static const TABLE_NAME = "vapi";

    name = "net.bodz.lily.schema.vapp.VApi"
    icon = "fa-tag"

    static const FIELD_ID = "id";
    static const FIELD_APP_ID = "app";
    static const FIELD_API_ID = "api";
    static const FIELD_CALLBACK = "callback";

    static const N_ID = 19;
    static const N_APP_ID = 10;
    static const N_API_ID = 10;
    static const N_CALLBACK = 200;

    static declaredProperty: EntityPropertyMap = {
        id: primaryKey({ type: "long", nullable: false, precision: 19, validator: validators.validate_id }),
        callback: property({ type: "string", precision: 200, validator: validators.validate_callback }),

        api: property({ type: "net.bodz.lily.schema.vapp.ApiType", nullable: false, validator: validators.validate_api }),
        apiId: property({ type: "int", nullable: false, precision: 10, validator: validators.validate_apiId }),

        app: property({ type: "net.bodz.lily.schema.vapp.VApp", nullable: false, validator: validators.validate_app }),
        appId: property({ type: "int", nullable: false, precision: 10, validator: validators.validate_appId }),
    }

    constructor() {
        super();
        this.declare(_VApi_stuff_Type.declaredProperty);
    }

}
