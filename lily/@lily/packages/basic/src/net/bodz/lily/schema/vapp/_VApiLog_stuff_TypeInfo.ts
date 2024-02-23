import type { integer, long } from "@skeljs/core/src/lang/type";
import CoEntityTypeInfo from "@skeljs/dba/src/net/bodz/lily/concrete/CoEntityTypeInfo";
import { EntityPropertyMap, primaryKey, property } from "@skeljs/dba/src/net/bodz/lily/entity";

import _VApiLog_stuff_Validators from "./_VApiLog_stuff_Validators";

export class _VApiLog_stuff_TypeInfo extends CoEntityTypeInfo {

    static SCHEMA_NAME = "lily";
    static TABLE_NAME = "vapi_log";

    name = "net.bodz.lily.schema.vapp.VApiLog"
    icon = "fa-tag"

    static FIELD_ID = "id";
    static FIELD_APP_ID = "app";
    static FIELD_API_ID = "api";
    static FIELD_MESSAGE = "message";
    static FIELD_ERR = "err";

    static N_ID = 19;
    static N_APP_ID = 10;
    static N_API_ID = 10;
    static N_MESSAGE = 2147483647;
    static N_ERR = 2147483647;

    static validators = new _VApiLog_stuff_Validators();

    static declaredProperty: EntityPropertyMap = {
        id: primaryKey({ type: "long", nullable: false, precision: 19, validator: this.validators.validateId }),
        message: property({ type: "string", validator: this.validators.validateMessage }),
        err: property({ type: "string", validator: this.validators.validateErr }),

        api: property({ type: net.bodz.lily.schema.vapp.ApiTypeTypeInfo, validator: this.validators.validateApi }),
        apiId: property({ type: "integer", precision: 10 }),

        app: property({ type: net.bodz.lily.schema.vapp.VAppTypeInfo, nullable: false, validator: this.validators.validateApp }),
        appId: property({ type: "integer", nullable: false, precision: 10 }),
    }

    constructor() {
        super();
        this.declare(_VApiLog_stuff_TypeInfo.declaredProperty);
    }

}

export default _VApiLog_stuff_TypeInfo;
