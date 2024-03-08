import { BIG_DECIMAL, INT } from "@skeljs/core/src/lang/baseinfo";
import type { int } from "@skeljs/core/src/lang/basetype";
import { primaryKey, property } from "@skeljs/dba/src/net/bodz/lily/entity/EntityType";

import CoEntityTypeInfo from "../../concrete/CoEntityTypeInfo";
import ApiType from "./ApiType";
import VApp from "./VApp";
import _VApiCredit_stuff_Validators from "./_VApiCredit_stuff_Validators";

export class _VApiCredit_stuff_TypeInfo extends CoEntityTypeInfo {

    static SCHEMA_NAME = "lily";
    static TABLE_NAME = "vapi_credit";

    get name() { return "net.bodz.lily.schema.vapp.VApiCredit"; }
    get icon() { return "fa-tag"; }

    static FIELD_ID = "id";
    static FIELD_APP_ID = "app";
    static FIELD_API_ID = "api";
    static FIELD_CREDIT = "credit";

    static N_ID = 10;
    static N_APP_ID = 10;
    static N_API_ID = 10;
    static N_CREDIT = 20;

    validators = new _VApiCredit_stuff_Validators(this);

    override preamble() {
        super.preamble();
        this.declare({
            id: primaryKey({ type: INT, nullable: false, precision: 10, validator: this.validators.validateId }),
            credit: property({ type: BIG_DECIMAL, nullable: false, precision: 20, scale: 4, validator: this.validators.validateCredit }),

            api: property({ type: ApiType.TYPE, nullable: false, validator: this.validators.validateApi }),
            apiId: property({ type: INT, nullable: false, precision: 10 }),

            app: property({ type: VApp.TYPE, nullable: false, validator: this.validators.validateApp }),
            appId: property({ type: INT, nullable: false, precision: 10 }),
        });
    }

    constructor() {
        super();
    }

}

export default _VApiCredit_stuff_TypeInfo;
