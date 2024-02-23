import type { integer } from "@skeljs/core/src/lang/type";
import CoEntityType from "@skeljs/dba/src/net/bodz/lily/concrete/CoEntityType";
import { EntityPropertyMap, primaryKey, property } from "@skeljs/dba/src/net/bodz/lily/entity";

import { ApiType } from "./ApiType";
import VApiCreditValidators from "./VApiCreditValidators";
import { VApp } from "./VApp";

export class _VApiCredit_stuff_Type extends CoEntityType {

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

    static validators = new VApiCreditValidators();

    static declaredProperty: EntityPropertyMap = {
        id: primaryKey({ type: "integer", nullable: false, precision: 10, validator: this.validators.validateId }),
        credit: property({ type: "BigInteger", nullable: false, precision: 20, scale: 4, validator: this.validators.validateCredit }),

        api: property({ type: ApiType.TYPE, nullable: false, validator: this.validators.validateApi }),
        apiId: property({ type: "integer", nullable: false, precision: 10 }),

        app: property({ type: VApp.TYPE, nullable: false, validator: this.validators.validateApp }),
        appId: property({ type: "integer", nullable: false, precision: 10 }),
    }

    constructor() {
        super();
        this.declare(_VApiCredit_stuff_Type.declaredProperty);
    }

}

export default _VApiCredit_stuff_Type;
