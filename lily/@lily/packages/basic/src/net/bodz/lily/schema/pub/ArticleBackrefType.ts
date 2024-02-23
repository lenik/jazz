import { EntityPropertyMap } from "@skeljs/dba/src/net/bodz/lily/entity";

import ArticleBackrefValidators from "./ArticleBackrefValidators";
import _ArticleBackref_stuff_Type from "./_ArticleBackref_stuff_Type";

// Type Info

export class ArticleBackrefType extends _ArticleBackref_stuff_Type {

    name = "net.bodz.lily.schema.pub.ArticleBackref"
    icon = "fa-tag"

    static validators = new ArticleBackrefValidators();

    static declaredProperty: EntityPropertyMap = {
    }

    constructor() {
        super();
        this.declare(ArticleBackrefType.declaredProperty);
    }

}

export default ArticleBackref;
