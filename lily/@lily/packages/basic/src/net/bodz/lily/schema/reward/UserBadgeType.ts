
import type { EntityPropertyMap } from "@skeljs/dba/src/net/bodz/lily/entity";

import { * as validators } from "./PersonValidators";
import type { _UserBadge_stuff_Type } from "./_UserBadge_stuff_Type";

// Type Info

export class UserBadgeType extends _UserBadge_stuff_Type {

    name = "net.bodz.lily.schema.reward.UserBadge"
    icon = "fa-tag"

    static declaredProperty: EntityPropertyMap = {
    }

    constructor() {
        super();
        this.declare(UserBadgeType.declaredProperty);
    }

}
