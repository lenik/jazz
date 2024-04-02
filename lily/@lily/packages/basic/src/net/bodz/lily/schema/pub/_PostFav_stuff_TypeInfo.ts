import { LONG } from "@skeljs/core/src/lang/baseinfo";
import { property } from "@skeljs/dba/src/net/bodz/lily/entity/EntityType";

import FavRecordTypeInfo from "../../concrete/FavRecordTypeInfo";
import { Post_TYPE } from "./PostTypeInfo";
import _PostFav_stuff_Validators from "./_PostFav_stuff_Validators";

export class _PostFav_stuff_TypeInfo extends FavRecordTypeInfo {

    static SCHEMA_NAME = "lily";
    static TABLE_NAME = "post_fav";

    static readonly FIELD_POST_ID = "post";

    static readonly N_POST_ID = 19;

    readonly validators = new _PostFav_stuff_Validators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.lily.schema.pub.PostFav"; }
    get icon() { return "fa-tag"; }

    override preamble() {
        super.preamble();
        this.declare({

            post: property({ type: Post_TYPE, nullable: false, validator: this.validators.validatePost }),
            postId: property({ type: LONG, nullable: false, precision: 19 }),
        });
    }

    static readonly INSTANCE = new _PostFav_stuff_TypeInfo();

}

export default _PostFav_stuff_TypeInfo;

export const _PostFav_stuff_TYPE = _PostFav_stuff_TypeInfo.INSTANCE;
