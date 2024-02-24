import ApiTypeTypeInfo from "./ApiTypeTypeInfo";
import _ApiType_stuff from "./_ApiType_stuff";

export class ApiType extends _ApiType_stuff {
    static TYPE = new ApiTypeTypeInfo();

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default ApiType;
