
import type { EntityPropertyMap } from "@skeljs/dba/src/net/bodz/lily/entity";

import { * as validators } from "./PersonValidators";
import type { _Article_stuff_Type } from "./_Article_stuff_Type";

// Type Info

export class ArticleType extends _Article_stuff_Type {

    name = "net.bodz.lily.schema.pub.Article"
    icon = "fa-tag"

    static declaredProperty: EntityPropertyMap = {
    }

    constructor() {
        super();
        this.declare(ArticleType.declaredProperty);
    }

}
