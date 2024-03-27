<script lang="ts">
import { onMounted, provide, ref } from "vue";

import type { long } from "@skeljs/core/src/lang/basetype";
import { getDefaultFieldRowProps } from "@skeljs/dba/src/ui/lily/defaults";
import IdEntity from "@lily/basic/src/net/bodz/lily/concrete/IdEntity";

import DiaryReview from "./DiaryReview";
import _DiaryReview_stuff from "./_DiaryReview_stuff";

export const title = "Editor view of: Diary review";
export interface Props {
}

</script>

<script setup lang="ts">
import FieldRow from "@skeljs/core/src/ui/FieldRow.vue";
import { FIELD_ROW_PROPS } from "@skeljs/core/src/ui/FieldRow.vue";
import RefEditor from "@skeljs/dba/src/ui/input/RefEditor.vue";
import FieldGroup from "@skeljs/dba/src/ui/lily/FieldGroup.vue";
import CoEventFieldGroup from "@lily/basic/src/net/bodz/lily/concrete/CoEventFieldGroup.vue";
import CoMessageFieldGroup from "@lily/basic/src/net/bodz/lily/concrete/CoMessageFieldGroup.vue";
import CoObjectFieldGroup from "@lily/basic/src/net/bodz/lily/concrete/CoObjectFieldGroup.vue";
import StructRowFieldGroup from "@lily/basic/src/net/bodz/lily/concrete/StructRowFieldGroup.vue";

import DiaryChooseDialog from "./DiaryChooseDialog.vue";
import DiaryReviewChooseDialog from "./DiaryReviewChooseDialog.vue";

defineOptions({
    inheritAttrs: false
});

const model = defineModel<DiaryReview>();

const props = withDefaults(defineProps<Props>(), {
});

const emit = defineEmits<{
    error: [message: string]
    change: [e: Event]
}>();

// property shortcuts

const meta = DiaryReview.TYPE.property;
const fieldRowProps = getDefaultFieldRowProps({ labelWidth: '7rem' });
provide(FIELD_ROW_PROPS, fieldRowProps);

const rootElement = ref<HTMLElement>();
const diaryChooseDialog = ref<InstanceType<typeof DiaryChooseDialog>>();
const diaryReviewChooseDialog = ref<InstanceType<typeof DiaryReviewChooseDialog>>();
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
        <FieldGroup :type="IdEntity.TYPE">
            <FieldRow :property="meta.id" v-model="model.id">
                <input type="number" v-model="model.id" disabled />
            </FieldRow>
        </FieldGroup>
        <CoEventFieldGroup :meta="meta" v-model="model" />
        <CoMessageFieldGroup :meta="meta" v-model="model" />
        <FieldGroup :type="_DiaryReview_stuff.TYPE">
            <FieldRow :property="meta.formArguments" v-model="model.formArguments">
                <input type="text" v-model="model.formArguments" />
            </FieldRow>
            <FieldRow :property="meta.diary" v-model="model.diary">
                <RefEditor :dialog="diaryChooseDialog" v-model="model.diary" v-model:id="model.diaryId" />
            </FieldRow>
            <FieldRow :property="meta.parent" v-model="model.parent">
                <RefEditor :dialog="diaryReviewChooseDialog" v-model="model.parent" v-model:id="model.parentId" />
            </FieldRow>
        </FieldGroup>
    </div>
    <DiaryChooseDialog ref="diaryChooseDialog" />
    <DiaryReviewChooseDialog ref="diaryReviewChooseDialog" />
</template>

<style scoped lang="scss">
.entity-editor {
    padding: 0;
}
</style>
