import { JSON_VARIANT } from "skel01-core/src/lang/bas-info";
import { INT, STRING } from "skel01-core/src/lang/baseinfo";
import type { int } from "skel01-core/src/lang/basetype";
import { property } from "skel01-dba/src/net/bodz/lily/entity/EntityType";

import IdEntityTypeInfo from "../../concrete/IdEntityTypeInfo";
import { VAppCategory_TYPE } from "./VAppCategoryTypeInfo";
import { VAppRequest_TYPE } from "./VAppRequestTypeInfo";
import _VApp_stuff_Validators from "./_VApp_stuff_Validators";

export class _VApp_stuff_TypeInfo extends IdEntityTypeInfo {

    static SCHEMA_NAME = "lily";
    static TABLE_NAME = "vapp";

    static readonly FIELD_CODE = "code";
    static readonly FIELD_PROPERTIES = "props";
    static readonly FIELD_REQ_ID = "req";
    static readonly FIELD_CATEGORY_ID = "cat";
    static readonly FIELD_SECRET = "secret";

    static readonly N_CODE = 30;
    static readonly N_PROPERTIES = 2147483647;
    static readonly N_REQ_ID = 10;
    static readonly N_CATEGORY_ID = 10;
    static readonly N_SECRET = 2147483647;

    readonly validators = new _VApp_stuff_Validators(this);

    constructor() {
        super(INT);
    }

    get name() { return "net.bodz.lily.schema.vapp.VApp"; }
    get icon() { return "fa-tag"; }

    override preamble() {
        super.preamble();
        this.declare({
            code: property({ type: STRING, precision: 30, validator: this.validators.validateCode }),
            properties: property({ type: JSON_VARIANT, validator: this.validators.validateProperties }),
            secret: property({ type: STRING, nullable: false, validator: this.validators.validateSecret }),

            category: property({ type: VAppCategory_TYPE, validator: this.validators.validateCategory }),
            categoryId: property({ type: INT, precision: 10 }),

            req: property({ type: VAppRequest_TYPE, validator: this.validators.validateReq }),
            reqId: property({ type: INT, precision: 10 }),
        });
    }

    static readonly INSTANCE = new _VApp_stuff_TypeInfo();

}

export default _VApp_stuff_TypeInfo;

export const _VApp_stuff_TYPE = _VApp_stuff_TypeInfo.INSTANCE;
