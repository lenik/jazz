import _PostFav_stuff from "./_PostFav_stuff";
import { _PostFav_stuff_Type } from "./_PostFav_stuff_Type";

export class PostFav extends _PostFav_stuff {
    static TYPE = new _PostFav_stuff_Type();

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default PostFav;
