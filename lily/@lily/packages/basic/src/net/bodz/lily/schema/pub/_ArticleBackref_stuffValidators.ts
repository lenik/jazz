import type { integer } from "@skeljs/core/src/lang/type";
import { ValidateResult } from "@skeljs/core/src/ui/types";
import BackrefRecordValidators from "@skeljs/dba/src/net/bodz/lily/concrete/BackrefRecordValidators";

import { ExternalSite } from "../inet/ExternalSite";
import { Article } from "./Article";

export class _ArticleBackref_stuffValidators extends BackrefRecordValidators {
    validateKey(val: string) {
    }

    validateValue(val: integer) {
    }

    validateArticle(val: Article) {
    }

    validateSite(val: ExternalSite) {
    }

}

export default _ArticleBackref_stuffValidators;
