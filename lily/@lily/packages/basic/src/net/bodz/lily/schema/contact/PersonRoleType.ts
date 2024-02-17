
import type { EntityPropertyMap } from "@skeljs/dba/src/net/bodz/lily/entity";

import { * as validators } from "./PersonValidators";
import type { _PersonRole_stuff_Type } from "./_PersonRole_stuff_Type";

// Type Info

export class PersonRoleType extends _PersonRole_stuff_Type {

    name = "net.bodz.lily.schema.contact.PersonRole"
    icon = "fa-tag"

    static declaredProperty: EntityPropertyMap = {
    }

    constructor() {
        super();
        this.declare(PersonRoleType.declaredProperty);
    }

}
