import _ArticleFav_stuff from "./_ArticleFav_stuff";
import { _ArticleFav_stuff_Type } from "./_ArticleFav_stuff_Type";

export class ArticleFav extends _ArticleFav_stuff {
    static TYPE = new _ArticleFav_stuff_Type();

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default ArticleFav;
