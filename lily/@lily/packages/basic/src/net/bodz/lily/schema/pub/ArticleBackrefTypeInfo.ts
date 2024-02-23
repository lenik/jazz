import { EntityPropertyMap } from "@skeljs/dba/src/net/bodz/lily/entity";

import ArticleBackrefValidators from "./ArticleBackrefValidators";
import _ArticleBackref_stuff_TypeInfo from "./_ArticleBackref_stuff_TypeInfo";

// Type Info

export class ArticleBackrefTypeInfo extends _ArticleBackref_stuff_TypeInfo {

    name = "net.bodz.lily.schema.pub.ArticleBackref"
    icon = "fa-tag"

    static validators = new ArticleBackrefValidators();

    static declaredProperty: EntityPropertyMap = {
    }

    constructor() {
        super();
        this.declare(ArticleBackrefTypeInfo.declaredProperty);
    }

}

export default ArticleBackref;
