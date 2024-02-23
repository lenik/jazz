import type { integer, long } from "@skeljs/core/src/lang/type";
import BackrefRecordType from "@skeljs/dba/src/net/bodz/lily/concrete/BackrefRecordType";
import { EntityPropertyMap, property } from "@skeljs/dba/src/net/bodz/lily/entity";

import { ExternalSite } from "../inet/ExternalSite";
import { Article } from "./Article";
import ArticleBackrefValidators from "./ArticleBackrefValidators";

export class _ArticleBackref_stuff_Type extends BackrefRecordType {

    static SCHEMA_NAME = "lily";
    static TABLE_NAME = "article_backref";

    name = "net.bodz.lily.schema.pub.ArticleBackref"
    icon = "fa-tag"

    static FIELD_ARTICLE_ID = "article";
    static FIELD_SITE_ID = "site";
    static FIELD_KEY = "key";
    static FIELD_VALUE = "value";

    static N_ARTICLE_ID = 19;
    static N_SITE_ID = 10;
    static N_KEY = 30;
    static N_VALUE = 10;

    static validators = new ArticleBackrefValidators();

    static declaredProperty: EntityPropertyMap = {
        key: property({ type: "string", precision: 30, validator: this.validators.validateKey }),
        value: property({ type: "integer", nullable: false, precision: 10, validator: this.validators.validateValue }),

        article: property({ type: Article.TYPE, nullable: false, validator: this.validators.validateArticle }),
        articleId: property({ type: "long", nullable: false, precision: 19 }),

        site: property({ type: ExternalSite.TYPE, nullable: false, validator: this.validators.validateSite }),
        siteId: property({ type: "integer", nullable: false, precision: 10 }),
    }

    constructor() {
        super();
        this.declare(_ArticleBackref_stuff_Type.declaredProperty);
    }

}

export default _ArticleBackref_stuff_Type;
