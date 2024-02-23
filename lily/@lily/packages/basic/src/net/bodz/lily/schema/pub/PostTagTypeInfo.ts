import { EntityPropertyMap } from "@skeljs/dba/src/net/bodz/lily/entity";

import PostTagValidators from "./PostTagValidators";
import _PostTag_stuff_TypeInfo from "./_PostTag_stuff_TypeInfo";

// Type Info

export class PostTagTypeInfo extends _PostTag_stuff_TypeInfo {

    name = "net.bodz.lily.schema.pub.PostTag"
    icon = "fa-tag"

    static validators = new PostTagValidators();

    static declaredProperty: EntityPropertyMap = {
    }

    constructor() {
        super();
        this.declare(PostTagTypeInfo.declaredProperty);
    }

}

export default PostTag;
