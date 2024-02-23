import type { integer, long } from "@skeljs/core/src/lang/type";
import { ValidateResult } from "@skeljs/core/src/ui/types";
import CoMessageValidators from "@skeljs/dba/src/net/bodz/lily/concrete/CoMessageValidators";

import { ArticleCategory } from "./ArticleCategory";

export class _Article_stuff_Validators extends CoMessageValidators {
    validateFormArguments(val: string) {
    }

    validateFavCount(val: integer) {
    }

    validateVoteCount(val: integer) {
    }

    validateHateCount(val: integer) {
    }

    validateMessageCount(val: integer) {
    }

    validatePlugins(val: any) {
    }

    validateCategory(val: ArticleCategory) {
    }

}

export default _Article_stuff_Validators;
