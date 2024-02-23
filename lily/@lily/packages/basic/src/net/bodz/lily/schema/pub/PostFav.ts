import _PostFav_stuff from "./_PostFav_stuff";
import { _PostFav_stuffTypeInfo } from "./_PostFav_stuffTypeInfo";

export class PostFav extends _PostFav_stuff {
    static TYPE = new _PostFav_stuffTypeInfo();

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default PostFav;
