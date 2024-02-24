import { EntityPropertyMap } from "@skeljs/dba/src/net/bodz/lily/entity";

import PostParameterValidators from "./PostParameterValidators";
import _PostParameter_stuff_TypeInfo from "./_PostParameter_stuff_TypeInfo";

export class PostParameterTypeInfo extends _PostParameter_stuff_TypeInfo {

    name = "net.bodz.lily.schema.pub.PostParameter"
    icon = "fa-tag"

    validators = new PostParameterValidators(this);

    declaredProperty: EntityPropertyMap = {
    }

    constructor() {
        super();
        this.declare(this.declaredProperty);
    }

}

export default PostParameterTypeInfo;
