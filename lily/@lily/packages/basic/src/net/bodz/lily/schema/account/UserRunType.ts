
import { property } from "@skeljs/dba/src/net/bodz/lily/entity";
import type { EntityPropertyMap } from "@skeljs/dba/src/net/bodz/lily/entity";

import { * as validators } from "./PersonValidators";
import type { _UserRun_stuff_Type } from "./_UserRun_stuff_Type";

// Type Info

export class UserRunType extends _UserRun_stuff_Type {

    name = "net.bodz.lily.schema.account.UserRun"
    icon = "fa-tag"
    description = "User Activity Log"

    static declaredProperty: EntityPropertyMap = {
        activeTime: property({ type: "Moment", validator: validators.validate_activeTime }),
        stateText: property({ type: "string", validator: validators.validate_stateText }),
    }

    constructor() {
        super();
        this.declare(UserRunType.declaredProperty);
    }

}
