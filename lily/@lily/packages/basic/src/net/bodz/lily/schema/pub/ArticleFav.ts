
import { * as validators } from "./PersonValidators";
import type { _ArticleFav_stuff } from "./_ArticleFav_stuff";

export class ArticleFav extends _ArticleFav_stuff {
    static TYPE = new ArticleFavType();

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}
