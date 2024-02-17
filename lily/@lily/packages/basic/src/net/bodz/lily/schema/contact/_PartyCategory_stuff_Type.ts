
import type { CoCategoryType } from "@skeljs/dba/src/net/bodz/lily/concrete/CoCategoryType";
import { property } from "@skeljs/dba/src/net/bodz/lily/entity";
import type { EntityPropertyMap } from "@skeljs/dba/src/net/bodz/lily/entity";

import type { TypeParamType } from "../../meta/TypeParamType";
import { * as validators } from "./PersonValidators";

// Type Info

export class _PartyCategory_stuff_Type extends CoCategoryType {

    static const SCHEMA_NAME = "lily";
    static const TABLE_NAME = "partycat";

    name = "net.bodz.lily.schema.contact.PartyCategory"
    icon = "fa-tag"

    static const FIELD_NAME = "name";

    static const N_NAME = 30;

    static declaredProperty: EntityPropertyMap = {
        name: property({ type: "string", precision: 30, validator: validators.validate_name }),
    }

    constructor() {
        super();
        this.declare(_PartyCategory_stuff_Type.declaredProperty);
    }

}
