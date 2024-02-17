
import { property } from "@skeljs/dba/src/net/bodz/lily/entity";
import type { EntityPropertyMap } from "@skeljs/dba/src/net/bodz/lily/entity";

import { * as validators } from "./PersonValidators";
import type { _Person_stuff_Type } from "./_Person_stuff_Type";

// Type Info

export class PersonType extends _Person_stuff_Type {

    name = "net.bodz.lily.schema.contact.Person"
    icon = "fa-tag"

    static declaredProperty: EntityPropertyMap = {
        hello: property({ type: "string", validator: validators.validate_hello }),
    }

    constructor() {
        super();
        this.declare(PersonType.declaredProperty);
    }

}
