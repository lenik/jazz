
import type { EntityPropertyMap } from "@skeljs/dba/src/net/bodz/lily/entity";

import { * as validators } from "./PersonValidators";
import type { _FormParameter_stuff_Type } from "./_FormParameter_stuff_Type";

// Type Info

export class FormParameterType extends _FormParameter_stuff_Type {

    name = "net.bodz.lily.schema.meta.FormParameter"
    icon = "fa-tag"

    static declaredProperty: EntityPropertyMap = {
    }

    constructor() {
        super();
        this.declare(FormParameterType.declaredProperty);
    }

}
