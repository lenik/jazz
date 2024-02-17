
import { * as validators } from "./PersonValidators";
import type { _UserSecret_stuff } from "./_UserSecret_stuff";

export class UserSecret extends _UserSecret_stuff {
    static TYPE = new UserSecretType();

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}
