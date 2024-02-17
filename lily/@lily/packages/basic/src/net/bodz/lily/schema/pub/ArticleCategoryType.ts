
import type { EntityPropertyMap } from "@skeljs/dba/src/net/bodz/lily/entity";

import { * as validators } from "./PersonValidators";
import type { _ArticleCategory_stuff_Type } from "./_ArticleCategory_stuff_Type";

// Type Info

export class ArticleCategoryType extends _ArticleCategory_stuff_Type {

    name = "net.bodz.lily.schema.pub.ArticleCategory"
    icon = "fa-tag"

    static declaredProperty: EntityPropertyMap = {
    }

    constructor() {
        super();
        this.declare(ArticleCategoryType.declaredProperty);
    }

}
