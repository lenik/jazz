import PostFavValidators from "./PostFavValidators";
import _PostFav_stuff_TypeInfo from "./_PostFav_stuff_TypeInfo";

export class PostFavTypeInfo extends _PostFav_stuff_TypeInfo {

    get name() { return "net.bodz.lily.schema.pub.PostFav"; }
    get icon() { return "fa-tag"; }

    validators = new PostFavValidators(this);

    override preamble() {
        super.preamble();
        this.declare({
        });
    }

    constructor() {
        super();
    }

}

export default PostFavTypeInfo;
