import type { long } from "@skeljs/core/src/lang/type";
import FavRecordType from "@skeljs/dba/src/net/bodz/lily/concrete/FavRecordType";
import { EntityPropertyMap, property } from "@skeljs/dba/src/net/bodz/lily/entity";

import { Post } from "./Post";
import PostFavValidators from "./PostFavValidators";

export class _PostFav_stuff_Type extends FavRecordType {

    static SCHEMA_NAME = "lily";
    static TABLE_NAME = "post_fav";

    name = "net.bodz.lily.schema.pub.PostFav"
    icon = "fa-tag"

    static FIELD_POST_ID = "post";

    static N_POST_ID = 19;

    static validators = new PostFavValidators();

    static declaredProperty: EntityPropertyMap = {

        post: property({ type: Post.TYPE, nullable: false, validator: this.validators.validatePost }),
        postId: property({ type: "long", nullable: false, precision: 19 }),
    }

    constructor() {
        super();
        this.declare(_PostFav_stuff_Type.declaredProperty);
    }

}

export default _PostFav_stuff_Type;
