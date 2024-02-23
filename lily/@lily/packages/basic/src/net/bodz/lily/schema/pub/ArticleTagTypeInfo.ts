import { EntityPropertyMap } from "@skeljs/dba/src/net/bodz/lily/entity";

import ArticleTagValidators from "./ArticleTagValidators";
import _ArticleTag_stuff_TypeInfo from "./_ArticleTag_stuff_TypeInfo";

// Type Info

export class ArticleTagTypeInfo extends _ArticleTag_stuff_TypeInfo {

    name = "net.bodz.lily.schema.pub.ArticleTag"
    icon = "fa-tag"

    static validators = new ArticleTagValidators();

    static declaredProperty: EntityPropertyMap = {
    }

    constructor() {
        super();
        this.declare(ArticleTagTypeInfo.declaredProperty);
    }

}

export default ArticleTag;
