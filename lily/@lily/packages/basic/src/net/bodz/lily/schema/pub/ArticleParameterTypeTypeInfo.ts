import { EntityPropertyMap } from "@skeljs/dba/src/net/bodz/lily/entity";

import ArticleParameterTypeValidators from "./ArticleParameterTypeValidators";
import _ArticleParameterType_stuff_TypeInfo from "./_ArticleParameterType_stuff_TypeInfo";

export class ArticleParameterTypeTypeInfo extends _ArticleParameterType_stuff_TypeInfo {

    name = "net.bodz.lily.schema.pub.ArticleParameterType"
    icon = "fa-tag"

    validators = new ArticleParameterTypeValidators(this);

    declaredProperty: EntityPropertyMap = {
    }

    constructor() {
        super();
        this.declare(this.declaredProperty);
    }

}

export default ArticleParameterTypeTypeInfo;
