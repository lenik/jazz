import type { integer, long } from "@skeljs/core/src/lang/type";
import { EntityPropertyMap, primaryKey, property } from "@skeljs/dba/src/net/bodz/lily/entity";

import CoEntityTypeInfo from "../../concrete/CoEntityTypeInfo";
import ApiTypeTypeInfo from "./ApiTypeTypeInfo";
import VAppTypeInfo from "./VAppTypeInfo";
import _VApi_stuff_Validators from "./_VApi_stuff_Validators";

export class _VApi_stuff_TypeInfo extends CoEntityTypeInfo {

    static SCHEMA_NAME = "lily";
    static TABLE_NAME = "vapi";

    name = "net.bodz.lily.schema.vapp.VApi"
    icon = "fa-tag"

    static FIELD_ID = "id";
    static FIELD_APP_ID = "app";
    static FIELD_API_ID = "api";
    static FIELD_CALLBACK = "callback";

    static N_ID = 19;
    static N_APP_ID = 10;
    static N_API_ID = 10;
    static N_CALLBACK = 200;

    validators = new _VApi_stuff_Validators(this);

    declaredProperty: EntityPropertyMap = {
        id: primaryKey({ type: "long", nullable: false, precision: 19, validator: this.validators.validateId }),
        callback: property({ type: "string", precision: 200, validator: this.validators.validateCallback }),

        api: property({ type: ApiTypeTypeInfo, nullable: false, validator: this.validators.validateApi }),
        apiId: property({ type: "integer", nullable: false, precision: 10 }),

        app: property({ type: VAppTypeInfo, nullable: false, validator: this.validators.validateApp }),
        appId: property({ type: "integer", nullable: false, precision: 10 }),
    }

    constructor() {
        super();
        this.declare(this.declaredProperty);
    }

}

export default _VApi_stuff_TypeInfo;
