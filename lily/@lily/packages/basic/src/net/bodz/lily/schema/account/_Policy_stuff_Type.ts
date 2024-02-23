import type { integer } from "@skeljs/core/src/lang/type";
import CoEntityType from "@skeljs/dba/src/net/bodz/lily/concrete/CoEntityType";
import { EntityPropertyMap, primaryKey, property } from "@skeljs/dba/src/net/bodz/lily/entity";

import PolicyValidators from "./PolicyValidators";

export class _Policy_stuff_Type extends CoEntityType {

    static SCHEMA_NAME = "lily";
    static TABLE_NAME = "policy";

    name = "net.bodz.lily.schema.account.Policy"
    icon = "fa-tag"
    description = "Security Policy"

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

    static validators = new PolicyValidators();

    static declaredProperty: EntityPropertyMap = {
        id: primaryKey({ type: "integer", nullable: false, precision: 10, validator: this.validators.validateId }),
        name: property({ type: "string", precision: 30, 
            description: "The policy name (unique)", 
            validator: this.validators.validateName }),
        controlClass: property({ type: "string", nullable: false, precision: 80, 
            description: "The control class", 
            validator: this.validators.validateControlClass }),
        methodName: property({ type: "string", precision: 80, 
            description: "The method name", 
            validator: this.validators.validateMethodName }),
        allowBits: property({ type: "integer", nullable: false, precision: 10, 
            description: "allow", 
            validator: this.validators.validateAllowBits }),
        denyBits: property({ type: "integer", nullable: false, precision: 10, 
            description: "deny", 
            validator: this.validators.validateDenyBits }),
    }

    constructor() {
        super();
        this.declare(_Policy_stuff_Type.declaredProperty);
    }

}

export default _Policy_stuff_Type;
