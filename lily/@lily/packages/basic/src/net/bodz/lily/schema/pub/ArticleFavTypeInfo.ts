import { EntityPropertyMap } from "@skeljs/dba/src/net/bodz/lily/entity";

import ArticleFavValidators from "./ArticleFavValidators";
import _ArticleFav_stuff_TypeInfo from "./_ArticleFav_stuff_TypeInfo";

// Type Info

export class ArticleFavTypeInfo extends _ArticleFav_stuff_TypeInfo {

    name = "net.bodz.lily.schema.pub.ArticleFav"
    icon = "fa-tag"

    static validators = new ArticleFavValidators();

    static declaredProperty: EntityPropertyMap = {
    }

    constructor() {
        super();
        this.declare(ArticleFavTypeInfo.declaredProperty);
    }

}

export default ArticleFav;
