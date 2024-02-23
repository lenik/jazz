import { EntityPropertyMap } from "@skeljs/dba/src/net/bodz/lily/entity";

import PostTagTypeValidators from "./PostTagTypeValidators";
import _PostTagType_stuff_Type from "./_PostTagType_stuff_Type";

// Type Info

export class PostTagTypeType extends _PostTagType_stuff_Type {

    name = "net.bodz.lily.schema.pub.PostTagType"
    icon = "fa-tag"

    static validators = new PostTagTypeValidators();

    static declaredProperty: EntityPropertyMap = {
    }

    constructor() {
        super();
        this.declare(PostTagTypeType.declaredProperty);
    }

}

export default PostTagType;
