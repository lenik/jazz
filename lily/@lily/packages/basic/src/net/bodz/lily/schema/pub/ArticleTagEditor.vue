<script lang="ts">
import { onMounted, ref } from "vue";

import type { integer } from "@skeljs/core/src/lang/type";
import { getDefaultFieldRowProps } from "@skeljs/dba/src/ui/lily/defaults";

import StructRow from "../../concrete/StructRow";
import ArticleTag from "./ArticleTag";
import _ArticleTag_stuff from "./_ArticleTag_stuff";

export const title = "Editor view of: Article tag";
export interface Props {
}

</script>

<script setup lang="ts">
import FieldRow from "@skeljs/core/src/ui/FieldRow.vue";
import RefEditor from "@skeljs/dba/src/ui/input/RefEditor.vue";
import FieldGroup from "@skeljs/dba/src/ui/lily/FieldGroup.vue";

import ArticleChooseDialog from "./ArticleChooseDialog.vue";
import ArticleTagTypeChooseDialog from "./ArticleTagTypeChooseDialog.vue";

defineOptions({
    inheritAttrs: false
});

const model = defineModel<ArticleTag>();

const props = withDefaults(defineProps<Props>(), {
});

const emit = defineEmits<{
    error: [message: string]
    change: [e: Event]
}>();

// property shortcuts

const meta = ArticleTag.TYPE.property;
const fieldRowProps = getDefaultFieldRowProps({ labelWidth: '7rem' });

const rootElement = ref<HTMLElement>();
const articleChooseDialog = ref<InstanceType<typeof ArticleChooseDialog>>();
const articleTagTypeChooseDialog = ref<InstanceType<typeof ArticleTagTypeChooseDialog>>();
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
        <FieldGroup :type="StructRow.TYPE">
            <FieldRow v-bind="fieldRowProps" :property="meta.creationDate" v-model="model.creationDate">
                <input type="date" v-model="model.creationDate" />
            </FieldRow>
            <FieldRow v-bind="fieldRowProps" :property="meta.lastModifiedDate" v-model="model.lastModifiedDate">
                <input type="date" v-model="model.lastModifiedDate" />
            </FieldRow>
            <FieldRow v-bind="fieldRowProps" :property="meta.version" v-model="model.version">
                <input type="number" v-model="model.version" />
            </FieldRow>
        </FieldGroup>
        <FieldGroup :type="_ArticleTag_stuff.TYPE">
            <FieldRow v-bind="fieldRowProps" :property="meta.id" v-model="model.id">
                <input type="number" v-model="model.id" />
            </FieldRow>
            <FieldRow v-bind="fieldRowProps" :property="meta.article" v-model="model.article">
                <RefEditor :dialog="articleChooseDialog" v-model="model.article" v-model:id="model.articleId" />
            </FieldRow>
            <FieldRow v-bind="fieldRowProps" :property="meta.tag" v-model="model.tag">
                <RefEditor :dialog="articleTagTypeChooseDialog" v-model="model.tag" v-model:id="model.tagId" />
            </FieldRow>
        </FieldGroup>
    </div>
    <ArticleChooseDialog ref="articleChooseDialog" />
    <ArticleTagTypeChooseDialog ref="articleTagTypeChooseDialog" />
</template>

<style scoped lang="scss">
.entity-editor {
    padding: 0;
}
</style>
