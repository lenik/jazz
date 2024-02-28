import EntityPropertyMap from "@skeljs/dba/src/net/bodz/lily/entity/EntityPropertyMap";

import ArticleCategoryValidators from "./ArticleCategoryValidators";
import _ArticleCategory_stuff_TypeInfo from "./_ArticleCategory_stuff_TypeInfo";

export class ArticleCategoryTypeInfo extends _ArticleCategory_stuff_TypeInfo {

    get name() { return "net.bodz.lily.schema.pub.ArticleCategory"; }
    get icon() { return "fa-tag"; }

    validators = new ArticleCategoryValidators(this);

    declaredProperty: EntityPropertyMap = {
    }

    constructor() {
        super();
        this.declare(this.declaredProperty);
    }

}

export default ArticleCategoryTypeInfo;
