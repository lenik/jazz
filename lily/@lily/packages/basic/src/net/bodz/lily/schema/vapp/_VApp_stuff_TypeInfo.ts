import { INT, STRING } from "@skeljs/core/src/lang/baseinfo";
import type { int } from "@skeljs/core/src/lang/basetype";
import { primaryKey, property } from "@skeljs/dba/src/net/bodz/lily/entity/EntityType";

import CoEntityTypeInfo from "../../concrete/CoEntityTypeInfo";
import VAppCat from "./VAppCat";
import VAppRequest from "./VAppRequest";
import _VApp_stuff_Validators from "./_VApp_stuff_Validators";

export class _VApp_stuff_TypeInfo extends CoEntityTypeInfo {

    static SCHEMA_NAME = "lily";
    static TABLE_NAME = "vapp";

    get name() { return "net.bodz.lily.schema.vapp.VApp"; }
    get icon() { return "fa-tag"; }

    static FIELD_ID = "id";
    static FIELD_CODE = "code";
    static FIELD_REQ_ID = "req";
    static FIELD_CATEGORY_ID = "cat";
    static FIELD_SECRET = "secret";

    static N_ID = 10;
    static N_CODE = 30;
    static N_REQ_ID = 10;
    static N_CATEGORY_ID = 10;
    static N_SECRET = 2147483647;

    validators = new _VApp_stuff_Validators(this);

    override preamble() {
        super.preamble();
        this.declare({
            id: primaryKey({ type: INT, nullable: false, precision: 10, validator: this.validators.validateId }),
            code: property({ type: STRING, precision: 30, validator: this.validators.validateCode }),
            secret: property({ type: STRING, nullable: false, validator: this.validators.validateSecret }),

            category: property({ type: VAppCat.TYPE, validator: this.validators.validateCategory }),
            categoryId: property({ type: INT, precision: 10 }),

            req: property({ type: VAppRequest.TYPE, validator: this.validators.validateReq }),
            reqId: property({ type: INT, precision: 10 }),
        });
    }

    constructor() {
        super();
    }

}

export default _VApp_stuff_TypeInfo;
