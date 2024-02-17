
import type { EntityPropertyMap } from "@skeljs/dba/src/net/bodz/lily/entity";

import { * as validators } from "./PersonValidators";
import type { _Policy_stuff_Type } from "./_Policy_stuff_Type";

// Type Info

export class PolicyType extends _Policy_stuff_Type {

    name = "net.bodz.lily.schema.account.Policy"
    icon = "fa-tag"
    description = "Security Policy"

    static declaredProperty: EntityPropertyMap = {
    }

    constructor() {
        super();
        this.declare(PolicyType.declaredProperty);
    }

}
