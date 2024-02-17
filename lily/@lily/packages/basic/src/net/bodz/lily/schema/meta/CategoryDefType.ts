
import type { EntityPropertyMap } from "@skeljs/dba/src/net/bodz/lily/entity";

import { * as validators } from "./PersonValidators";
import type { _CategoryDef_stuff_Type } from "./_CategoryDef_stuff_Type";

// Type Info

export class CategoryDefType extends _CategoryDef_stuff_Type {

    name = "net.bodz.lily.schema.meta.CategoryDef"
    icon = "fa-tag"
    label = "Category"

    static declaredProperty: EntityPropertyMap = {
    }

    constructor() {
        super();
        this.declare(CategoryDefType.declaredProperty);
    }

}
