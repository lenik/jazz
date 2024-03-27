import ArticleFav from "./ArticleFav";
import ArticleFavValidators from "./ArticleFavValidators";
import _ArticleFav_stuff_TypeInfo from "./_ArticleFav_stuff_TypeInfo";

export class ArticleFavTypeInfo extends _ArticleFav_stuff_TypeInfo {

    readonly validators = new ArticleFavValidators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.lily.schema.pub.ArticleFav"; }
    get icon() { return "fa-tag"; }

    override create() {
        return new ArticleFav();
    }

    override preamble() {
        super.preamble();
        this.declare({
        });
    }

    static readonly INSTANCE = new ArticleFavTypeInfo();

}

export default ArticleFavTypeInfo;
