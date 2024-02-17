
import type { EntityPropertyMap } from "@skeljs/dba/src/net/bodz/lily/entity";

import { * as validators } from "./PersonValidators";
import type { _Post_stuff_Type } from "./_Post_stuff_Type";

// Type Info

export class PostType extends _Post_stuff_Type {

    name = "net.bodz.lily.schema.pub.Post"
    icon = "fa-tag"

    static declaredProperty: EntityPropertyMap = {
    }

    constructor() {
        super();
        this.declare(PostType.declaredProperty);
    }

}
