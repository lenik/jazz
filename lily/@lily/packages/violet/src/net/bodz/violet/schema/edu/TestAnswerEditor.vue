<script lang="ts">
import { onMounted, provide, ref } from "vue";

import type { long } from "skel01-core/src/lang/basetype";
import { getDefaultFieldRowProps } from "skel01-dba/src/ui/lily/defaults";
import { IdEntity_TYPE } from "@lily/basic/src/net/bodz/lily/concrete/IdEntityTypeInfo";

import TestAnswer from "./TestAnswer";
import { _TestAnswer_stuff_TYPE } from "./_TestAnswer_stuff_TypeInfo";

export const title = "Editor view of: Test answer";
export interface Props {
}

</script>

<script setup lang="ts">
import FieldRow from "skel01-core/src/ui/FieldRow.vue";
import { FIELD_ROW_PROPS } from "skel01-core/src/ui/FieldRow.vue";
import RefEditor from "skel01-dba/src/ui/input/RefEditor.vue";
import FieldGroup from "skel01-dba/src/ui/lily/FieldGroup.vue";
import CoImagedEventFieldGroup from "@lily/basic/src/net/bodz/lily/concrete/CoImagedEventFieldGroup.vue";
import CoObjectFieldGroup from "@lily/basic/src/net/bodz/lily/concrete/CoObjectFieldGroup.vue";
import StructRowFieldGroup from "@lily/basic/src/net/bodz/lily/concrete/StructRowFieldGroup.vue";

import TestQuestionChooseDialog from "./TestQuestionChooseDialog.vue";

defineOptions({
    inheritAttrs: false
});

const model = defineModel<TestAnswer>();

const props = withDefaults(defineProps<Props>(), {
});

const emit = defineEmits<{
    error: [message: string]
    change: [e: Event]
}>();

// property shortcuts

const meta = TestAnswer.TYPE.property;
const fieldRowProps = getDefaultFieldRowProps({ labelWidth: '7rem' });
provide(FIELD_ROW_PROPS, fieldRowProps);

const rootElement = ref<HTMLElement>();
const testQuestionChooseDialog = ref<InstanceType<typeof TestQuestionChooseDialog>>();
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
        <CoImagedEventFieldGroup :meta="meta" v-model="model" />
        <FieldGroup :type="_TestAnswer_stuff_TYPE">
            <FieldRow :property="meta.valid" v-model="model.valid">
                <input type="checkbox" v-model="model.valid" />
            </FieldRow>
            <FieldRow :property="meta.question" v-model="model.question">
                <RefEditor :dialog="testQuestionChooseDialog" v-model="model.question" v-model:id="model.questionId" />
            </FieldRow>
        </FieldGroup>
    </div>
    <TestQuestionChooseDialog ref="testQuestionChooseDialog" />
</template>

<style scoped lang="scss">
.entity-editor {
    padding: 0;
}
</style>
