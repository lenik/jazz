
import type { FavRecordType } from "@skeljs/dba/src/net/bodz/lily/concrete/FavRecordType";
import { property } from "@skeljs/dba/src/net/bodz/lily/entity";
import type { EntityPropertyMap } from "@skeljs/dba/src/net/bodz/lily/entity";

import { * as validators } from "./PersonValidators";

// Type Info

export class _PostFav_stuff_Type extends FavRecordType {

    static const SCHEMA_NAME = "lily";
    static const TABLE_NAME = "post_fav";

    name = "net.bodz.lily.schema.pub.PostFav"
    icon = "fa-tag"

    static const FIELD_POST_ID = "post";

    static const N_POST_ID = 19;

    static declaredProperty: EntityPropertyMap = {

        post: property({ type: "net.bodz.lily.schema.pub.Post", nullable: false, validator: validators.validate_post }),
        postId: property({ type: "long", nullable: false, precision: 19, validator: validators.validate_postId }),
    }

    constructor() {
        super();
        this.declare(_PostFav_stuff_Type.declaredProperty);
    }

}
