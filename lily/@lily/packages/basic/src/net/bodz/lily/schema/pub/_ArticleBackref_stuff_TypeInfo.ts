import type { integer, long } from "@skeljs/core/src/lang/type";
import { EntityPropertyMap, property } from "@skeljs/dba/src/net/bodz/lily/entity";

import BackrefRecordTypeInfo from "../../concrete/BackrefRecordTypeInfo";
import ExternalSiteTypeInfo from "../inet/ExternalSiteTypeInfo";
import ArticleTypeInfo from "./ArticleTypeInfo";
import _ArticleBackref_stuff_Validators from "./_ArticleBackref_stuff_Validators";

export class _ArticleBackref_stuff_TypeInfo extends BackrefRecordTypeInfo {

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

    validators = new _ArticleBackref_stuff_Validators(this);

    declaredProperty: EntityPropertyMap = {
        key: property({ type: "string", precision: 30, validator: this.validators.validateKey }),
        value: property({ type: "integer", nullable: false, precision: 10, validator: this.validators.validateValue }),

        article: property({ type: ArticleTypeInfo, nullable: false, validator: this.validators.validateArticle }),
        articleId: property({ type: "long", nullable: false, precision: 19 }),

        site: property({ type: ExternalSiteTypeInfo, nullable: false, validator: this.validators.validateSite }),
        siteId: property({ type: "integer", nullable: false, precision: 10 }),
    }

    constructor() {
        super();
        this.declare(this.declaredProperty);
    }

}

export default _ArticleBackref_stuff_TypeInfo;
