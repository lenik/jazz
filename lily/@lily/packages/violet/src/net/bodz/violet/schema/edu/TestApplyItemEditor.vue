<script lang="ts">
import { onMounted, provide, ref } from "vue";

import type { double, int, long } from "@skeljs/core/src/lang/basetype";
import { getDefaultFieldRowProps } from "@skeljs/dba/src/ui/lily/defaults";
import { IdEntity_TYPE } from "@lily/basic/src/net/bodz/lily/concrete/IdEntityTypeInfo";

import TestApplyItem from "./TestApplyItem";
import { _TestApplyItem_stuff_TYPE } from "./_TestApplyItem_stuff_TypeInfo";

export const title = "Editor view of: Test apply item";
export interface Props {
}

</script>

<script setup lang="ts">
import FieldRow from "@skeljs/core/src/ui/FieldRow.vue";
import { FIELD_ROW_PROPS } from "@skeljs/core/src/ui/FieldRow.vue";
import RefEditor from "@skeljs/dba/src/ui/input/RefEditor.vue";
import FieldGroup from "@skeljs/dba/src/ui/lily/FieldGroup.vue";
import CoObjectFieldGroup from "@lily/basic/src/net/bodz/lily/concrete/CoObjectFieldGroup.vue";
import StructRowFieldGroup from "@lily/basic/src/net/bodz/lily/concrete/StructRowFieldGroup.vue";

import TestApplyChooseDialog from "./TestApplyChooseDialog.vue";
import TestQuestionChooseDialog from "./TestQuestionChooseDialog.vue";

defineOptions({
    inheritAttrs: false
});

const model = defineModel<TestApplyItem>();

const props = withDefaults(defineProps<Props>(), {
});

const emit = defineEmits<{
    error: [message: string]
    change: [e: Event]
}>();

// property shortcuts

const meta = TestApplyItem.TYPE.property;
const fieldRowProps = getDefaultFieldRowProps({ labelWidth: '7rem' });
provide(FIELD_ROW_PROPS, fieldRowProps);

const rootElement = ref<HTMLElement>();
const testApplyChooseDialog = ref<InstanceType<typeof TestApplyChooseDialog>>();
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
        <FieldGroup :type="_TestApplyItem_stuff_TYPE">
            <FieldRow :property="meta.answer" v-model="model.answer">
                <input type="number" v-model="model.answer" />
            </FieldRow>
            <FieldRow :property="meta.anstext" v-model="model.anstext">
                <input type="text" v-model="model.anstext" />
            </FieldRow>
            <FieldRow :property="meta.score" v-model="model.score">
                <input type="number" v-model="model.score" />
            </FieldRow>
            <FieldRow :property="meta.waittime" v-model="model.waittime">
                <input type="number" v-model="model.waittime" />
            </FieldRow>
            <FieldRow :property="meta.apply" v-model="model.apply">
                <RefEditor :dialog="testApplyChooseDialog" v-model="model.apply" v-model:id="model.applyId" />
            </FieldRow>
            <FieldRow :property="meta.question" v-model="model.question">
                <RefEditor :dialog="testQuestionChooseDialog" v-model="model.question" v-model:id="model.questionId" />
            </FieldRow>
        </FieldGroup>
    </div>
    <TestApplyChooseDialog ref="testApplyChooseDialog" />
    <TestQuestionChooseDialog ref="testQuestionChooseDialog" />
</template>

<style scoped lang="scss">
.entity-editor {
    padding: 0;
}
</style>
