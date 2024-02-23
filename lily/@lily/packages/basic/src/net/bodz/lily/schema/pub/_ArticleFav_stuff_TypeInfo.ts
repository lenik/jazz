import type { long } from "@skeljs/core/src/lang/type";
import FavRecordTypeInfo from "@skeljs/dba/src/net/bodz/lily/concrete/FavRecordTypeInfo";
import { EntityPropertyMap, property } from "@skeljs/dba/src/net/bodz/lily/entity";

import _ArticleFav_stuff_Validators from "./_ArticleFav_stuff_Validators";

export class _ArticleFav_stuff_TypeInfo extends FavRecordTypeInfo {

    static SCHEMA_NAME = "lily";
    static TABLE_NAME = "article_fav";

    name = "net.bodz.lily.schema.pub.ArticleFav"
    icon = "fa-tag"

    static FIELD_ARTICLE_ID = "article";

    static N_ARTICLE_ID = 19;

    static validators = new _ArticleFav_stuff_Validators();

    static declaredProperty: EntityPropertyMap = {

        article: property({ type: net.bodz.lily.schema.pub.ArticleTypeInfo, nullable: false, validator: this.validators.validateArticle }),
        articleId: property({ type: "long", nullable: false, precision: 19 }),
    }

    constructor() {
        super();
        this.declare(_ArticleFav_stuff_TypeInfo.declaredProperty);
    }

}

export default _ArticleFav_stuff_TypeInfo;
