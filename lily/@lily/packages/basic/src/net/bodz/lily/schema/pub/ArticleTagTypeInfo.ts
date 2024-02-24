import { EntityPropertyMap } from "@skeljs/dba/src/net/bodz/lily/entity";

import ArticleTagValidators from "./ArticleTagValidators";
import _ArticleTag_stuff_TypeInfo from "./_ArticleTag_stuff_TypeInfo";

export class ArticleTagTypeInfo extends _ArticleTag_stuff_TypeInfo {

    name = "net.bodz.lily.schema.pub.ArticleTag"
    icon = "fa-tag"

    validators = new ArticleTagValidators(this);

    declaredProperty: EntityPropertyMap = {
    }

    constructor() {
        super();
        this.declare(this.declaredProperty);
    }

}

export default ArticleTagTypeInfo;
