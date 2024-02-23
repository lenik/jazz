
import type { EntityPropertyMap } from "@skeljs/dba/src/net/bodz/lily/entity";

import { * as validators } from "./PersonValidators";
import type { _UserSecret_stuff_Type } from "./_UserSecret_stuff_Type";

// Type Info

export class UserSecretType extends _UserSecret_stuff_Type {

    name = "net.bodz.lily.schema.account.UserSecret"
    icon = "fa-tag"
    description = "User Secret"

    static declaredProperty: EntityPropertyMap = {
    }

    constructor() {
        super();
        this.declare(UserSecretType.declaredProperty);
    }

}