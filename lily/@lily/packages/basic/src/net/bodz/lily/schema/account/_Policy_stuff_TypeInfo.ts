import { INT, STRING } from "@skeljs/core/src/lang/baseinfo";
import type { int } from "@skeljs/core/src/lang/basetype";
import { primaryKey, property } from "@skeljs/dba/src/net/bodz/lily/entity/EntityType";

import CoEntityTypeInfo from "../../concrete/CoEntityTypeInfo";
import _Policy_stuff_Validators from "./_Policy_stuff_Validators";

export class _Policy_stuff_TypeInfo extends CoEntityTypeInfo {

    static SCHEMA_NAME = "lily";
    static TABLE_NAME = "policy";

    get name() { return "net.bodz.lily.schema.account.Policy"; }
    get icon() { return "fa-tag"; }
    get description() { return "Security Policy"; }

    static FIELD_ID = "id";
    static FIELD_NAME = "name";
    static FIELD_CONTROL_CLASS = "cclass";
    static FIELD_METHOD_NAME = "method";
    static FIELD_ALLOW_BITS = "allow";
    static FIELD_DENY_BITS = "deny";

    static N_ID = 10;
    static N_NAME = 30;
    static N_CONTROL_CLASS = 80;
    static N_METHOD_NAME = 80;
    static N_ALLOW_BITS = 10;
    static N_DENY_BITS = 10;

    validators = new _Policy_stuff_Validators(this);

    override preamble() {
        super.preamble();
        this.declare({
            id: primaryKey({ type: INT, nullable: false, precision: 10, validator: this.validators.validateId }),
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

    constructor() {
        super();
    }

}

export default _Policy_stuff_TypeInfo;
