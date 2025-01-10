import { BIG_DECIMAL, INT } from "skel01-core/src/lang/baseinfo";
import type { int } from "skel01-core/src/lang/basetype";
import { property } from "skel01-dba/src/net/bodz/lily/entity/EntityType";

import IdEntityTypeInfo from "../../concrete/IdEntityTypeInfo";
import { ApiType_TYPE } from "./ApiTypeTypeInfo";
import { VApp_TYPE } from "./VAppTypeInfo";
import _VApiCredit_stuff_Validators from "./_VApiCredit_stuff_Validators";

export class _VApiCredit_stuff_TypeInfo extends IdEntityTypeInfo {

    static SCHEMA_NAME = "lily";
    static TABLE_NAME = "vapi_credit";

    static readonly FIELD_APP_ID = "app";
    static readonly FIELD_API_ID = "api";
    static readonly FIELD_CREDIT = "credit";

    static readonly N_APP_ID = 10;
    static readonly N_API_ID = 10;
    static readonly N_CREDIT = 20;

    readonly validators = new _VApiCredit_stuff_Validators(this);

    constructor() {
        super(INT);
    }

    get name() { return "net.bodz.lily.schema.vapp.VApiCredit"; }
    get icon() { return "fa-tag"; }

    override preamble() {
        super.preamble();
        this.declare({
            credit: property({ type: BIG_DECIMAL, nullable: false, precision: 20, scale: 4, validator: this.validators.validateCredit }),

            api: property({ type: ApiType_TYPE, nullable: false, validator: this.validators.validateApi }),
            apiId: property({ type: INT, nullable: false, precision: 10 }),

            app: property({ type: VApp_TYPE, nullable: false, validator: this.validators.validateApp }),
            appId: property({ type: INT, nullable: false, precision: 10 }),
        });
    }

    static readonly INSTANCE = new _VApiCredit_stuff_TypeInfo();

}

export default _VApiCredit_stuff_TypeInfo;

export const _VApiCredit_stuff_TYPE = _VApiCredit_stuff_TypeInfo.INSTANCE;
