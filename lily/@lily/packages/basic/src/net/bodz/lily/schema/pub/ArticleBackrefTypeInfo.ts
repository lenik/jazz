import EntityPropertyMap from "@skeljs/dba/src/net/bodz/lily/entity/EntityPropertyMap";

import ArticleBackrefValidators from "./ArticleBackrefValidators";
import _ArticleBackref_stuff_TypeInfo from "./_ArticleBackref_stuff_TypeInfo";

export class ArticleBackrefTypeInfo extends _ArticleBackref_stuff_TypeInfo {

    get name() { return "net.bodz.lily.schema.pub.ArticleBackref"; }
    get icon() { return "fa-tag"; }

    validators = new ArticleBackrefValidators(this);

    declaredProperty: EntityPropertyMap = {
    }

    constructor() {
        super();
        this.declare(this.declaredProperty);
    }

}

export default ArticleBackrefTypeInfo;
