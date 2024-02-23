import { EntityPropertyMap } from "@skeljs/dba/src/net/bodz/lily/entity";

import PolicyValidators from "./PolicyValidators";
import _Policy_stuff_TypeInfo from "./_Policy_stuff_TypeInfo";

// Type Info

export class PolicyTypeInfo extends _Policy_stuff_TypeInfo {

    name = "net.bodz.lily.schema.account.Policy"
    icon = "fa-tag"
    description = "Security Policy"

    static validators = new PolicyValidators();

    static declaredProperty: EntityPropertyMap = {
    }

    constructor() {
        super();
        this.declare(PolicyTypeInfo.declaredProperty);
    }

}

export default Policy;
