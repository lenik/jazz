
import type { EntityPropertyMap } from "@skeljs/dba/src/net/bodz/lily/entity";

import { * as validators } from "./PersonValidators";
import type { _PostTagType_stuff_Type } from "./_PostTagType_stuff_Type";

// Type Info

export class PostTagTypeType extends _PostTagType_stuff_Type {

    name = "net.bodz.lily.schema.pub.PostTagType"
    icon = "fa-tag"

    static declaredProperty: EntityPropertyMap = {
    }

    constructor() {
        super();
        this.declare(PostTagTypeType.declaredProperty);
    }

}
