import { LONG, STRING } from "skel01-core/src/lang/baseinfo";
import { property } from "skel01-dba/src/net/bodz/lily/entity/EntityType";

import CoTalkTypeInfo from "../../concrete/CoTalkTypeInfo";
import { Article_TYPE } from "./ArticleTypeInfo";
import _ArticleTalk_stuff_Validators from "./_ArticleTalk_stuff_Validators";

export class _ArticleTalk_stuff_TypeInfo extends CoTalkTypeInfo {

    static SCHEMA_NAME = "lily";
    static TABLE_NAME = "article_msg";

    static readonly FIELD_FORM_ARGUMENTS = "formargs";
    static readonly FIELD_ARTICLE_ID = "article";

    static readonly N_FORM_ARGUMENTS = 2147483647;
    static readonly N_ARTICLE_ID = 19;

    readonly validators = new _ArticleTalk_stuff_Validators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.lily.schema.pub.ArticleTalk"; }
    get icon() { return "fa-tag"; }

    override preamble() {
        super.preamble();
        this.declare({
            formArguments: property({ type: STRING, validator: this.validators.validateFormArguments }),

            article: property({ type: Article_TYPE, nullable: false, validator: this.validators.validateArticle }),
            articleId: property({ type: LONG, nullable: false, precision: 19 }),
        });
    }

}

export default _ArticleTalk_stuff_TypeInfo;

export const _ArticleTalk_stuff_TYPE = _ArticleTalk_stuff_TypeInfo.INSTANCE;
