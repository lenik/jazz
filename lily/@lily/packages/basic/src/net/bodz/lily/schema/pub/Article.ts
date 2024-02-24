import ArticleTypeInfo from "./ArticleTypeInfo";
import _Article_stuff from "./_Article_stuff";

export class Article extends _Article_stuff {
    static TYPE = new ArticleTypeInfo();

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default Article;
