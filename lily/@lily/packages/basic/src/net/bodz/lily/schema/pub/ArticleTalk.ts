import ArticleTalkTypeInfo from "./ArticleTalkTypeInfo";
import _ArticleTalk_stuff from "./_ArticleTalk_stuff";

export class ArticleTalk extends _ArticleTalk_stuff<ArticleTalk> {
    static TYPE = new ArticleTalkTypeInfo();

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default ArticleTalk;
