<script lang="ts">
import { onMounted, provide, ref } from "vue";

import type { JsonVariant } from "skel01-core/src/lang/bas-type";
import type { double, int } from "skel01-core/src/lang/basetype";
import { getDefaultFieldRowProps } from "skel01-dba/src/ui/lily/defaults";

import { IdEntity_TYPE } from "../../concrete/IdEntityTypeInfo";
import UomRow from "./UomRow";
import { _UomRow_stuff_TYPE } from "./_UomRow_stuff_TypeInfo";
import { _Uom_stuff_TYPE } from "./_Uom_stuff_TypeInfo";

export const title = "Editor view of: Uom row";
export interface Props {
}

</script>

<script setup lang="ts">
import FieldRow from "skel01-core/src/ui/FieldRow.vue";
import { FIELD_ROW_PROPS } from "skel01-core/src/ui/FieldRow.vue";
import JsonEditor from "skel01-core/src/ui/input/JsonEditor.vue";
import RefEditor from "skel01-dba/src/ui/input/RefEditor.vue";
import FieldGroup from "skel01-dba/src/ui/lily/FieldGroup.vue";

import CoCodeFieldGroup from "../../concrete/CoCodeFieldGroup.vue";
import CoImagedFieldGroup from "../../concrete/CoImagedFieldGroup.vue";
import CoObjectFieldGroup from "../../concrete/CoObjectFieldGroup.vue";
import UomRowChooseDialog from "./UomRowChooseDialog.vue";

defineOptions({
    inheritAttrs: false
});

const model = defineModel<UomRow>();

const props = withDefaults(defineProps<Props>(), {
});

const emit = defineEmits<{
    error: [message: string]
    change: [e: Event]
}>();

// property shortcuts

const meta = UomRow.TYPE.property;
const fieldRowProps = getDefaultFieldRowProps({ labelWidth: '7rem' });
provide(FIELD_ROW_PROPS, fieldRowProps);

const rootElement = ref<HTMLElement>();
const uomRowChooseDialog = ref<InstanceType<typeof UomRowChooseDialog>>();
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
        <CoObjectFieldGroup :meta="meta" v-model="model" />
        <FieldGroup :type="IdEntity_TYPE">
            <FieldRow :property="meta.id" v-model="model.id">
                <input type="number" v-model="model.id" disabled />
            </FieldRow>
        </FieldGroup>
        <CoImagedFieldGroup :meta="meta" v-model="model" />
        <CoCodeFieldGroup :meta="meta" v-model="model" />
        <FieldGroup :type="_Uom_stuff_TYPE">
            <FieldRow :property="meta.property" v-model="model.property">
                <input type="text" v-model="model.property" />
            </FieldRow>
            <FieldRow :property="meta.scale" v-model="model.scale">
                <input type="number" v-model="model.scale" />
            </FieldRow>
            <FieldRow :property="meta.standard" v-model="model.standard">
                <RefEditor :dialog="uomRowChooseDialog" v-model="model.standard" v-model:id="model.standardId" />
            </FieldRow>
        </FieldGroup>
        <FieldGroup :type="_UomRow_stuff_TYPE">
            <FieldRow :property="meta.files" v-model="model.files">
                <JsonEditor v-model="model.files" />
            </FieldRow>
        </FieldGroup>
    </div>
    <UomRowChooseDialog ref="uomRowChooseDialog" />
</template>

<style scoped lang="scss">
.entity-editor {
    padding: 0;
}
</style>
