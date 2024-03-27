import ArticleFavTypeInfo from "./ArticleFavTypeInfo";
import _ArticleFav_stuff from "./_ArticleFav_stuff";

export class ArticleFav extends _ArticleFav_stuff {

    static _typeInfo: ArticleFavTypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = ArticleFavTypeInfo.INSTANCE;
        return this._typeInfo;
    }

    constructor(o?: any) {
        super(o);
    }
}

export default ArticleFav;
