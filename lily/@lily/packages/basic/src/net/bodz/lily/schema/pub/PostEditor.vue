<script lang="ts">
import { onMounted, provide, ref } from "vue";

import type { JsonVariant } from "skel01-core/src/lang/bas-type";
import type { int, long } from "skel01-core/src/lang/basetype";
import { getDefaultFieldRowProps } from "skel01-dba/src/ui/lily/defaults";

import { IdEntity_TYPE } from "../../concrete/IdEntityTypeInfo";
import Post from "./Post";
import { _Post_stuff_TYPE } from "./_Post_stuff_TypeInfo";

export const title = "Editor view of: Post";
export interface Props {
}

</script>

<script setup lang="ts">
import FieldRow from "skel01-core/src/ui/FieldRow.vue";
import { FIELD_ROW_PROPS } from "skel01-core/src/ui/FieldRow.vue";
import JsonEditor from "skel01-core/src/ui/input/JsonEditor.vue";
import RefEditor from "skel01-dba/src/ui/input/RefEditor.vue";
import FieldGroup from "skel01-dba/src/ui/lily/FieldGroup.vue";

import CoEventFieldGroup from "../../concrete/CoEventFieldGroup.vue";
import CoMessageFieldGroup from "../../concrete/CoMessageFieldGroup.vue";
import CoObjectFieldGroup from "../../concrete/CoObjectFieldGroup.vue";
import StructRowFieldGroup from "../../concrete/StructRowFieldGroup.vue";
import PostCategoryChooseDialog from "./PostCategoryChooseDialog.vue";
import PostChooseDialog from "./PostChooseDialog.vue";

defineOptions({
    inheritAttrs: false
});

const model = defineModel<Post>();

const props = withDefaults(defineProps<Props>(), {
});

const emit = defineEmits<{
    error: [message: string]
    change: [e: Event]
}>();

// property shortcuts

const meta = Post.TYPE.property;
const fieldRowProps = getDefaultFieldRowProps({ labelWidth: '7rem' });
provide(FIELD_ROW_PROPS, fieldRowProps);

const rootElement = ref<HTMLElement>();
const postChooseDialog = ref<InstanceType<typeof PostChooseDialog>>();
const postCategoryChooseDialog = ref<InstanceType<typeof PostCategoryChooseDialog>>();
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
        <CoEventFieldGroup :meta="meta" v-model="model" />
        <CoMessageFieldGroup :meta="meta" v-model="model" />
        <FieldGroup :type="_Post_stuff_TYPE">
            <FieldRow :property="meta.formArguments" v-model="model.formArguments">
                <input type="text" v-model="model.formArguments" />
            </FieldRow>
            <FieldRow :property="meta.favCount" v-model="model.favCount">
                <input type="number" v-model="model.favCount" />
            </FieldRow>
            <FieldRow :property="meta.voteCount" v-model="model.voteCount">
                <input type="number" v-model="model.voteCount" />
            </FieldRow>
            <FieldRow :property="meta.hateCount" v-model="model.hateCount">
                <input type="number" v-model="model.hateCount" />
            </FieldRow>
            <FieldRow :property="meta.messageCount" v-model="model.messageCount">
                <input type="number" v-model="model.messageCount" />
            </FieldRow>
            <FieldRow :property="meta.plugins" v-model="model.plugins">
                <JsonEditor v-model="model.plugins" />
            </FieldRow>
            <FieldRow :property="meta.parent" v-model="model.parent">
                <RefEditor :dialog="postChooseDialog" v-model="model.parent" v-model:id="model.parentId" />
            </FieldRow>
            <FieldRow :property="meta.category" v-model="model.category">
                <RefEditor :dialog="postCategoryChooseDialog" v-model="model.category" v-model:id="model.categoryId" />
            </FieldRow>
        </FieldGroup>
    </div>
    <PostChooseDialog ref="postChooseDialog" />
    <PostCategoryChooseDialog ref="postCategoryChooseDialog" />
</template>

<style scoped lang="scss">
.entity-editor {
    padding: 0;
}
</style>
