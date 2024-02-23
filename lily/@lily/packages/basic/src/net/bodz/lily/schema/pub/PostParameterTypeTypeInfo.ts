import { EntityPropertyMap } from "@skeljs/dba/src/net/bodz/lily/entity";

import PostParameterTypeValidators from "./PostParameterTypeValidators";
import _PostParameterType_stuff_TypeInfo from "./_PostParameterType_stuff_TypeInfo";

// Type Info

export class PostParameterTypeTypeInfo extends _PostParameterType_stuff_TypeInfo {

    name = "net.bodz.lily.schema.pub.PostParameterType"
    icon = "fa-tag"

    static validators = new PostParameterTypeValidators();

    static declaredProperty: EntityPropertyMap = {
    }

    constructor() {
        super();
        this.declare(PostParameterTypeTypeInfo.declaredProperty);
    }

}

export default PostParameterType;
