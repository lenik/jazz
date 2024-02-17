
import type { EntityPropertyMap } from "@skeljs/dba/src/net/bodz/lily/entity";

import { * as validators } from "./PersonValidators";
import type { _Organization_stuff_Type } from "./_Organization_stuff_Type";

// Type Info

export class OrganizationType extends _Organization_stuff_Type {

    name = "net.bodz.lily.schema.contact.Organization"
    icon = "fa-tag"

    static declaredProperty: EntityPropertyMap = {
    }

    constructor() {
        super();
        this.declare(OrganizationType.declaredProperty);
    }

}
