
import type { CoEntityType } from "@skeljs/dba/src/net/bodz/lily/concrete/CoEntityType";
import { primaryKey, property } from "@skeljs/dba/src/net/bodz/lily/entity";
import type { EntityPropertyMap } from "@skeljs/dba/src/net/bodz/lily/entity";

import type { Integer } from "../../../../../java/lang/Integer";
import { * as validators } from "./PersonValidators";

// Type Info

export class _Policy_stuff_Type extends CoEntityType {

    static const SCHEMA_NAME = "lily";
    static const TABLE_NAME = "policy";

    name = "net.bodz.lily.schema.account.Policy"
    icon = "fa-tag"
    description = "Security Policy"

    static const FIELD_ID = "id";
    static const FIELD_NAME = "name";
    static const FIELD_CONTROL_CLASS = "cclass";
    static const FIELD_METHOD_NAME = "method";
    static const FIELD_ALLOW_BITS = "allow";
    static const FIELD_DENY_BITS = "deny";

    static const N_ID = 10;
    static const N_NAME = 30;
    static const N_CONTROL_CLASS = 80;
    static const N_METHOD_NAME = 80;
    static const N_ALLOW_BITS = 10;
    static const N_DENY_BITS = 10;

    static declaredProperty: EntityPropertyMap = {
        id: primaryKey({ type: "int", nullable: false, precision: 10, validator: validators.validate_id }),
        name: property({ type: "string", precision: 30, 
            description: "The policy name (unique)", 
            validator: validators.validate_name }),
        controlClass: property({ type: "string", nullable: false, precision: 80, 
            description: "The control class", 
            validator: validators.validate_controlClass }),
        methodName: property({ type: "string", precision: 80, 
            description: "The method name", 
            validator: validators.validate_methodName }),
        allowBits: property({ type: "int", nullable: false, precision: 10, 
            description: "allow", 
            validator: validators.validate_allowBits }),
        denyBits: property({ type: "int", nullable: false, precision: 10, 
            description: "deny", 
            validator: validators.validate_denyBits }),
    }

    constructor() {
        super();
        this.declare(_Policy_stuff_Type.declaredProperty);
    }

}
