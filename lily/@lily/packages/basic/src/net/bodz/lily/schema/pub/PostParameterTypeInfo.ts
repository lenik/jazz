import { EntityPropertyMap } from "@skeljs/dba/src/net/bodz/lily/entity";

import PostParameterValidators from "./PostParameterValidators";
import _PostParameter_stuff_TypeInfo from "./_PostParameter_stuff_TypeInfo";

// Type Info

export class PostParameterTypeInfo extends _PostParameter_stuff_TypeInfo {

    name = "net.bodz.lily.schema.pub.PostParameter"
    icon = "fa-tag"

    static validators = new PostParameterValidators();

    static declaredProperty: EntityPropertyMap = {
    }

    constructor() {
        super();
        this.declare(PostParameterTypeInfo.declaredProperty);
    }

}

export default PostParameter;
