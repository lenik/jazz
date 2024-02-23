import { EntityPropertyMap } from "@skeljs/dba/src/net/bodz/lily/entity";

import ArticleParameterTypeValidators from "./ArticleParameterTypeValidators";
import _ArticleParameterType_stuff_TypeInfo from "./_ArticleParameterType_stuff_TypeInfo";

// Type Info

export class ArticleParameterTypeTypeInfo extends _ArticleParameterType_stuff_TypeInfo {

    name = "net.bodz.lily.schema.pub.ArticleParameterType"
    icon = "fa-tag"

    static validators = new ArticleParameterTypeValidators();

    static declaredProperty: EntityPropertyMap = {
    }

    constructor() {
        super();
        this.declare(ArticleParameterTypeTypeInfo.declaredProperty);
    }

}

export default ArticleParameterType;
