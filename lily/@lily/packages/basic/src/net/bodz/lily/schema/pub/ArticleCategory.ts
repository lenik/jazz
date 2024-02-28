import ArticleCategoryTypeInfo from "./ArticleCategoryTypeInfo";
import _ArticleCategory_stuff from "./_ArticleCategory_stuff";

export class ArticleCategory extends _ArticleCategory_stuff {
    static _typeInfo: ArticleCategoryTypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = new ArticleCategoryTypeInfo();
        return this._typeInfo;
    }


    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default ArticleCategory;
