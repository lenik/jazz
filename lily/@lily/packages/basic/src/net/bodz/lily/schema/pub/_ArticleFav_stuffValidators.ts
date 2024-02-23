import { ValidateResult } from "@skeljs/core/src/ui/types";
import FavRecordValidators from "@skeljs/dba/src/net/bodz/lily/concrete/FavRecordValidators";

import { Article } from "./Article";

export class _ArticleFav_stuffValidators extends FavRecordValidators {
    validateArticle(val: Article) {
    }

}

export default _ArticleFav_stuffValidators;
