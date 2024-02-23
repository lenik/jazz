import type { integer } from "@skeljs/core/src/lang/type";
import CoEntityType from "@skeljs/dba/src/net/bodz/lily/concrete/CoEntityType";
import { EntityPropertyMap, primaryKey, property } from "@skeljs/dba/src/net/bodz/lily/entity";

import { VAppCat } from "./VAppCat";
import { VAppRequest } from "./VAppRequest";
import VAppValidators from "./VAppValidators";

export class _VApp_stuff_Type extends CoEntityType {

    static SCHEMA_NAME = "lily";
    static TABLE_NAME = "vapp";

    name = "net.bodz.lily.schema.vapp.VApp"
    icon = "fa-tag"

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

    static validators = new VAppValidators();

    static declaredProperty: EntityPropertyMap = {
        id: primaryKey({ type: "integer", nullable: false, precision: 10, validator: this.validators.validateId }),
        code: property({ type: "string", precision: 30, validator: this.validators.validateCode }),
        secret: property({ type: "string", nullable: false, validator: this.validators.validateSecret }),

        category: property({ type: VAppCat.TYPE, validator: this.validators.validateCategory }),
        categoryId: property({ type: "integer", precision: 10 }),

        req: property({ type: VAppRequest.TYPE, validator: this.validators.validateReq }),
        reqId: property({ type: "integer", precision: 10 }),
    }

    constructor() {
        super();
        this.declare(_VApp_stuff_Type.declaredProperty);
    }

}

export default _VApp_stuff_Type;
