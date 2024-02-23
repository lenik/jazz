import { EntityPropertyMap } from "@skeljs/dba/src/net/bodz/lily/entity";

import ArticleTagTypeValidators from "./ArticleTagTypeValidators";
import _ArticleTagType_stuff_TypeInfo from "./_ArticleTagType_stuff_TypeInfo";

// Type Info

export class ArticleTagTypeTypeInfo extends _ArticleTagType_stuff_TypeInfo {

    name = "net.bodz.lily.schema.pub.ArticleTagType"
    icon = "fa-tag"

    static validators = new ArticleTagTypeValidators();

    static declaredProperty: EntityPropertyMap = {
    }

    constructor() {
        super();
        this.declare(ArticleTagTypeTypeInfo.declaredProperty);
    }

}

export default ArticleTagType;
