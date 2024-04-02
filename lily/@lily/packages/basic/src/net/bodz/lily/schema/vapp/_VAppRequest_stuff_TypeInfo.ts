import { INT, STRING } from "@skeljs/core/src/lang/baseinfo";
import type { int } from "@skeljs/core/src/lang/basetype";
import { property } from "@skeljs/dba/src/net/bodz/lily/entity/EntityType";

import CoMessageTypeInfo from "../../concrete/CoMessageTypeInfo";
import _VAppRequest_stuff_Validators from "./_VAppRequest_stuff_Validators";

export class _VAppRequest_stuff_TypeInfo extends CoMessageTypeInfo {

    static SCHEMA_NAME = "lily";
    static TABLE_NAME = "vappreq";

    static readonly FIELD_CODE = "code";
    static readonly FIELD_FORM_ARGUMENTS = "formargs";
    static readonly FIELD_DUMMY = "dummy";

    static readonly N_CODE = 30;
    static readonly N_FORM_ARGUMENTS = 2147483647;
    static readonly N_DUMMY = 10;

    readonly validators = new _VAppRequest_stuff_Validators(this);

    constructor() {
        super(INT);
    }

    get name() { return "net.bodz.lily.schema.vapp.VAppRequest"; }
    get icon() { return "fa-tag"; }

    override preamble() {
        super.preamble();
        this.declare({
            code: property({ type: STRING, precision: 30, validator: this.validators.validateCode }),
            formArguments: property({ type: STRING, validator: this.validators.validateFormArguments }),
            dummy: property({ type: INT, precision: 10, validator: this.validators.validateDummy }),
        });
    }

    static readonly INSTANCE = new _VAppRequest_stuff_TypeInfo();

}

export default _VAppRequest_stuff_TypeInfo;

export const _VAppRequest_stuff_TYPE = _VAppRequest_stuff_TypeInfo.INSTANCE;
