
import type { CoEntityType } from "@skeljs/dba/src/net/bodz/lily/concrete/CoEntityType";
import { primaryKey, property } from "@skeljs/dba/src/net/bodz/lily/entity";
import type { EntityPropertyMap } from "@skeljs/dba/src/net/bodz/lily/entity";

import type { Integer } from "../../../../../java/lang/Integer";
import { * as validators } from "./PersonValidators";

// Type Info

export class _ArticleTag_stuff_Type extends CoEntityType {

    static const SCHEMA_NAME = "lily";
    static const TABLE_NAME = "article_tag";

    name = "net.bodz.lily.schema.pub.ArticleTag"
    icon = "fa-tag"

    static const FIELD_ID = "id";
    static const FIELD_ARTICLE_ID = "article";
    static const FIELD_TAG_ID = "tag";

    static const N_ID = 10;
    static const N_ARTICLE_ID = 19;
    static const N_TAG_ID = 10;

    static declaredProperty: EntityPropertyMap = {
        id: primaryKey({ type: "int", nullable: false, precision: 10, validator: validators.validate_id }),

        tag: property({ type: "net.bodz.lily.schema.pub.ArticleTagType", nullable: false, validator: validators.validate_tag }),
        tagId: property({ type: "int", nullable: false, precision: 10, validator: validators.validate_tagId }),

        article: property({ type: "net.bodz.lily.schema.pub.Article", nullable: false, validator: validators.validate_article }),
        articleId: property({ type: "long", nullable: false, precision: 19, validator: validators.validate_articleId }),
    }

    constructor() {
        super();
        this.declare(_ArticleTag_stuff_Type.declaredProperty);
    }

}
