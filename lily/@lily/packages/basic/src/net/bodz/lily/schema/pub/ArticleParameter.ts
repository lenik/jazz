import _ArticleParameter_stuff from "./_ArticleParameter_stuff";
import { _ArticleParameter_stuff_Type } from "./_ArticleParameter_stuff_Type";

export class ArticleParameter extends _ArticleParameter_stuff {
    static TYPE = new _ArticleParameter_stuff_Type();

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default ArticleParameter;
