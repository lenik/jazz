import { EntityPropertyMap } from "@skeljs/dba/src/net/bodz/lily/entity";

import ArticleValidators from "./ArticleValidators";
import _Article_stuff_Type from "./_Article_stuff_Type";

// Type Info

export class ArticleType extends _Article_stuff_Type {

    name = "net.bodz.lily.schema.pub.Article"
    icon = "fa-tag"

    static validators = new ArticleValidators();

    static declaredProperty: EntityPropertyMap = {
    }

    constructor() {
        super();
        this.declare(ArticleType.declaredProperty);
    }

}

export default Article;
