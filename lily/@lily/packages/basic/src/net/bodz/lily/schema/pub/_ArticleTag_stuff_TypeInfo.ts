import { INT, LONG } from "skel01-core/src/lang/baseinfo";
import type { int } from "skel01-core/src/lang/basetype";
import { property } from "skel01-dba/src/net/bodz/lily/entity/EntityType";

import IdEntityTypeInfo from "../../concrete/IdEntityTypeInfo";
import { ArticleTagType_TYPE } from "./ArticleTagTypeTypeInfo";
import { Article_TYPE } from "./ArticleTypeInfo";
import _ArticleTag_stuff_Validators from "./_ArticleTag_stuff_Validators";

export class _ArticleTag_stuff_TypeInfo extends IdEntityTypeInfo {

    static SCHEMA_NAME = "lily";
    static TABLE_NAME = "article_tag";

    static readonly FIELD_ARTICLE_ID = "article";
    static readonly FIELD_TAG_ID = "tag";

    static readonly N_ARTICLE_ID = 19;
    static readonly N_TAG_ID = 10;

    readonly validators = new _ArticleTag_stuff_Validators(this);

    constructor() {
        super(INT);
    }

    get name() { return "net.bodz.lily.schema.pub.ArticleTag"; }
    get icon() { return "fa-tag"; }

    override preamble() {
        super.preamble();
        this.declare({

            tag: property({ type: ArticleTagType_TYPE, nullable: false, validator: this.validators.validateTag }),
            tagId: property({ type: INT, nullable: false, precision: 10 }),

            article: property({ type: Article_TYPE, nullable: false, validator: this.validators.validateArticle }),
            articleId: property({ type: LONG, nullable: false, precision: 19 }),
        });
    }

    static readonly INSTANCE = new _ArticleTag_stuff_TypeInfo();

}

export default _ArticleTag_stuff_TypeInfo;

export const _ArticleTag_stuff_TYPE = _ArticleTag_stuff_TypeInfo.INSTANCE;
