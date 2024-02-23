import { EntityPropertyMap } from "@skeljs/dba/src/net/bodz/lily/entity";

import ArticleCategoryValidators from "./ArticleCategoryValidators";
import _ArticleCategory_stuff_TypeInfo from "./_ArticleCategory_stuff_TypeInfo";

// Type Info

export class ArticleCategoryTypeInfo extends _ArticleCategory_stuff_TypeInfo {

    name = "net.bodz.lily.schema.pub.ArticleCategory"
    icon = "fa-tag"

    static validators = new ArticleCategoryValidators();

    static declaredProperty: EntityPropertyMap = {
    }

    constructor() {
        super();
        this.declare(ArticleCategoryTypeInfo.declaredProperty);
    }

}

export default ArticleCategory;
