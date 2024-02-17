
import type { EntityPropertyMap } from "@skeljs/dba/src/net/bodz/lily/entity";

import { * as validators } from "./PersonValidators";
import type { _ArticleFav_stuff_Type } from "./_ArticleFav_stuff_Type";

// Type Info

export class ArticleFavType extends _ArticleFav_stuff_Type {

    name = "net.bodz.lily.schema.pub.ArticleFav"
    icon = "fa-tag"

    static declaredProperty: EntityPropertyMap = {
    }

    constructor() {
        super();
        this.declare(ArticleFavType.declaredProperty);
    }

}
