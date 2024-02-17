
import type { EntityPropertyMap } from "@skeljs/dba/src/net/bodz/lily/entity";

import { * as validators } from "./PersonValidators";
import type { _ZoneCategory_stuff_Type } from "./_ZoneCategory_stuff_Type";

// Type Info

export class ZoneCategoryType extends _ZoneCategory_stuff_Type {

    name = "net.bodz.lily.schema.geo.ZoneCategory"
    icon = "fa-tag"

    static declaredProperty: EntityPropertyMap = {
    }

    constructor() {
        super();
        this.declare(ZoneCategoryType.declaredProperty);
    }

}
