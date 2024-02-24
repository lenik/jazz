import { EntityPropertyMap } from "@skeljs/dba/src/net/bodz/lily/entity";

import PostParameterTypeValidators from "./PostParameterTypeValidators";
import _PostParameterType_stuff_TypeInfo from "./_PostParameterType_stuff_TypeInfo";

export class PostParameterTypeTypeInfo extends _PostParameterType_stuff_TypeInfo {

    name = "net.bodz.lily.schema.pub.PostParameterType"
    icon = "fa-tag"

    validators = new PostParameterTypeValidators(this);

    declaredProperty: EntityPropertyMap = {
    }

    constructor() {
        super();
        this.declare(this.declaredProperty);
    }

}

export default PostParameterTypeTypeInfo;
