<script lang="ts">
import { onMounted, provide, ref } from "vue";

import type { long } from "skel01-core/src/lang/basetype";
import { getDefaultFieldRowProps } from "skel01-dba/src/ui/lily/defaults";

import { CoTalk_TYPE } from "../../concrete/CoTalkTypeInfo";
import { IdEntity_TYPE } from "../../concrete/IdEntityTypeInfo";
import ArticleTalk from "./ArticleTalk";
import { _ArticleTalk_stuff_TYPE } from "./_ArticleTalk_stuff_TypeInfo";

export const title = "Editor view of: Article talk";
export interface Props {
}

</script>

<script setup lang="ts">
import FieldRow from "skel01-core/src/ui/FieldRow.vue";
import { FIELD_ROW_PROPS } from "skel01-core/src/ui/FieldRow.vue";
import RefEditor from "skel01-dba/src/ui/input/RefEditor.vue";
import FieldGroup from "skel01-dba/src/ui/lily/FieldGroup.vue";

import CoEventFieldGroup from "../../concrete/CoEventFieldGroup.vue";
import CoMessageFieldGroup from "../../concrete/CoMessageFieldGroup.vue";
import CoObjectFieldGroup from "../../concrete/CoObjectFieldGroup.vue";
import StructRowFieldGroup from "../../concrete/StructRowFieldGroup.vue";
import ArticleChooseDialog from "./ArticleChooseDialog.vue";
import ArticleTalkChooseDialog from "./ArticleTalkChooseDialog.vue";

defineOptions({
    inheritAttrs: false
});

const model = defineModel<ArticleTalk>();

const props = withDefaults(defineProps<Props>(), {
});

const emit = defineEmits<{
    error: [message: string]
    change: [e: Event]
}>();

// property shortcuts

const meta = ArticleTalk.TYPE.property;
const fieldRowProps = getDefaultFieldRowProps({ labelWidth: '7rem' });
provide(FIELD_ROW_PROPS, fieldRowProps);

const rootElement = ref<HTMLElement>();
const articleTalkChooseDialog = ref<InstanceType<typeof ArticleTalkChooseDialog>>();
const articleChooseDialog = ref<InstanceType<typeof ArticleChooseDialog>>();
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
        <FieldGroup :type="CoTalk_TYPE">
            <FieldRow :property="meta.parent" v-model="model.parent">
                <RefEditor :dialog="articleTalkChooseDialog" v-model="model.parent" v-model:id="model.parentId" />
            </FieldRow>
        </FieldGroup>
        <FieldGroup :type="_ArticleTalk_stuff_TYPE">
            <FieldRow :property="meta.formArguments" v-model="model.formArguments">
                <input type="text" v-model="model.formArguments" />
            </FieldRow>
            <FieldRow :property="meta.article" v-model="model.article">
                <RefEditor :dialog="articleChooseDialog" v-model="model.article" v-model:id="model.articleId" />
            </FieldRow>
        </FieldGroup>
    </div>
    <ArticleTalkChooseDialog ref="articleTalkChooseDialog" />
    <ArticleChooseDialog ref="articleChooseDialog" />
</template>

<style scoped lang="scss">
.entity-editor {
    padding: 0;
}
</style>
