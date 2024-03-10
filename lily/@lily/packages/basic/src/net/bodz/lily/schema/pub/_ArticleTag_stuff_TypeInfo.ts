import { INT, LONG } from "@skeljs/core/src/lang/baseinfo";
import type { int } from "@skeljs/core/src/lang/basetype";
import { primaryKey, property } from "@skeljs/dba/src/net/bodz/lily/entity/EntityType";

import CoEntityTypeInfo from "../../concrete/CoEntityTypeInfo";
import Article from "./Article";
import ArticleTagType from "./ArticleTagType";
import _ArticleTag_stuff_Validators from "./_ArticleTag_stuff_Validators";

export class _ArticleTag_stuff_TypeInfo extends CoEntityTypeInfo {

    static SCHEMA_NAME = "lily";
    static TABLE_NAME = "article_tag";

    static readonly FIELD_ID = "id";
    static readonly FIELD_ARTICLE_ID = "article";
    static readonly FIELD_TAG_ID = "tag";

    static readonly N_ID = 10;
    static readonly N_ARTICLE_ID = 19;
    static readonly N_TAG_ID = 10;

    readonly validators = new _ArticleTag_stuff_Validators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.lily.schema.pub.ArticleTag"; }
    get icon() { return "fa-tag"; }

    override preamble() {
        super.preamble();
        this.declare({
            id: primaryKey({ type: INT, nullable: false, precision: 10, validator: this.validators.validateId }),

            tag: property({ type: ArticleTagType.TYPE, nullable: false, validator: this.validators.validateTag }),
            tagId: property({ type: INT, nullable: false, precision: 10 }),

            article: property({ type: Article.TYPE, nullable: false, validator: this.validators.validateArticle }),
            articleId: property({ type: LONG, nullable: false, precision: 19 }),
        });
    }

    static readonly INSTANCE = new _ArticleTag_stuff_TypeInfo();

}

export default _ArticleTag_stuff_TypeInfo;
