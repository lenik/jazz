import ArticleFavValidators from "./ArticleFavValidators";
import _ArticleFav_stuff_TypeInfo from "./_ArticleFav_stuff_TypeInfo";

export class ArticleFavTypeInfo extends _ArticleFav_stuff_TypeInfo {

    get name() { return "net.bodz.lily.schema.pub.ArticleFav"; }
    get icon() { return "fa-tag"; }

    validators = new ArticleFavValidators(this);

    override preamble() {
        super.preamble();
        this.declare({
        });
    }

    constructor() {
        super();
    }

}

export default ArticleFavTypeInfo;
