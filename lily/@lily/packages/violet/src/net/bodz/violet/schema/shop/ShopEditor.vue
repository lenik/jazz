<script lang="ts">
import { onMounted, provide, ref } from "vue";

import type { int } from "skel01-core/src/lang/basetype";
import { getDefaultFieldRowProps } from "skel01-dba/src/ui/lily/defaults";
import { IdEntity_TYPE } from "lily-basic/src/net/bodz/lily/concrete/IdEntityTypeInfo";

import Shop from "./Shop";
import { _Shop_stuff_TYPE } from "./_Shop_stuff_TypeInfo";

export const title = "Editor view of: Shop";
export interface Props {
}

</script>

<script setup lang="ts">
import FieldRow from "skel01-core/src/ui/FieldRow.vue";
import { FIELD_ROW_PROPS } from "skel01-core/src/ui/FieldRow.vue";
import RefEditor from "skel01-dba/src/ui/input/RefEditor.vue";
import FieldGroup from "skel01-dba/src/ui/lily/FieldGroup.vue";
import CoImagedFieldGroup from "lily-basic/src/net/bodz/lily/concrete/CoImagedFieldGroup.vue";
import CoObjectFieldGroup from "lily-basic/src/net/bodz/lily/concrete/CoObjectFieldGroup.vue";
import StructRowFieldGroup from "lily-basic/src/net/bodz/lily/concrete/StructRowFieldGroup.vue";
import OrganizationChooseDialog from "lily-basic/src/net/bodz/lily/schema/contact/OrganizationChooseDialog.vue";
import PersonChooseDialog from "lily-basic/src/net/bodz/lily/schema/contact/PersonChooseDialog.vue";

defineOptions({
    inheritAttrs: false
});

const model = defineModel<Shop>();

const props = withDefaults(defineProps<Props>(), {
});

const emit = defineEmits<{
    error: [message: string]
    change: [e: Event]
}>();

// property shortcuts

const meta = Shop.TYPE.property;
const fieldRowProps = getDefaultFieldRowProps({ labelWidth: '7rem' });
provide(FIELD_ROW_PROPS, fieldRowProps);

const rootElement = ref<HTMLElement>();
const organizationChooseDialog = ref<InstanceType<typeof OrganizationChooseDialog>>();
const personChooseDialog = ref<InstanceType<typeof PersonChooseDialog>>();
const valids = ref<any>({});

// methods

defineExpose({ update });

function update() {
}

onMounted(() => {
});

</script>

<template>
    <div class="entity-editor person-editor" ref="rootElement" v-if="model != null" v-bind="$attrs">
        <StructRowFieldGroup :meta="meta" v-model="model" />
        <CoObjectFieldGroup :meta="meta" v-model="model" />
        <FieldGroup :type="IdEntity_TYPE">
            <FieldRow :property="meta.id" v-model="model.id">
                <input type="number" v-model="model.id" disabled />
            </FieldRow>
        </FieldGroup>
        <CoImagedFieldGroup :meta="meta" v-model="model" />
        <FieldGroup :type="_Shop_stuff_TYPE">
            <FieldRow :property="meta.code" v-model="model.code">
                <input type="text" v-model="model.code" />
            </FieldRow>
            <FieldRow :property="meta.hydm" v-model="model.hydm">
                <input type="number" v-model="model.hydm" />
            </FieldRow>
            <FieldRow :property="meta.supplierOrg" v-model="model.supplierOrg">
                <RefEditor :dialog="organizationChooseDialog" v-model="model.supplierOrg" v-model:id="model.supplierOrgId" />
            </FieldRow>
            <FieldRow :property="meta.supplier" v-model="model.supplier">
                <RefEditor :dialog="personChooseDialog" v-model="model.supplier" v-model:id="model.supplierId" />
            </FieldRow>
        </FieldGroup>
    </div>
    <OrganizationChooseDialog ref="organizationChooseDialog" />
    <PersonChooseDialog ref="personChooseDialog" />
</template>

<style scoped lang="scss">
.entity-editor {
    padding: 0;
}
</style>
