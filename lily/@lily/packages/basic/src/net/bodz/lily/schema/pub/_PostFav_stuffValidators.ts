import { ValidateResult } from "@skeljs/core/src/ui/types";
import FavRecordValidators from "@skeljs/dba/src/net/bodz/lily/concrete/FavRecordValidators";

import { Post } from "./Post";

export class _PostFav_stuffValidators extends FavRecordValidators {
    validatePost(val: Post) {
    }

}

export default _PostFav_stuffValidators;
