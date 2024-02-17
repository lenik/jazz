
import type { EntityPropertyMap } from "@skeljs/dba/src/net/bodz/lily/entity";

import { * as validators } from "./PersonValidators";
import type { _FormDef_stuff_Type } from "./_FormDef_stuff_Type";

// Type Info

export class FormDefType extends _FormDef_stuff_Type {

    name = "net.bodz.lily.schema.meta.FormDef"
    icon = "fa-tag"

    static declaredProperty: EntityPropertyMap = {
    }

    constructor() {
        super();
        this.declare(FormDefType.declaredProperty);
    }

}
