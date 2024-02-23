import { EntityPropertyMap } from "@skeljs/dba/src/net/bodz/lily/entity";

import PostCategoryValidators from "./PostCategoryValidators";
import _PostCategory_stuff_TypeInfo from "./_PostCategory_stuff_TypeInfo";

// Type Info

export class PostCategoryTypeInfo extends _PostCategory_stuff_TypeInfo {

    name = "net.bodz.lily.schema.pub.PostCategory"
    icon = "fa-tag"

    static validators = new PostCategoryValidators();

    static declaredProperty: EntityPropertyMap = {
    }

    constructor() {
        super();
        this.declare(PostCategoryTypeInfo.declaredProperty);
    }

}

export default PostCategory;
