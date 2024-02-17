
import type { EntityPropertyMap } from "@skeljs/dba/src/net/bodz/lily/entity";

import { * as validators } from "./PersonValidators";
import type { _PostBackref_stuff_Type } from "./_PostBackref_stuff_Type";

// Type Info

export class PostBackrefType extends _PostBackref_stuff_Type {

    name = "net.bodz.lily.schema.pub.PostBackref"
    icon = "fa-tag"

    static declaredProperty: EntityPropertyMap = {
    }

    constructor() {
        super();
        this.declare(PostBackrefType.declaredProperty);
    }

}
