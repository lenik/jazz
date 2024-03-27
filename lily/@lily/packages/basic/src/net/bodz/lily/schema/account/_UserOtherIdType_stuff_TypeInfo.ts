import { INT } from "@skeljs/core/src/lang/baseinfo";
import type { int } from "@skeljs/core/src/lang/basetype";
import { property } from "@skeljs/dba/src/net/bodz/lily/entity/EntityType";

import CoImagedTypeInfo from "../../concrete/CoImagedTypeInfo";
import _UserOtherIdType_stuff_Validators from "./_UserOtherIdType_stuff_Validators";

export class _UserOtherIdType_stuff_TypeInfo extends CoImagedTypeInfo {

    static SCHEMA_NAME = "lily";
    static TABLE_NAME = "useroidtype";

    static readonly FIELD_DUMMY = "dummy";

    static readonly N_DUMMY = 10;

    readonly validators = new _UserOtherIdType_stuff_Validators(this);

    constructor() {
        super(INT);
    }

    get name() { return "net.bodz.lily.schema.account.UserOtherIdType"; }
    get icon() { return "fa-tag"; }
    get description() { return "Type of Open ID"; }

    override preamble() {
        super.preamble();
        this.declare({
            dummy: property({ type: INT, precision: 10, validator: this.validators.validateDummy }),
        });
    }

    static readonly INSTANCE = new _UserOtherIdType_stuff_TypeInfo();

}

export default _UserOtherIdType_stuff_TypeInfo;
