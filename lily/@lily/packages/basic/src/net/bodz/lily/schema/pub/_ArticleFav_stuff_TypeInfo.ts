import type { long } from "@skeljs/core/src/lang/basetype";
import { EntityPropertyMap, property } from "@skeljs/dba/src/net/bodz/lily/entity/EntityType";

import FavRecordTypeInfo from "../../concrete/FavRecordTypeInfo";
import ArticleTypeInfo from "./ArticleTypeInfo";
import _ArticleFav_stuff_Validators from "./_ArticleFav_stuff_Validators";

export class _ArticleFav_stuff_TypeInfo extends FavRecordTypeInfo {

    static SCHEMA_NAME = "lily";
    static TABLE_NAME = "article_fav";

    get name() { return "net.bodz.lily.schema.pub.ArticleFav"; }
    get icon() { return "fa-tag"; }

    static FIELD_ARTICLE_ID = "article";

    static N_ARTICLE_ID = 19;

    validators = new _ArticleFav_stuff_Validators(this);

    declaredProperty: EntityPropertyMap = {

        article: property({ type: ArticleTypeInfo, nullable: false, validator: this.validators.validateArticle }),
        articleId: property({ type: "long", nullable: false, precision: 19 }),
    }

    constructor() {
        super();
        this.declare(this.declaredProperty);
    }

}

export default _ArticleFav_stuff_TypeInfo;
