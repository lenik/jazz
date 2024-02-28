import { INT, LONG } from "@skeljs/core/src/lang/baseinfo";
import type { int } from "@skeljs/core/src/lang/basetype";
import EntityPropertyMap from "@skeljs/dba/src/net/bodz/lily/entity/EntityPropertyMap";
import { primaryKey, property } from "@skeljs/dba/src/net/bodz/lily/entity/EntityType";

import CoEntityTypeInfo from "../../concrete/CoEntityTypeInfo";
import Article from "./Article";
import ArticleTagType from "./ArticleTagType";
import _ArticleTag_stuff_Validators from "./_ArticleTag_stuff_Validators";

export class _ArticleTag_stuff_TypeInfo extends CoEntityTypeInfo {

    static SCHEMA_NAME = "lily";
    static TABLE_NAME = "article_tag";

    get name() { return "net.bodz.lily.schema.pub.ArticleTag"; }
    get icon() { return "fa-tag"; }

    static FIELD_ID = "id";
    static FIELD_ARTICLE_ID = "article";
    static FIELD_TAG_ID = "tag";

    static N_ID = 10;
    static N_ARTICLE_ID = 19;
    static N_TAG_ID = 10;

    validators = new _ArticleTag_stuff_Validators(this);

    declaredProperty: EntityPropertyMap = {
        id: primaryKey({ type: INT, nullable: false, precision: 10, validator: this.validators.validateId }),

        tag: property({ type: ArticleTagType.TYPE, nullable: false, validator: this.validators.validateTag }),
        tagId: property({ type: INT, nullable: false, precision: 10 }),

        article: property({ type: Article.TYPE, nullable: false, validator: this.validators.validateArticle }),
        articleId: property({ type: LONG, nullable: false, precision: 19 }),
    }

    constructor() {
        super();
        this.declare(this.declaredProperty);
    }

}

export default _ArticleTag_stuff_TypeInfo;
