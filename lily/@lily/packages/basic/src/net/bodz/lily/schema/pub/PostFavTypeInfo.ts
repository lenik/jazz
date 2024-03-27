import PostFav from "./PostFav";
import PostFavValidators from "./PostFavValidators";
import _PostFav_stuff_TypeInfo from "./_PostFav_stuff_TypeInfo";

export class PostFavTypeInfo extends _PostFav_stuff_TypeInfo {

    readonly validators = new PostFavValidators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.lily.schema.pub.PostFav"; }
    get icon() { return "fa-tag"; }

    override create() {
        return new PostFav();
    }

    override preamble() {
        super.preamble();
        this.declare({
        });
    }

    static readonly INSTANCE = new PostFavTypeInfo();

}

export default PostFavTypeInfo;
