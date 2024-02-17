
import type { FavRecordType } from "@skeljs/dba/src/net/bodz/lily/concrete/FavRecordType";
import { property } from "@skeljs/dba/src/net/bodz/lily/entity";
import type { EntityPropertyMap } from "@skeljs/dba/src/net/bodz/lily/entity";

import { * as validators } from "./PersonValidators";

// Type Info

export class _ArticleFav_stuff_Type extends FavRecordType {

    static const SCHEMA_NAME = "lily";
    static const TABLE_NAME = "article_fav";

    name = "net.bodz.lily.schema.pub.ArticleFav"
    icon = "fa-tag"

    static const FIELD_ARTICLE_ID = "article";

    static const N_ARTICLE_ID = 19;

    static declaredProperty: EntityPropertyMap = {

        article: property({ type: "net.bodz.lily.schema.pub.Article", nullable: false, validator: validators.validate_article }),
        articleId: property({ type: "long", nullable: false, precision: 19, validator: validators.validate_articleId }),
    }

    constructor() {
        super();
        this.declare(_ArticleFav_stuff_Type.declaredProperty);
    }

}
