import _ArticleBackref_stuff from "./_ArticleBackref_stuff";
import { _ArticleBackref_stuffTypeInfo } from "./_ArticleBackref_stuffTypeInfo";

export class ArticleBackref extends _ArticleBackref_stuff {
    static TYPE = new _ArticleBackref_stuffTypeInfo();

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default ArticleBackref;
