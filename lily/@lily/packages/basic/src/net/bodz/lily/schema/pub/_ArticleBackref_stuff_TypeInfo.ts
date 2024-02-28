import { INT, LONG, STRING } from "@skeljs/core/src/lang/baseinfo";
import EntityPropertyMap from "@skeljs/dba/src/net/bodz/lily/entity/EntityPropertyMap";
import { property } from "@skeljs/dba/src/net/bodz/lily/entity/EntityType";

import BackrefRecordTypeInfo from "../../concrete/BackrefRecordTypeInfo";
import ExternalSite from "../inet/ExternalSite";
import Article from "./Article";
import _ArticleBackref_stuff_Validators from "./_ArticleBackref_stuff_Validators";

export class _ArticleBackref_stuff_TypeInfo extends BackrefRecordTypeInfo {

    static SCHEMA_NAME = "lily";
    static TABLE_NAME = "article_backref";

    get name() { return "net.bodz.lily.schema.pub.ArticleBackref"; }
    get icon() { return "fa-tag"; }

    static FIELD_ARTICLE_ID = "article";
    static FIELD_SITE_ID = "site";
    static FIELD_KEY = "key";
    static FIELD_VALUE = "value";

    static N_ARTICLE_ID = 19;
    static N_SITE_ID = 10;
    static N_KEY = 30;
    static N_VALUE = 10;

    validators = new _ArticleBackref_stuff_Validators(this);

    declaredProperty: EntityPropertyMap = {
        key: property({ type: STRING, precision: 30, validator: this.validators.validateKey }),
        value: property({ type: INT, nullable: false, precision: 10, validator: this.validators.validateValue }),

        article: property({ type: Article.TYPE, nullable: false, validator: this.validators.validateArticle }),
        articleId: property({ type: LONG, nullable: false, precision: 19 }),

        site: property({ type: ExternalSite.TYPE, nullable: false, validator: this.validators.validateSite }),
        siteId: property({ type: INT, nullable: false, precision: 10 }),
    }

    constructor() {
        super();
        this.declare(this.declaredProperty);
    }

}

export default _ArticleBackref_stuff_TypeInfo;
