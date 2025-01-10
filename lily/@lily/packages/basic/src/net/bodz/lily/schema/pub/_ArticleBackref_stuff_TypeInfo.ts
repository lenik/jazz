import { INT, LONG, STRING } from "skel01-core/src/lang/baseinfo";
import { property } from "skel01-dba/src/net/bodz/lily/entity/EntityType";

import BackrefRecordTypeInfo from "../../concrete/BackrefRecordTypeInfo";
import { ExternalSite_TYPE } from "../inet/ExternalSiteTypeInfo";
import { Article_TYPE } from "./ArticleTypeInfo";
import _ArticleBackref_stuff_Validators from "./_ArticleBackref_stuff_Validators";

export class _ArticleBackref_stuff_TypeInfo extends BackrefRecordTypeInfo {

    static SCHEMA_NAME = "lily";
    static TABLE_NAME = "article_backref";

    static readonly FIELD_ARTICLE_ID = "article";
    static readonly FIELD_SITE_ID = "site";
    static readonly FIELD_KEY = "key";
    static readonly FIELD_VALUE = "value";

    static readonly N_ARTICLE_ID = 19;
    static readonly N_SITE_ID = 10;
    static readonly N_KEY = 30;
    static readonly N_VALUE = 10;

    readonly validators = new _ArticleBackref_stuff_Validators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.lily.schema.pub.ArticleBackref"; }
    get icon() { return "fa-tag"; }

    override preamble() {
        super.preamble();
        this.declare({
            key: property({ type: STRING, precision: 30, validator: this.validators.validateKey }),
            value: property({ type: INT, nullable: false, precision: 10, validator: this.validators.validateValue }),

            article: property({ type: Article_TYPE, nullable: false, validator: this.validators.validateArticle }),
            articleId: property({ type: LONG, nullable: false, precision: 19 }),

            site: property({ type: ExternalSite_TYPE, nullable: false, validator: this.validators.validateSite }),
            siteId: property({ type: INT, nullable: false, precision: 10 }),
        });
    }

    static readonly INSTANCE = new _ArticleBackref_stuff_TypeInfo();

}

export default _ArticleBackref_stuff_TypeInfo;

export const _ArticleBackref_stuff_TYPE = _ArticleBackref_stuff_TypeInfo.INSTANCE;
