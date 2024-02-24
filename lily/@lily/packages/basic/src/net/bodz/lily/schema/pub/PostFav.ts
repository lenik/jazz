import PostFavTypeInfo from "./PostFavTypeInfo";
import _PostFav_stuff from "./_PostFav_stuff";

export class PostFav extends _PostFav_stuff {
    static TYPE = new PostFavTypeInfo();

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default PostFav;
