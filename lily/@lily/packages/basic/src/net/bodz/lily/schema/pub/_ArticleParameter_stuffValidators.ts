import type { double, integer } from "@skeljs/core/src/lang/type";
import { ValidateResult } from "@skeljs/core/src/ui/types";
import CoEntityValidators from "@skeljs/dba/src/net/bodz/lily/concrete/CoEntityValidators";

import { Article } from "./Article";
import { ArticleParameterType } from "./ArticleParameterType";

export class _ArticleParameter_stuffValidators extends CoEntityValidators {
    validateId(val: integer) {
    }

    validateIval(val: integer) {
    }

    validateFval(val: double) {
    }

    validateSval(val: string) {
    }

    validateArticle(val: Article) {
    }

    validateParameter(val: ArticleParameterType) {
    }

}

export default _ArticleParameter_stuffValidators;
