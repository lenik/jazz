
import type { EntityPropertyMap } from "@skeljs/dba/src/net/bodz/lily/entity";

import { * as validators } from "./PersonValidators";
import type { _PersonTagType_stuff_Type } from "./_PersonTagType_stuff_Type";

// Type Info

export class PersonTagTypeType extends _PersonTagType_stuff_Type {

    name = "net.bodz.lily.schema.contact.PersonTagType"
    icon = "fa-tag"

    static declaredProperty: EntityPropertyMap = {
    }

    constructor() {
        super();
        this.declare(PersonTagTypeType.declaredProperty);
    }

}
