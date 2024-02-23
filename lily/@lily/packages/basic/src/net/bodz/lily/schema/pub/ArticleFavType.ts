import { EntityPropertyMap } from "@skeljs/dba/src/net/bodz/lily/entity";

import ArticleFavValidators from "./ArticleFavValidators";
import _ArticleFav_stuff_Type from "./_ArticleFav_stuff_Type";

// Type Info

export class ArticleFavType extends _ArticleFav_stuff_Type {

    name = "net.bodz.lily.schema.pub.ArticleFav"
    icon = "fa-tag"

    static validators = new ArticleFavValidators();

    static declaredProperty: EntityPropertyMap = {
    }

    constructor() {
        super();
        this.declare(ArticleFavType.declaredProperty);
    }

}

export default ArticleFav;
