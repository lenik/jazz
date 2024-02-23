import _ArticleBackref_stuff from "./_ArticleBackref_stuff";
import { _ArticleBackref_stuff_Type } from "./_ArticleBackref_stuff_Type";

export class ArticleBackref extends _ArticleBackref_stuff {
    static TYPE = new _ArticleBackref_stuff_Type();

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default ArticleBackref;
