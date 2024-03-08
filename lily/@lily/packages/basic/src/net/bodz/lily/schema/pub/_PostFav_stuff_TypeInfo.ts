import { LONG } from "@skeljs/core/src/lang/baseinfo";
import { property } from "@skeljs/dba/src/net/bodz/lily/entity/EntityType";

import FavRecordTypeInfo from "../../concrete/FavRecordTypeInfo";
import Post from "./Post";
import _PostFav_stuff_Validators from "./_PostFav_stuff_Validators";

export class _PostFav_stuff_TypeInfo extends FavRecordTypeInfo {

    static SCHEMA_NAME = "lily";
    static TABLE_NAME = "post_fav";

    get name() { return "net.bodz.lily.schema.pub.PostFav"; }
    get icon() { return "fa-tag"; }

    static FIELD_POST_ID = "post";

    static N_POST_ID = 19;

    validators = new _PostFav_stuff_Validators(this);

    override preamble() {
        super.preamble();
        this.declare({

            post: property({ type: Post.TYPE, nullable: false, validator: this.validators.validatePost }),
            postId: property({ type: LONG, nullable: false, precision: 19 }),
        });
    }

    constructor() {
        super();
    }

}

export default _PostFav_stuff_TypeInfo;
