import { EntityPropertyMap } from "@skeljs/dba/src/net/bodz/lily/entity";

import PostBackrefValidators from "./PostBackrefValidators";
import _PostBackref_stuff_TypeInfo from "./_PostBackref_stuff_TypeInfo";

// Type Info

export class PostBackrefTypeInfo extends _PostBackref_stuff_TypeInfo {

    name = "net.bodz.lily.schema.pub.PostBackref"
    icon = "fa-tag"

    static validators = new PostBackrefValidators();

    static declaredProperty: EntityPropertyMap = {
    }

    constructor() {
        super();
        this.declare(PostBackrefTypeInfo.declaredProperty);
    }

}

export default PostBackref;
