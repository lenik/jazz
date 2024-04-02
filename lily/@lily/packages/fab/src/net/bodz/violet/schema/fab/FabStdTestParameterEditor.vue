<script lang="ts">
import { onMounted, provide, ref } from "vue";

import type { JsonVariant } from "@skeljs/core/src/lang/bas-type";
import type { int } from "@skeljs/core/src/lang/basetype";
import { getDefaultFieldRowProps } from "@skeljs/dba/src/ui/lily/defaults";
import { IdEntity_TYPE } from "@lily/basic/src/net/bodz/lily/concrete/IdEntityTypeInfo";

import FabStdTestParameter from "./FabStdTestParameter";
import { _FabStdTestParameter_stuff_TYPE } from "./_FabStdTestParameter_stuff_TypeInfo";

export const title = "Editor view of: Fab std test parameter";
export interface Props {
}

</script>

<script setup lang="ts">
import FieldRow from "@skeljs/core/src/ui/FieldRow.vue";
import { FIELD_ROW_PROPS } from "@skeljs/core/src/ui/FieldRow.vue";
import JsonEditor from "@skeljs/core/src/ui/input/JsonEditor.vue";
import RefEditor from "@skeljs/dba/src/ui/input/RefEditor.vue";
import FieldGroup from "@skeljs/dba/src/ui/lily/FieldGroup.vue";
import StructRowFieldGroup from "@lily/basic/src/net/bodz/lily/concrete/StructRowFieldGroup.vue";

import FabStdTestChooseDialog from "./FabStdTestChooseDialog.vue";

defineOptions({
    inheritAttrs: false
});

const model = defineModel<FabStdTestParameter>();

const props = withDefaults(defineProps<Props>(), {
});

const emit = defineEmits<{
    error: [message: string]
    change: [e: Event]
}>();

// property shortcuts

const meta = FabStdTestParameter.TYPE.property;
const fieldRowProps = getDefaultFieldRowProps({ labelWidth: '7rem' });
provide(FIELD_ROW_PROPS, fieldRowProps);

const rootElement = ref<HTMLElement>();
const fabStdTestChooseDialog = ref<InstanceType<typeof FabStdTestChooseDialog>>();
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
        <FieldGroup :type="IdEntity_TYPE">
            <FieldRow :property="meta.id" v-model="model.id">
                <input type="number" v-model="model.id" disabled />
            </FieldRow>
        </FieldGroup>
        <FieldGroup :type="_FabStdTestParameter_stuff_TYPE">
            <FieldRow :property="meta.required" v-model="model.required">
                <input type="checkbox" v-model="model.required" />
            </FieldRow>
            <FieldRow :property="meta.properties" v-model="model.properties">
                <JsonEditor v-model="model.properties" />
            </FieldRow>
            <FieldRow :property="meta.expected" v-model="model.expected">
                <input type="text" v-model="model.expected" />
            </FieldRow>
            <FieldRow :property="meta.test" v-model="model.test">
                <RefEditor :dialog="fabStdTestChooseDialog" v-model="model.test" v-model:id="model.testId" />
            </FieldRow>
        </FieldGroup>
    </div>
    <FabStdTestChooseDialog ref="fabStdTestChooseDialog" />
</template>

<style scoped lang="scss">
.entity-editor {
    padding: 0;
}
</style>
