import ArticleFavTypeInfo from "./ArticleFavTypeInfo";
import _ArticleFav_stuff from "./_ArticleFav_stuff";

export class ArticleFav extends _ArticleFav_stuff {
    static TYPE = new ArticleFavTypeInfo();

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default ArticleFav;
