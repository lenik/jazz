import { EntityPropertyMap } from "@skeljs/dba/src/net/bodz/lily/entity";

import ArticleTalkValidators from "./ArticleTalkValidators";
import _ArticleTalk_stuff_TypeInfo from "./_ArticleTalk_stuff_TypeInfo";

// Type Info

export class ArticleTalkTypeInfo extends _ArticleTalk_stuff_TypeInfo {

    name = "net.bodz.lily.schema.pub.ArticleTalk"
    icon = "fa-tag"

    static validators = new ArticleTalkValidators();

    static declaredProperty: EntityPropertyMap = {
    }

    constructor() {
        super();
        this.declare(ArticleTalkTypeInfo.declaredProperty);
    }

}

export default ArticleTalk;
