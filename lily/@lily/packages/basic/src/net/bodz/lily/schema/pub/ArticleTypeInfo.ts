import EntityPropertyMap from "@skeljs/dba/src/net/bodz/lily/entity/EntityPropertyMap";

import ArticleValidators from "./ArticleValidators";
import _Article_stuff_TypeInfo from "./_Article_stuff_TypeInfo";

export class ArticleTypeInfo extends _Article_stuff_TypeInfo {

    get name() { return "net.bodz.lily.schema.pub.Article"; }
    get icon() { return "fa-tag"; }

    validators = new ArticleValidators(this);

    declaredProperty: EntityPropertyMap = {
    }

    constructor() {
        super();
        this.declare(this.declaredProperty);
    }

}

export default ArticleTypeInfo;
