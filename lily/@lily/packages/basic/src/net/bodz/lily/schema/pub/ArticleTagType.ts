import ArticleTagTypeTypeInfo from "./ArticleTagTypeTypeInfo";
import _ArticleTagType_stuff from "./_ArticleTagType_stuff";

export class ArticleTagType extends _ArticleTagType_stuff<ArticleTagType> {
    static TYPE = new ArticleTagTypeTypeInfo();

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default ArticleTagType;
