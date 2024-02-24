import ArticleTagTypeInfo from "./ArticleTagTypeInfo";
import _ArticleTag_stuff from "./_ArticleTag_stuff";

export class ArticleTag extends _ArticleTag_stuff {
    static TYPE = new ArticleTagTypeInfo();

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default ArticleTag;
