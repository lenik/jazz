import type { integer } from "@skeljs/core/src/lang/type";
import CoEntityTypeInfo from "@skeljs/dba/src/net/bodz/lily/concrete/CoEntityTypeInfo";
import { EntityPropertyMap, primaryKey, property } from "@skeljs/dba/src/net/bodz/lily/entity";

import _VApiCredit_stuff_Validators from "./_VApiCredit_stuff_Validators";

export class _VApiCredit_stuff_TypeInfo extends CoEntityTypeInfo {

    static SCHEMA_NAME = "lily";
    static TABLE_NAME = "vapi_credit";

    name = "net.bodz.lily.schema.vapp.VApiCredit"
    icon = "fa-tag"

    static FIELD_ID = "id";
    static FIELD_APP_ID = "app";
    static FIELD_API_ID = "api";
    static FIELD_CREDIT = "credit";

    static N_ID = 10;
    static N_APP_ID = 10;
    static N_API_ID = 10;
    static N_CREDIT = 20;

    static validators = new _VApiCredit_stuff_Validators();

    static declaredProperty: EntityPropertyMap = {
        id: primaryKey({ type: "integer", nullable: false, precision: 10, validator: this.validators.validateId }),
        credit: property({ type: "BigInteger", nullable: false, precision: 20, scale: 4, validator: this.validators.validateCredit }),

        api: property({ type: net.bodz.lily.schema.vapp.ApiTypeTypeInfo, nullable: false, validator: this.validators.validateApi }),
        apiId: property({ type: "integer", nullable: false, precision: 10 }),

        app: property({ type: net.bodz.lily.schema.vapp.VAppTypeInfo, nullable: false, validator: this.validators.validateApp }),
        appId: property({ type: "integer", nullable: false, precision: 10 }),
    }

    constructor() {
        super();
        this.declare(_VApiCredit_stuff_TypeInfo.declaredProperty);
    }

}

export default _VApiCredit_stuff_TypeInfo;
