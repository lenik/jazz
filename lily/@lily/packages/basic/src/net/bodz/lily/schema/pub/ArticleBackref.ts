import ArticleBackrefTypeInfo from "./ArticleBackrefTypeInfo";
import _ArticleBackref_stuff from "./_ArticleBackref_stuff";

export class ArticleBackref extends _ArticleBackref_stuff {
    static TYPE = new ArticleBackrefTypeInfo();

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default ArticleBackref;
