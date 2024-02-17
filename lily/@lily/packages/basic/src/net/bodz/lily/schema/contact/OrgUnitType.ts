
import { property } from "@skeljs/dba/src/net/bodz/lily/entity";
import type { EntityPropertyMap } from "@skeljs/dba/src/net/bodz/lily/entity";

import { * as validators } from "./PersonValidators";
import type { _OrgUnit_stuff_Type } from "./_OrgUnit_stuff_Type";

// Type Info

export class OrgUnitType extends _OrgUnit_stuff_Type {

    name = "net.bodz.lily.schema.contact.OrgUnit"
    icon = "fa-tag"

    static declaredProperty: EntityPropertyMap = {
        contact: property({ type: "net.bodz.lily.schema.contact.Contact", validator: validators.validate_contact }),
        staff: property({ type: "java.util.List", validator: validators.validate_staff }),
    }

    constructor() {
        super();
        this.declare(OrgUnitType.declaredProperty);
    }

}
