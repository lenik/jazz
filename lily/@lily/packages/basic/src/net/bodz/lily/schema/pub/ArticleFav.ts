import _ArticleFav_stuff from "./_ArticleFav_stuff";
import { _ArticleFav_stuffTypeInfo } from "./_ArticleFav_stuffTypeInfo";

export class ArticleFav extends _ArticleFav_stuff {
    static TYPE = new _ArticleFav_stuffTypeInfo();

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default ArticleFav;
