import { INT, STRING } from "@skeljs/core/src/lang/baseinfo";
import type { int } from "@skeljs/core/src/lang/basetype";
import { property } from "@skeljs/dba/src/net/bodz/lily/entity/EntityType";

import CoImagedTypeInfo from "../../concrete/CoImagedTypeInfo";
import _Policy_stuff_Validators from "./_Policy_stuff_Validators";

export class _Policy_stuff_TypeInfo extends CoImagedTypeInfo {

    static SCHEMA_NAME = "lily";
    static TABLE_NAME = "policy";

    static readonly FIELD_NAME = "name";
    static readonly FIELD_CONTROL_CLASS = "cclass";
    static readonly FIELD_METHOD_NAME = "method";
    static readonly FIELD_ALLOW_BITS = "allow";
    static readonly FIELD_DENY_BITS = "deny";

    static readonly N_NAME = 30;
    static readonly N_CONTROL_CLASS = 80;
    static readonly N_METHOD_NAME = 80;
    static readonly N_ALLOW_BITS = 10;
    static readonly N_DENY_BITS = 10;

    readonly validators = new _Policy_stuff_Validators(this);

    constructor() {
        super(INT);
    }

    get name() { return "net.bodz.lily.schema.account.Policy"; }
    get icon() { return "fa-tag"; }
    get description() { return "Security Policy"; }

    override preamble() {
        super.preamble();
        this.declare({
            name: property({ type: STRING, precision: 30, 
                description: "The policy name (unique)", 
                validator: this.validators.validateName }),
            controlClass: property({ type: STRING, nullable: false, precision: 80, 
                description: "The control class", 
                validator: this.validators.validateControlClass }),
            methodName: property({ type: STRING, precision: 80, 
                description: "The method name", 
                validator: this.validators.validateMethodName }),
            allowBits: property({ type: INT, nullable: false, precision: 10, 
                description: "allow", 
                validator: this.validators.validateAllowBits }),
            denyBits: property({ type: INT, nullable: false, precision: 10, 
                description: "deny", 
                validator: this.validators.validateDenyBits }),
        });
    }

    static readonly INSTANCE = new _Policy_stuff_TypeInfo();

}

export default _Policy_stuff_TypeInfo;

export const _Policy_stuff_TYPE = _Policy_stuff_TypeInfo.INSTANCE;
