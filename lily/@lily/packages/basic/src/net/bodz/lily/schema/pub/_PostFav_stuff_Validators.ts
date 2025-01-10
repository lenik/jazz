import { ValidateResult } from "skel01-core/src/ui/types";

import FavRecordValidators from "../../concrete/FavRecordValidators";
import type Post from "./Post";
import type _PostFav_stuff_TypeInfo from "./_PostFav_stuff_TypeInfo";

export class _PostFav_stuff_Validators extends FavRecordValidators {

    constructor(type: _PostFav_stuff_TypeInfo) {
        super(type);
    }

    get type() {
        return this._type as _PostFav_stuff_TypeInfo;
    }

    validatePost(val: Post) {
    }

}

export default _PostFav_stuff_Validators;
