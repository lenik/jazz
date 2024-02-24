import ParameterDefTypeInfo from "./ParameterDefTypeInfo";
import _ParameterDef_stuff from "./_ParameterDef_stuff";

export class ParameterDef extends _ParameterDef_stuff {
    static TYPE = new ParameterDefTypeInfo();

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default ParameterDef;
