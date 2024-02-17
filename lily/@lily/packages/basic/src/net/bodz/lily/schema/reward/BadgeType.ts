
import type { EntityPropertyMap } from "@skeljs/dba/src/net/bodz/lily/entity";

import { * as validators } from "./PersonValidators";
import type { _Badge_stuff_Type } from "./_Badge_stuff_Type";

// Type Info

export class BadgeType extends _Badge_stuff_Type {

    name = "net.bodz.lily.schema.reward.Badge"
    icon = "fa-tag"

    static declaredProperty: EntityPropertyMap = {
    }

    constructor() {
        super();
        this.declare(BadgeType.declaredProperty);
    }

}
