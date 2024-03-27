import ArticleCategoryTypeInfo from "./ArticleCategoryTypeInfo";
import _ArticleCategory_stuff from "./_ArticleCategory_stuff";

export class ArticleCategory extends _ArticleCategory_stuff<ArticleCategory> {

    static _typeInfo: ArticleCategoryTypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = ArticleCategoryTypeInfo.INSTANCE;
        return this._typeInfo;
    }

    constructor(o?: any) {
        super(o);
    }
}

export default ArticleCategory;
