import _ArticleTagType_stuff from "./_ArticleTagType_stuff";
import { _ArticleTagType_stuffTypeInfo } from "./_ArticleTagType_stuffTypeInfo";

export class ArticleTagType extends _ArticleTagType_stuff<ArticleTagType> {
    static TYPE = new _ArticleTagType_stuffTypeInfo();

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default ArticleTagType;
