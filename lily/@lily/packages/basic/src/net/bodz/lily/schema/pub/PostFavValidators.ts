import { ValidateResult } from "@skeljs/core/src/ui/types";

import type PostFavTypeInfo from "./PostFavTypeInfo";
import _PostFav_stuff_Validators from "./_PostFav_stuff_Validators";

export class PostFavValidators extends _PostFav_stuff_Validators {

    constructor(type: PostFavTypeInfo) {
        super(type);
    }

    get type() {
        return this._type as PostFavTypeInfo;
    }

}

export default PostFavValidators;
