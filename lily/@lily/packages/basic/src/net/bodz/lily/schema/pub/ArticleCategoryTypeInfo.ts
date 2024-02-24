import { EntityPropertyMap } from "@skeljs/dba/src/net/bodz/lily/entity";

import ArticleCategoryValidators from "./ArticleCategoryValidators";
import _ArticleCategory_stuff_TypeInfo from "./_ArticleCategory_stuff_TypeInfo";

export class ArticleCategoryTypeInfo extends _ArticleCategory_stuff_TypeInfo {

    name = "net.bodz.lily.schema.pub.ArticleCategory"
    icon = "fa-tag"

    validators = new ArticleCategoryValidators(this);

    declaredProperty: EntityPropertyMap = {
    }

    constructor() {
        super();
        this.declare(this.declaredProperty);
    }

}

export default ArticleCategoryTypeInfo;
