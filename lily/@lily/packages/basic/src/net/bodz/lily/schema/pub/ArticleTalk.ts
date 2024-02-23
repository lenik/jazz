import _ArticleTalk_stuff from "./_ArticleTalk_stuff";
import { _ArticleTalk_stuffTypeInfo } from "./_ArticleTalk_stuffTypeInfo";

export class ArticleTalk extends _ArticleTalk_stuff<ArticleTalk> {
    static TYPE = new _ArticleTalk_stuffTypeInfo();

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default ArticleTalk;
