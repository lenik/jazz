import ArticleCategoryTypeInfo from "./ArticleCategoryTypeInfo";
import _ArticleCategory_stuff from "./_ArticleCategory_stuff";

export class ArticleCategory extends _ArticleCategory_stuff {
    static TYPE = new ArticleCategoryTypeInfo();

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default ArticleCategory;
