
import type { EntityPropertyMap } from "@skeljs/dba/src/net/bodz/lily/entity";

import { * as validators } from "./PersonValidators";
import type { _PostParameterType_stuff_Type } from "./_PostParameterType_stuff_Type";

// Type Info

export class PostParameterTypeType extends _PostParameterType_stuff_Type {

    name = "net.bodz.lily.schema.pub.PostParameterType"
    icon = "fa-tag"

    static declaredProperty: EntityPropertyMap = {
    }

    constructor() {
        super();
        this.declare(PostParameterTypeType.declaredProperty);
    }

}
