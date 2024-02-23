import { EntityPropertyMap } from "@skeljs/dba/src/net/bodz/lily/entity";

import PolicyValidators from "./PolicyValidators";
import _Policy_stuff_Type from "./_Policy_stuff_Type";

// Type Info

export class PolicyType extends _Policy_stuff_Type {

    name = "net.bodz.lily.schema.account.Policy"
    icon = "fa-tag"
    description = "Security Policy"

    static validators = new PolicyValidators();

    static declaredProperty: EntityPropertyMap = {
    }

    constructor() {
        super();
        this.declare(PolicyType.declaredProperty);
    }

}

export default Policy;
