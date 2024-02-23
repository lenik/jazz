import type { integer } from "@skeljs/core/src/lang/type";
import { ValidateResult } from "@skeljs/core/src/ui/types";
import CoEntityValidators from "@skeljs/dba/src/net/bodz/lily/concrete/CoEntityValidators";

import { Article } from "./Article";
import { ArticleTagType } from "./ArticleTagType";

export class _ArticleTag_stuff_Validators extends CoEntityValidators {
    validateId(val: integer) {
    }

    validateTag(val: ArticleTagType) {
    }

    validateArticle(val: Article) {
    }

}

export default _ArticleTag_stuff_Validators;
