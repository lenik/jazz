
import type { CoTalkType } from "@skeljs/dba/src/net/bodz/lily/concrete/CoTalkType";
import { property } from "@skeljs/dba/src/net/bodz/lily/entity";
import type { EntityPropertyMap } from "@skeljs/dba/src/net/bodz/lily/entity";

import type { TypeParamType } from "../../meta/TypeParamType";
import { * as validators } from "./PersonValidators";

// Type Info

export class _ArticleTalk_stuff_Type extends CoTalkType {

    static const SCHEMA_NAME = "lily";
    static const TABLE_NAME = "article_msg";

    name = "net.bodz.lily.schema.pub.ArticleTalk"
    icon = "fa-tag"

    static const FIELD_FORM_ARGUMENTS = "formargs";
    static const FIELD_ARTICLE_ID = "article";

    static const N_FORM_ARGUMENTS = 2147483647;
    static const N_ARTICLE_ID = 19;

    static declaredProperty: EntityPropertyMap = {
        formArguments: property({ type: "string", validator: validators.validate_formArguments }),

        article: property({ type: "net.bodz.lily.schema.pub.Article", nullable: false, validator: validators.validate_article }),
        articleId: property({ type: "long", nullable: false, precision: 19, validator: validators.validate_articleId }),
    }

    constructor() {
        super();
        this.declare(_ArticleTalk_stuff_Type.declaredProperty);
    }

}
