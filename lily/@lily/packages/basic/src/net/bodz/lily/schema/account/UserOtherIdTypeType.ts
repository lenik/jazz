
import type { EntityPropertyMap } from "@skeljs/dba/src/net/bodz/lily/entity";

import { * as validators } from "./PersonValidators";
import type { _UserOtherIdType_stuff_Type } from "./_UserOtherIdType_stuff_Type";

// Type Info

export class UserOtherIdTypeType extends _UserOtherIdType_stuff_Type {

    name = "net.bodz.lily.schema.account.UserOtherIdType"
    icon = "fa-tag"
    description = "Type of Open ID"

    static declaredProperty: EntityPropertyMap = {
    }

    constructor() {
        super();
        this.declare(UserOtherIdTypeType.declaredProperty);
    }

}
