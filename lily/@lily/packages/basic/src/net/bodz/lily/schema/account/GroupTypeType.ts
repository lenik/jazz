
import type { EntityPropertyMap } from "@skeljs/dba/src/net/bodz/lily/entity";

import { * as validators } from "./PersonValidators";
import type { _GroupType_stuff_Type } from "./_GroupType_stuff_Type";

// Type Info

export class GroupTypeType extends _GroupType_stuff_Type {

    name = "net.bodz.lily.schema.account.GroupType"
    icon = "fa-tag"
    description = "Group Type"

    static declaredProperty: EntityPropertyMap = {
    }

    constructor() {
        super();
        this.declare(GroupTypeType.declaredProperty);
    }

}
