import { EntityPropertyMap } from "@skeljs/dba/src/net/bodz/lily/entity";

import ArticleParameterValidators from "./ArticleParameterValidators";
import _ArticleParameter_stuff_TypeInfo from "./_ArticleParameter_stuff_TypeInfo";

export class ArticleParameterTypeInfo extends _ArticleParameter_stuff_TypeInfo {

    name = "net.bodz.lily.schema.pub.ArticleParameter"
    icon = "fa-tag"

    validators = new ArticleParameterValidators(this);

    declaredProperty: EntityPropertyMap = {
    }

    constructor() {
        super();
        this.declare(this.declaredProperty);
    }

}

export default ArticleParameterTypeInfo;
