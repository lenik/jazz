import { LONG, STRING } from "@skeljs/core/src/lang/baseinfo";
import { property } from "@skeljs/dba/src/net/bodz/lily/entity/EntityType";

import CoTalkTypeInfo from "../../concrete/CoTalkTypeInfo";
import Article from "./Article";
import _ArticleTalk_stuff_Validators from "./_ArticleTalk_stuff_Validators";

export class _ArticleTalk_stuff_TypeInfo extends CoTalkTypeInfo {

    static SCHEMA_NAME = "lily";
    static TABLE_NAME = "article_msg";

    get name() { return "net.bodz.lily.schema.pub.ArticleTalk"; }
    get icon() { return "fa-tag"; }

    static FIELD_FORM_ARGUMENTS = "formargs";
    static FIELD_ARTICLE_ID = "article";

    static N_FORM_ARGUMENTS = 2147483647;
    static N_ARTICLE_ID = 19;

    validators = new _ArticleTalk_stuff_Validators(this);

    override preamble() {
        super.preamble();
        this.declare({
            formArguments: property({ type: STRING, validator: this.validators.validateFormArguments }),

            article: property({ type: Article.TYPE, nullable: false, validator: this.validators.validateArticle }),
            articleId: property({ type: LONG, nullable: false, precision: 19 }),
        });
    }

    constructor() {
        super();
    }

}

export default _ArticleTalk_stuff_TypeInfo;
