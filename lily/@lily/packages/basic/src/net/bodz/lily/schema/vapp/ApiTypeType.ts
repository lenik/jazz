
import type { EntityPropertyMap } from "@skeljs/dba/src/net/bodz/lily/entity";

import { * as validators } from "./PersonValidators";
import type { _ApiType_stuff_Type } from "./_ApiType_stuff_Type";

// Type Info

export class ApiTypeType extends _ApiType_stuff_Type {

    name = "net.bodz.lily.schema.vapp.ApiType"
    icon = "fa-tag"

    static declaredProperty: EntityPropertyMap = {
    }

    constructor() {
        super();
        this.declare(ApiTypeType.declaredProperty);
    }

}
