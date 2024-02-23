import { EntityPropertyMap } from "@skeljs/dba/src/net/bodz/lily/entity";

import ArticleValidators from "./ArticleValidators";
import _Article_stuff_TypeInfo from "./_Article_stuff_TypeInfo";

// Type Info

export class ArticleTypeInfo extends _Article_stuff_TypeInfo {

    name = "net.bodz.lily.schema.pub.Article"
    icon = "fa-tag"

    static validators = new ArticleValidators();

    static declaredProperty: EntityPropertyMap = {
    }

    constructor() {
        super();
        this.declare(ArticleTypeInfo.declaredProperty);
    }

}

export default Article;
