import { EntityPropertyMap } from "@skeljs/dba/src/net/bodz/lily/entity/EntityType";

import ArticleParameterTypeValidators from "./ArticleParameterTypeValidators";
import _ArticleParameterType_stuff_TypeInfo from "./_ArticleParameterType_stuff_TypeInfo";

export class ArticleParameterTypeTypeInfo extends _ArticleParameterType_stuff_TypeInfo {

    get name() { return "net.bodz.lily.schema.pub.ArticleParameterType"; }
    get icon() { return "fa-tag"; }

    validators = new ArticleParameterTypeValidators(this);

    declaredProperty: EntityPropertyMap = {
    }

    constructor() {
        super();
        this.declare(this.declaredProperty);
    }

}

export default ArticleParameterTypeTypeInfo;
