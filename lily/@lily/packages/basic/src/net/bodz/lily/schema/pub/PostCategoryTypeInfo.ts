import EntityPropertyMap from "@skeljs/dba/src/net/bodz/lily/entity/EntityPropertyMap";

import PostCategoryValidators from "./PostCategoryValidators";
import _PostCategory_stuff_TypeInfo from "./_PostCategory_stuff_TypeInfo";

export class PostCategoryTypeInfo extends _PostCategory_stuff_TypeInfo {

    get name() { return "net.bodz.lily.schema.pub.PostCategory"; }
    get icon() { return "fa-tag"; }

    validators = new PostCategoryValidators(this);

    declaredProperty: EntityPropertyMap = {
    }

    constructor() {
        super();
        this.declare(this.declaredProperty);
    }

}

export default PostCategoryTypeInfo;
