
import type { EntityPropertyMap } from "@skeljs/dba/src/net/bodz/lily/entity";

import { * as validators } from "./PersonValidators";
import type { _TagDef_stuff_Type } from "./_TagDef_stuff_Type";

// Type Info

export class TagDefType extends _TagDef_stuff_Type {

    name = "net.bodz.lily.schema.meta.TagDef"
    icon = "fa-tag"
    label = "Tag"

    static declaredProperty: EntityPropertyMap = {
    }

    constructor() {
        super();
        this.declare(TagDefType.declaredProperty);
    }

}
