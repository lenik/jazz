import { ValidateResult } from "@skeljs/core/src/ui/types";
import CoTalkValidators from "@skeljs/dba/src/net/bodz/lily/concrete/CoTalkValidators";

import { TypeParamType } from "../../meta/TypeParamType";
import { Article } from "./Article";

export class _ArticleTalk_stuffValidators extends CoTalkValidators {
    validateFormArguments(val: string) {
    }

    validateArticle(val: Article) {
    }

}

export default _ArticleTalk_stuffValidators;
