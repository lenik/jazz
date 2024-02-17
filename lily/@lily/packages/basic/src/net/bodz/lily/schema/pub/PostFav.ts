
import { * as validators } from "./PersonValidators";
import type { _PostFav_stuff } from "./_PostFav_stuff";

export class PostFav extends _PostFav_stuff {
    static TYPE = new PostFavType();

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}
