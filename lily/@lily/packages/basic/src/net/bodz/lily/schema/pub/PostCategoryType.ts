import { EntityPropertyMap } from "@skeljs/dba/src/net/bodz/lily/entity";

import PostCategoryValidators from "./PostCategoryValidators";
import _PostCategory_stuff_Type from "./_PostCategory_stuff_Type";

// Type Info

export class PostCategoryType extends _PostCategory_stuff_Type {

    name = "net.bodz.lily.schema.pub.PostCategory"
    icon = "fa-tag"

    static validators = new PostCategoryValidators();

    static declaredProperty: EntityPropertyMap = {
    }

    constructor() {
        super();
        this.declare(PostCategoryType.declaredProperty);
    }

}

export default PostCategory;
