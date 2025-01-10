<script lang="ts">
import { onMounted, provide, ref } from "vue";

import type { int } from "skel01-core/src/lang/basetype";
import { getDefaultFieldRowProps } from "skel01-dba/src/ui/lily/defaults";

import { IdEntity_TYPE } from "../../concrete/IdEntityTypeInfo";
import ArticleTag from "./ArticleTag";
import { _ArticleTag_stuff_TYPE } from "./_ArticleTag_stuff_TypeInfo";

export const title = "Editor view of: Article tag";
export interface Props {
}

</script>

<script setup lang="ts">
import FieldRow from "skel01-core/src/ui/FieldRow.vue";
import { FIELD_ROW_PROPS } from "skel01-core/src/ui/FieldRow.vue";
import RefEditor from "skel01-dba/src/ui/input/RefEditor.vue";
import FieldGroup from "skel01-dba/src/ui/lily/FieldGroup.vue";

import StructRowFieldGroup from "../../concrete/StructRowFieldGroup.vue";
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
provide(FIELD_ROW_PROPS, fieldRowProps);

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
        <StructRowFieldGroup :meta="meta" v-model="model" />
        <FieldGroup :type="IdEntity_TYPE">
            <FieldRow :property="meta.id" v-model="model.id">
                <input type="number" v-model="model.id" disabled />
            </FieldRow>
        </FieldGroup>
        <FieldGroup :type="_ArticleTag_stuff_TYPE">
            <FieldRow :property="meta.article" v-model="model.article">
                <RefEditor :dialog="articleChooseDialog" v-model="model.article" v-model:id="model.articleId" />
            </FieldRow>
            <FieldRow :property="meta.tag" v-model="model.tag">
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
