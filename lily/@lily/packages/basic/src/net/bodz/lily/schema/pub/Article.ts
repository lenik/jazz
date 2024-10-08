import ArticleTypeInfo from "./ArticleTypeInfo";
import _Article_stuff from "./_Article_stuff";

export class Article extends _Article_stuff {

    static _typeInfo: ArticleTypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = ArticleTypeInfo.INSTANCE;
        return this._typeInfo;
    }

    constructor(o?: any) {
        super(o);
    }
}

export default Article;
