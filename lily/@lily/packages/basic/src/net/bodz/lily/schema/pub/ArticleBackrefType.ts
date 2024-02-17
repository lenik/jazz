
import type { EntityPropertyMap } from "@skeljs/dba/src/net/bodz/lily/entity";

import { * as validators } from "./PersonValidators";
import type { _ArticleBackref_stuff_Type } from "./_ArticleBackref_stuff_Type";

// Type Info

export class ArticleBackrefType extends _ArticleBackref_stuff_Type {

    name = "net.bodz.lily.schema.pub.ArticleBackref"
    icon = "fa-tag"

    static declaredProperty: EntityPropertyMap = {
    }

    constructor() {
        super();
        this.declare(ArticleBackrefType.declaredProperty);
    }

}
