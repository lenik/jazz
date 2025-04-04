import { INT, LONG, STRING } from "skel01-core/src/lang/baseinfo";
import type { long } from "skel01-core/src/lang/basetype";
import { property } from "skel01-dba/src/net/bodz/lily/entity/EntityType";

import IdEntityTypeInfo from "../../concrete/IdEntityTypeInfo";
import { ApiType_TYPE } from "./ApiTypeTypeInfo";
import { VApp_TYPE } from "./VAppTypeInfo";
import _VApiLog_stuff_Validators from "./_VApiLog_stuff_Validators";

export class _VApiLog_stuff_TypeInfo extends IdEntityTypeInfo {

    static SCHEMA_NAME = "lily";
    static TABLE_NAME = "vapi_log";

    static readonly FIELD_APP_ID = "app";
    static readonly FIELD_API_ID = "api";
    static readonly FIELD_MESSAGE = "message";
    static readonly FIELD_ERR = "err";

    static readonly N_APP_ID = 10;
    static readonly N_API_ID = 10;
    static readonly N_MESSAGE = 2147483647;
    static readonly N_ERR = 2147483647;

    readonly validators = new _VApiLog_stuff_Validators(this);

    constructor() {
        super(LONG);
    }

    get name() { return "net.bodz.lily.schema.vapp.VApiLog"; }
    get icon() { return "fa-tag"; }

    override preamble() {
        super.preamble();
        this.declare({
            message: property({ type: STRING, validator: this.validators.validateMessage }),
            err: property({ type: STRING, validator: this.validators.validateErr }),

            api: property({ type: ApiType_TYPE, validator: this.validators.validateApi }),
            apiId: property({ type: INT, precision: 10 }),

            app: property({ type: VApp_TYPE, nullable: false, validator: this.validators.validateApp }),
            appId: property({ type: INT, nullable: false, precision: 10 }),
        });
    }

    static readonly INSTANCE = new _VApiLog_stuff_TypeInfo();

}

export default _VApiLog_stuff_TypeInfo;

export const _VApiLog_stuff_TYPE = _VApiLog_stuff_TypeInfo.INSTANCE;
