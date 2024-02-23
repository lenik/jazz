import _ArticleTag_stuff from "./_ArticleTag_stuff";
import { _ArticleTag_stuff_Type } from "./_ArticleTag_stuff_Type";

export class ArticleTag extends _ArticleTag_stuff {
    static TYPE = new _ArticleTag_stuff_Type();

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default ArticleTag;
