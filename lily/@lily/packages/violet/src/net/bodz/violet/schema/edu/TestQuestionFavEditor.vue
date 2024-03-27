<script lang="ts">
import { onMounted, provide, ref } from "vue";

import type { long } from "@skeljs/core/src/lang/basetype";
import { getDefaultFieldRowProps } from "@skeljs/dba/src/ui/lily/defaults";
import IdEntity from "@lily/basic/src/net/bodz/lily/concrete/IdEntity";

import TestQuestionFav from "./TestQuestionFav";
import _TestQuestionFav_stuff from "./_TestQuestionFav_stuff";

export const title = "Editor view of: Test question fav";
export interface Props {
}

</script>

<script setup lang="ts">
import FieldRow from "@skeljs/core/src/ui/FieldRow.vue";
import { FIELD_ROW_PROPS } from "@skeljs/core/src/ui/FieldRow.vue";
import RefEditor from "@skeljs/dba/src/ui/input/RefEditor.vue";
import FieldGroup from "@skeljs/dba/src/ui/lily/FieldGroup.vue";
import FavRecordFieldGroup from "@lily/basic/src/net/bodz/lily/concrete/FavRecordFieldGroup.vue";

import TestQuestionChooseDialog from "./TestQuestionChooseDialog.vue";

defineOptions({
    inheritAttrs: false
});

const model = defineModel<TestQuestionFav>();

const props = withDefaults(defineProps<Props>(), {
});

const emit = defineEmits<{
    error: [message: string]
    change: [e: Event]
}>();

// property shortcuts

const meta = TestQuestionFav.TYPE.property;
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
        <FieldGroup :type="IdEntity.TYPE">
            <FieldRow :property="meta.id" v-model="model.id">
                <input type="number" v-model="model.id" disabled />
            </FieldRow>
        </FieldGroup>
        <FavRecordFieldGroup :meta="meta" v-model="model" />
        <FieldGroup :type="_TestQuestionFav_stuff.TYPE">
            <FieldRow :property="meta.testq" v-model="model.testq">
                <RefEditor :dialog="testQuestionChooseDialog" v-model="model.testq" v-model:id="model.testqId" />
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
