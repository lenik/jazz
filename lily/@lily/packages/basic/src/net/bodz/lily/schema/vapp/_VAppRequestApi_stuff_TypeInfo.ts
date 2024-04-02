import { INT, LONG } from "@skeljs/core/src/lang/baseinfo";
import type { long } from "@skeljs/core/src/lang/basetype";
import { property } from "@skeljs/dba/src/net/bodz/lily/entity/EntityType";

import IdEntityTypeInfo from "../../concrete/IdEntityTypeInfo";
import { ApiType_TYPE } from "./ApiTypeTypeInfo";
import { VAppRequest_TYPE } from "./VAppRequestTypeInfo";
import _VAppRequestApi_stuff_Validators from "./_VAppRequestApi_stuff_Validators";

export class _VAppRequestApi_stuff_TypeInfo extends IdEntityTypeInfo {

    static SCHEMA_NAME = "lily";
    static TABLE_NAME = "vappreq_api";

    static readonly FIELD_PARENT_ID = "parent";
    static readonly FIELD_API_ID = "api";

    static readonly N_PARENT_ID = 10;
    static readonly N_API_ID = 10;

    readonly validators = new _VAppRequestApi_stuff_Validators(this);

    constructor() {
        super(LONG);
    }

    get name() { return "net.bodz.lily.schema.vapp.VAppRequestApi"; }
    get icon() { return "fa-tag"; }

    override preamble() {
        super.preamble();
        this.declare({

            parent: property({ type: VAppRequest_TYPE, nullable: false, validator: this.validators.validateParent }),
            parentId: property({ type: INT, nullable: false, precision: 10 }),

            api: property({ type: ApiType_TYPE, nullable: false, validator: this.validators.validateApi }),
            apiId: property({ type: INT, nullable: false, precision: 10 }),
        });
    }

    static readonly INSTANCE = new _VAppRequestApi_stuff_TypeInfo();

}

export default _VAppRequestApi_stuff_TypeInfo;

export const _VAppRequestApi_stuff_TYPE = _VAppRequestApi_stuff_TypeInfo.INSTANCE;
