
import type { EntityPropertyMap } from "@skeljs/dba/src/net/bodz/lily/entity";

import { * as validators } from "./PersonValidators";
import type { _ArticleTagType_stuff_Type } from "./_ArticleTagType_stuff_Type";

// Type Info

export class ArticleTagTypeType extends _ArticleTagType_stuff_Type {

    name = "net.bodz.lily.schema.pub.ArticleTagType"
    icon = "fa-tag"

    static declaredProperty: EntityPropertyMap = {
    }

    constructor() {
        super();
        this.declare(ArticleTagTypeType.declaredProperty);
    }

}
