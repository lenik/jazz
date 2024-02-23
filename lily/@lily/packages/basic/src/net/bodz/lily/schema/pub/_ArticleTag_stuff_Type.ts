import type { integer, long } from "@skeljs/core/src/lang/type";
import CoEntityType from "@skeljs/dba/src/net/bodz/lily/concrete/CoEntityType";
import { EntityPropertyMap, primaryKey, property } from "@skeljs/dba/src/net/bodz/lily/entity";

import { Article } from "./Article";
import { ArticleTagType } from "./ArticleTagType";
import ArticleTagValidators from "./ArticleTagValidators";

export class _ArticleTag_stuff_Type extends CoEntityType {

    static SCHEMA_NAME = "lily";
    static TABLE_NAME = "article_tag";

    name = "net.bodz.lily.schema.pub.ArticleTag"
    icon = "fa-tag"

    static FIELD_ID = "id";
    static FIELD_ARTICLE_ID = "article";
    static FIELD_TAG_ID = "tag";

    static N_ID = 10;
    static N_ARTICLE_ID = 19;
    static N_TAG_ID = 10;

    static validators = new ArticleTagValidators();

    static declaredProperty: EntityPropertyMap = {
        id: primaryKey({ type: "integer", nullable: false, precision: 10, validator: this.validators.validateId }),

        tag: property({ type: ArticleTagType.TYPE, nullable: false, validator: this.validators.validateTag }),
        tagId: property({ type: "integer", nullable: false, precision: 10 }),

        article: property({ type: Article.TYPE, nullable: false, validator: this.validators.validateArticle }),
        articleId: property({ type: "long", nullable: false, precision: 19 }),
    }

    constructor() {
        super();
        this.declare(_ArticleTag_stuff_Type.declaredProperty);
    }

}

export default _ArticleTag_stuff_Type;
