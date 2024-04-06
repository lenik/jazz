import TypeInfo from "@skeljs/core/src/lang/TypeInfo";
import { getItemImage } from "@skeljs/core/src/net/bodz/lily/entity/IAttachment";

class DefaultAttachmentTypeInfo extends TypeInfo<any> {

    constructor() {
        super();
    }

    get name() { return "DefaultAttachment" }
    get label() { return "DefaultAttachment" }
    get description() { return "DefaultAttachment" }

    create() {
        return {} as any;
    }

    parse(s: string) {
        return JSON.parse(s);
    }
    format(val: any): string {
        return JSON.stringify(val);
    }
    fromJson(jv: any) {
        return jv;
    }
    toJson(val: any) {
        return val;
    }
    renderHtml(val: any, context: any): string | HTMLElement | undefined {
        if (val == null || val.length == 0)
            return "none";
        let first = val[0];
        let img = document.createElement("img");
        img.src = getItemImage(first, context.id)!;
        img.className = "attachment"
        return img;
    }

    static INSTANCE = new DefaultAttachmentTypeInfo();
}

export default DefaultAttachmentTypeInfo;
export const DefaultAttachment_TYPE = DefaultAttachmentTypeInfo.INSTANCE;