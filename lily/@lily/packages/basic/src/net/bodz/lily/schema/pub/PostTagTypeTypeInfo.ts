import { EntityPropertyMap } from "@skeljs/dba/src/net/bodz/lily/entity";

import PostTagTypeValidators from "./PostTagTypeValidators";
import _PostTagType_stuff_TypeInfo from "./_PostTagType_stuff_TypeInfo";

// Type Info

export class PostTagTypeTypeInfo extends _PostTagType_stuff_TypeInfo {

    name = "net.bodz.lily.schema.pub.PostTagType"
    icon = "fa-tag"

    static validators = new PostTagTypeValidators();

    static declaredProperty: EntityPropertyMap = {
    }

    constructor() {
        super();
        this.declare(PostTagTypeTypeInfo.declaredProperty);
    }

}

export default PostTagType;
