import { EntityPropertyMap } from "@skeljs/dba/src/net/bodz/lily/entity";

import PostValidators from "./PostValidators";
import _Post_stuff_TypeInfo from "./_Post_stuff_TypeInfo";

// Type Info

export class PostTypeInfo extends _Post_stuff_TypeInfo {

    name = "net.bodz.lily.schema.pub.Post"
    icon = "fa-tag"

    static validators = new PostValidators();

    static declaredProperty: EntityPropertyMap = {
    }

    constructor() {
        super();
        this.declare(PostTypeInfo.declaredProperty);
    }

}

export default Post;
