import _ArticleParameter_stuff from "./_ArticleParameter_stuff";
import { _ArticleParameter_stuffTypeInfo } from "./_ArticleParameter_stuffTypeInfo";

export class ArticleParameter extends _ArticleParameter_stuff {
    static TYPE = new _ArticleParameter_stuffTypeInfo();

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default ArticleParameter;
