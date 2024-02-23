import { EntityPropertyMap } from "@skeljs/dba/src/net/bodz/lily/entity";

import ArticleParameterTypeValidators from "./ArticleParameterTypeValidators";
import _ArticleParameterType_stuff_Type from "./_ArticleParameterType_stuff_Type";

// Type Info

export class ArticleParameterTypeType extends _ArticleParameterType_stuff_Type {

    name = "net.bodz.lily.schema.pub.ArticleParameterType"
    icon = "fa-tag"

    static validators = new ArticleParameterTypeValidators();

    static declaredProperty: EntityPropertyMap = {
    }

    constructor() {
        super();
        this.declare(ArticleParameterTypeType.declaredProperty);
    }

}

export default ArticleParameterType;
