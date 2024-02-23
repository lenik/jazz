import { EntityPropertyMap } from "@skeljs/dba/src/net/bodz/lily/entity";

import PostParameterTypeValidators from "./PostParameterTypeValidators";
import _PostParameterType_stuff_Type from "./_PostParameterType_stuff_Type";

// Type Info

export class PostParameterTypeType extends _PostParameterType_stuff_Type {

    name = "net.bodz.lily.schema.pub.PostParameterType"
    icon = "fa-tag"

    static validators = new PostParameterTypeValidators();

    static declaredProperty: EntityPropertyMap = {
    }

    constructor() {
        super();
        this.declare(PostParameterTypeType.declaredProperty);
    }

}

export default PostParameterType;
