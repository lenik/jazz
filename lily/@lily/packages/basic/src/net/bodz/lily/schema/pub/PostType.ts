import { EntityPropertyMap } from "@skeljs/dba/src/net/bodz/lily/entity";

import PostValidators from "./PostValidators";
import _Post_stuff_Type from "./_Post_stuff_Type";

// Type Info

export class PostType extends _Post_stuff_Type {

    name = "net.bodz.lily.schema.pub.Post"
    icon = "fa-tag"

    static validators = new PostValidators();

    static declaredProperty: EntityPropertyMap = {
    }

    constructor() {
        super();
        this.declare(PostType.declaredProperty);
    }

}

export default Post;
