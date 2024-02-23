import { EntityPropertyMap } from "@skeljs/dba/src/net/bodz/lily/entity";

import PostFavValidators from "./PostFavValidators";
import _PostFav_stuff_TypeInfo from "./_PostFav_stuff_TypeInfo";

// Type Info

export class PostFavTypeInfo extends _PostFav_stuff_TypeInfo {

    name = "net.bodz.lily.schema.pub.PostFav"
    icon = "fa-tag"

    static validators = new PostFavValidators();

    static declaredProperty: EntityPropertyMap = {
    }

    constructor() {
        super();
        this.declare(PostFavTypeInfo.declaredProperty);
    }

}

export default PostFav;
