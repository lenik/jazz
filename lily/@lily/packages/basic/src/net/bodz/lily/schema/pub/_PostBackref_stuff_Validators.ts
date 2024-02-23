import type { integer } from "@skeljs/core/src/lang/type";
import { ValidateResult } from "@skeljs/core/src/ui/types";
import BackrefRecordValidators from "@skeljs/dba/src/net/bodz/lily/concrete/BackrefRecordValidators";

import { ExternalSite } from "../inet/ExternalSite";
import { Post } from "./Post";

export class _PostBackref_stuff_Validators extends BackrefRecordValidators {
    validateKey(val: string) {
    }

    validateValue(val: integer) {
    }

    validatePost(val: Post) {
    }

    validateSite(val: ExternalSite) {
    }

}

export default _PostBackref_stuff_Validators;
