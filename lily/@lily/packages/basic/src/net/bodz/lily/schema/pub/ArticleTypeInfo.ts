import { EntityPropertyMap } from "@skeljs/dba/src/net/bodz/lily/entity";

import ArticleValidators from "./ArticleValidators";
import _Article_stuff_TypeInfo from "./_Article_stuff_TypeInfo";

export class ArticleTypeInfo extends _Article_stuff_TypeInfo {

    name = "net.bodz.lily.schema.pub.Article"
    icon = "fa-tag"

    validators = new ArticleValidators(this);

    declaredProperty: EntityPropertyMap = {
    }

    constructor() {
        super();
        this.declare(this.declaredProperty);
    }

}

export default ArticleTypeInfo;
