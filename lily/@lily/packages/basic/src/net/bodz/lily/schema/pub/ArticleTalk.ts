import _ArticleTalk_stuff from "./_ArticleTalk_stuff";
import { _ArticleTalk_stuff_Type } from "./_ArticleTalk_stuff_Type";

export class ArticleTalk extends _ArticleTalk_stuff<ArticleTalk> {
    static TYPE = new _ArticleTalk_stuff_Type();

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default ArticleTalk;
