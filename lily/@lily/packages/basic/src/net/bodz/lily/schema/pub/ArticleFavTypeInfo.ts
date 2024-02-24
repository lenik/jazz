import { EntityPropertyMap } from "@skeljs/dba/src/net/bodz/lily/entity";

import ArticleFavValidators from "./ArticleFavValidators";
import _ArticleFav_stuff_TypeInfo from "./_ArticleFav_stuff_TypeInfo";

export class ArticleFavTypeInfo extends _ArticleFav_stuff_TypeInfo {

    name = "net.bodz.lily.schema.pub.ArticleFav"
    icon = "fa-tag"

    validators = new ArticleFavValidators(this);

    declaredProperty: EntityPropertyMap = {
    }

    constructor() {
        super();
        this.declare(this.declaredProperty);
    }

}

export default ArticleFavTypeInfo;
