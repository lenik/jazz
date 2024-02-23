import type { long } from "@skeljs/core/src/lang/type";
import FavRecordType from "@skeljs/dba/src/net/bodz/lily/concrete/FavRecordType";
import { EntityPropertyMap, property } from "@skeljs/dba/src/net/bodz/lily/entity";

import { Article } from "./Article";
import ArticleFavValidators from "./ArticleFavValidators";

export class _ArticleFav_stuff_Type extends FavRecordType {

    static SCHEMA_NAME = "lily";
    static TABLE_NAME = "article_fav";

    name = "net.bodz.lily.schema.pub.ArticleFav"
    icon = "fa-tag"

    static FIELD_ARTICLE_ID = "article";

    static N_ARTICLE_ID = 19;

    static validators = new ArticleFavValidators();

    static declaredProperty: EntityPropertyMap = {

        article: property({ type: Article.TYPE, nullable: false, validator: this.validators.validateArticle }),
        articleId: property({ type: "long", nullable: false, precision: 19 }),
    }

    constructor() {
        super();
        this.declare(_ArticleFav_stuff_Type.declaredProperty);
    }

}

export default _ArticleFav_stuff_Type;
