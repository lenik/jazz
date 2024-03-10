import { JSON_VARIANT } from "@skeljs/core/src/lang/bas-info";
import { INT, STRING } from "@skeljs/core/src/lang/baseinfo";
import type { int } from "@skeljs/core/src/lang/basetype";
import { primaryKey, property } from "@skeljs/dba/src/net/bodz/lily/entity/EntityType";

import CoEntityTypeInfo from "../../concrete/CoEntityTypeInfo";
import VAppCategory from "./VAppCategory";
import VAppRequest from "./VAppRequest";
import _VApp_stuff_Validators from "./_VApp_stuff_Validators";

export class _VApp_stuff_TypeInfo extends CoEntityTypeInfo {

    static SCHEMA_NAME = "lily";
    static TABLE_NAME = "vapp";

    static readonly FIELD_ID = "id";
    static readonly FIELD_CODE = "code";
    static readonly FIELD_PROPERTIES = "props";
    static readonly FIELD_REQ_ID = "req";
    static readonly FIELD_CATEGORY_ID = "cat";
    static readonly FIELD_SECRET = "secret";

    static readonly N_ID = 10;
    static readonly N_CODE = 30;
    static readonly N_PROPERTIES = 2147483647;
    static readonly N_REQ_ID = 10;
    static readonly N_CATEGORY_ID = 10;
    static readonly N_SECRET = 2147483647;

    readonly validators = new _VApp_stuff_Validators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.lily.schema.vapp.VApp"; }
    get icon() { return "fa-tag"; }

    override preamble() {
        super.preamble();
        this.declare({
            id: primaryKey({ type: INT, nullable: false, precision: 10, validator: this.validators.validateId }),
            code: property({ type: STRING, precision: 30, validator: this.validators.validateCode }),
            properties: property({ type: JSON_VARIANT, validator: this.validators.validateProperties }),
            secret: property({ type: STRING, nullable: false, validator: this.validators.validateSecret }),

            category: property({ type: VAppCategory.TYPE, validator: this.validators.validateCategory }),
            categoryId: property({ type: INT, precision: 10 }),

            req: property({ type: VAppRequest.TYPE, validator: this.validators.validateReq }),
            reqId: property({ type: INT, precision: 10 }),
        });
    }

    static readonly INSTANCE = new _VApp_stuff_TypeInfo();

}

export default _VApp_stuff_TypeInfo;
