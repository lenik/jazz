import _ArticleCategory_stuff from "./_ArticleCategory_stuff";
import { _ArticleCategory_stuffTypeInfo } from "./_ArticleCategory_stuffTypeInfo";

export class ArticleCategory extends _ArticleCategory_stuff {
    static TYPE = new _ArticleCategory_stuffTypeInfo();

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default ArticleCategory;
