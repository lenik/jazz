
import type { EntityPropertyMap } from "@skeljs/dba/src/net/bodz/lily/entity";

import { * as validators } from "./PersonValidators";
import type { _PostFav_stuff_Type } from "./_PostFav_stuff_Type";

// Type Info

export class PostFavType extends _PostFav_stuff_Type {

    name = "net.bodz.lily.schema.pub.PostFav"
    icon = "fa-tag"

    static declaredProperty: EntityPropertyMap = {
    }

    constructor() {
        super();
        this.declare(PostFavType.declaredProperty);
    }

}
