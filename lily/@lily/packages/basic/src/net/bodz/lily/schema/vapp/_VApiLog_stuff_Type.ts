
import type { CoEntityType } from "@skeljs/dba/src/net/bodz/lily/concrete/CoEntityType";
import { primaryKey, property } from "@skeljs/dba/src/net/bodz/lily/entity";
import type { EntityPropertyMap } from "@skeljs/dba/src/net/bodz/lily/entity";

import type { Long } from "../../../../../java/lang/Long";
import { * as validators } from "./PersonValidators";

// Type Info

export class _VApiLog_stuff_Type extends CoEntityType {

    static const SCHEMA_NAME = "lily";
    static const TABLE_NAME = "vapi_log";

    name = "net.bodz.lily.schema.vapp.VApiLog"
    icon = "fa-tag"

    static const FIELD_ID = "id";
    static const FIELD_APP_ID = "app";
    static const FIELD_API_ID = "api";
    static const FIELD_MESSAGE = "message";
    static const FIELD_ERR = "err";

    static const N_ID = 19;
    static const N_APP_ID = 10;
    static const N_API_ID = 10;
    static const N_MESSAGE = 2147483647;
    static const N_ERR = 2147483647;

    static declaredProperty: EntityPropertyMap = {
        id: primaryKey({ type: "long", nullable: false, precision: 19, validator: validators.validate_id }),
        message: property({ type: "string", validator: validators.validate_message }),
        err: property({ type: "string", validator: validators.validate_err }),

        api: property({ type: "net.bodz.lily.schema.vapp.ApiType", validator: validators.validate_api }),
        apiId: property({ type: "integer", precision: 10, validator: validators.validate_apiId }),

        app: property({ type: "net.bodz.lily.schema.vapp.VApp", nullable: false, validator: validators.validate_app }),
        appId: property({ type: "int", nullable: false, precision: 10, validator: validators.validate_appId }),
    }

    constructor() {
        super();
        this.declare(_VApiLog_stuff_Type.declaredProperty);
    }

}
