<script lang="ts">
import { onMounted, provide, ref } from "vue";

import type { int } from "@skeljs/core/src/lang/basetype";
import { getDefaultFieldRowProps } from "@skeljs/dba/src/ui/lily/defaults";

import { IdEntity_TYPE } from "../../concrete/IdEntityTypeInfo";
import ParameterValue from "./ParameterValue";
import { _ParameterValue_stuff_TYPE } from "./_ParameterValue_stuff_TypeInfo";

export const title = "Editor view of: Parameter value";
export interface Props {
}

</script>

<script setup lang="ts">
import FieldRow from "@skeljs/core/src/ui/FieldRow.vue";
import { FIELD_ROW_PROPS } from "@skeljs/core/src/ui/FieldRow.vue";
import RefEditor from "@skeljs/dba/src/ui/input/RefEditor.vue";
import FieldGroup from "@skeljs/dba/src/ui/lily/FieldGroup.vue";

import CoCodeFieldGroup from "../../concrete/CoCodeFieldGroup.vue";
import CoObjectFieldGroup from "../../concrete/CoObjectFieldGroup.vue";
import StructRowFieldGroup from "../../concrete/StructRowFieldGroup.vue";
import ParameterDefChooseDialog from "./ParameterDefChooseDialog.vue";

defineOptions({
    inheritAttrs: false
});

const model = defineModel<ParameterValue>();

const props = withDefaults(defineProps<Props>(), {
});

const emit = defineEmits<{
    error: [message: string]
    change: [e: Event]
}>();

// property shortcuts

const meta = ParameterValue.TYPE.property;
const fieldRowProps = getDefaultFieldRowProps({ labelWidth: '7rem' });
provide(FIELD_ROW_PROPS, fieldRowProps);

const rootElement = ref<HTMLElement>();
const parameterDefChooseDialog = ref<InstanceType<typeof ParameterDefChooseDialog>>();
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
        <CoCodeFieldGroup :meta="meta" v-model="model" />
        <FieldGroup :type="_ParameterValue_stuff_TYPE">
            <FieldRow :property="meta.val" v-model="model.val">
                <input type="text" v-model="model.val" />
            </FieldRow>
            <FieldRow :property="meta.parameter" v-model="model.parameter">
                <RefEditor :dialog="parameterDefChooseDialog" v-model="model.parameter" v-model:id="model.parameterId" />
            </FieldRow>
        </FieldGroup>
    </div>
    <ParameterDefChooseDialog ref="parameterDefChooseDialog" />
</template>

<style scoped lang="scss">
.entity-editor {
    padding: 0;
}
</style>
