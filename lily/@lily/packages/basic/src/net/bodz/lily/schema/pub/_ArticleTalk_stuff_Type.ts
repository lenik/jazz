import type { long } from "@skeljs/core/src/lang/type";
import CoTalkType from "@skeljs/dba/src/net/bodz/lily/concrete/CoTalkType";
import { EntityPropertyMap, property } from "@skeljs/dba/src/net/bodz/lily/entity";

import { TypeParamType } from "../../meta/TypeParamType";
import { Article } from "./Article";
import ArticleTalkValidators from "./ArticleTalkValidators";

export class _ArticleTalk_stuff_Type extends CoTalkType {

    static SCHEMA_NAME = "lily";
    static TABLE_NAME = "article_msg";

    name = "net.bodz.lily.schema.pub.ArticleTalk"
    icon = "fa-tag"

    static FIELD_FORM_ARGUMENTS = "formargs";
    static FIELD_ARTICLE_ID = "article";

    static N_FORM_ARGUMENTS = 2147483647;
    static N_ARTICLE_ID = 19;

    static validators = new ArticleTalkValidators();

    static declaredProperty: EntityPropertyMap = {
        formArguments: property({ type: "string", validator: this.validators.validateFormArguments }),

        article: property({ type: Article.TYPE, nullable: false, validator: this.validators.validateArticle }),
        articleId: property({ type: "long", nullable: false, precision: 19 }),
    }

    constructor() {
        super();
        this.declare(_ArticleTalk_stuff_Type.declaredProperty);
    }

}

export default _ArticleTalk_stuff_Type;
