<script lang="ts">
import { onMounted, provide, ref } from "vue";

import type { double, int } from "@skeljs/core/src/lang/basetype";
import { getDefaultFieldRowProps } from "@skeljs/dba/src/ui/lily/defaults";

import { IdEntity_TYPE } from "../../concrete/IdEntityTypeInfo";
import Uom from "./Uom";
import { _Uom_stuff_TYPE } from "./_Uom_stuff_TypeInfo";

export const title = "Editor view of: Uom";
export interface Props {
}

</script>

<script setup lang="ts">
import FieldRow from "@skeljs/core/src/ui/FieldRow.vue";
import { FIELD_ROW_PROPS } from "@skeljs/core/src/ui/FieldRow.vue";
import RefEditor from "@skeljs/dba/src/ui/input/RefEditor.vue";
import FieldGroup from "@skeljs/dba/src/ui/lily/FieldGroup.vue";

import CoCodeFieldGroup from "../../concrete/CoCodeFieldGroup.vue";
import CoImagedFieldGroup from "../../concrete/CoImagedFieldGroup.vue";
import CoObjectFieldGroup from "../../concrete/CoObjectFieldGroup.vue";
import UomChooseDialog from "./UomChooseDialog.vue";

defineOptions({
    inheritAttrs: false
});

const model = defineModel<Uom>();

const props = withDefaults(defineProps<Props>(), {
});

const emit = defineEmits<{
    error: [message: string]
    change: [e: Event]
}>();

// property shortcuts

const meta = Uom.TYPE.property;
const fieldRowProps = getDefaultFieldRowProps({ labelWidth: '7rem' });
provide(FIELD_ROW_PROPS, fieldRowProps);

const rootElement = ref<HTMLElement>();
const uomChooseDialog = ref<InstanceType<typeof UomChooseDialog>>();
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
                <RefEditor :dialog="uomChooseDialog" v-model="model.standard" v-model:id="model.standardId" />
            </FieldRow>
        </FieldGroup>
    </div>
    <UomChooseDialog ref="uomChooseDialog" />
</template>

<style scoped lang="scss">
.entity-editor {
    padding: 0;
}
</style>
