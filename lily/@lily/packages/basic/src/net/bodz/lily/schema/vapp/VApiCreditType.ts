
import type { EntityPropertyMap } from "@skeljs/dba/src/net/bodz/lily/entity";

import { * as validators } from "./PersonValidators";
import type { _VApiCredit_stuff_Type } from "./_VApiCredit_stuff_Type";

// Type Info

export class VApiCreditType extends _VApiCredit_stuff_Type {

    name = "net.bodz.lily.schema.vapp.VApiCredit"
    icon = "fa-tag"

    static declaredProperty: EntityPropertyMap = {
    }

    constructor() {
        super();
        this.declare(VApiCreditType.declaredProperty);
    }

}
