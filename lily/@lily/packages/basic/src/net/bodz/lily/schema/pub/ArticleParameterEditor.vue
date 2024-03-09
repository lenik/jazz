<script lang="ts">
import { onMounted, ref } from "vue";

import type { Timestamp, double, int } from "@skeljs/core/src/lang/basetype";
import { getDefaultFieldRowProps } from "@skeljs/dba/src/ui/lily/defaults";

import StructRow from "../../concrete/StructRow";
import ArticleParameter from "./ArticleParameter";
import _ArticleParameter_stuff from "./_ArticleParameter_stuff";

export const title = "Editor view of: Article parameter";
export interface Props {
}

</script>

<script setup lang="ts">
import FieldRow from "@skeljs/core/src/ui/FieldRow.vue";
import RefEditor from "@skeljs/dba/src/ui/input/RefEditor.vue";
import FieldGroup from "@skeljs/dba/src/ui/lily/FieldGroup.vue";

import ArticleChooseDialog from "./ArticleChooseDialog.vue";
import ArticleParameterTypeChooseDialog from "./ArticleParameterTypeChooseDialog.vue";

defineOptions({
    inheritAttrs: false
});

const model = defineModel<ArticleParameter>();

const props = withDefaults(defineProps<Props>(), {
});

const emit = defineEmits<{
    error: [message: string]
    change: [e: Event]
}>();

// property shortcuts

const meta = ArticleParameter.TYPE.property;
const fieldRowProps = getDefaultFieldRowProps({ labelWidth: '7rem' });

const rootElement = ref<HTMLElement>();
const articleChooseDialog = ref<InstanceType<typeof ArticleChooseDialog>>();
const articleParameterTypeChooseDialog = ref<InstanceType<typeof ArticleParameterTypeChooseDialog>>();
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
                <input type="datetime" v-model="model.creationDate" />
            </FieldRow>
            <FieldRow v-bind="fieldRowProps" :property="meta.lastModifiedDate" v-model="model.lastModifiedDate">
                <input type="datetime" v-model="model.lastModifiedDate" />
            </FieldRow>
            <FieldRow v-bind="fieldRowProps" :property="meta.version" v-model="model.version">
                <input type="number" v-model="model.version" />
            </FieldRow>
        </FieldGroup>
        <FieldGroup :type="_ArticleParameter_stuff.TYPE">
            <FieldRow v-bind="fieldRowProps" :property="meta.id" v-model="model.id">
                <input type="number" v-model="model.id" />
            </FieldRow>
            <FieldRow v-bind="fieldRowProps" :property="meta.ival" v-model="model.ival">
                <input type="number" v-model="model.ival" />
            </FieldRow>
            <FieldRow v-bind="fieldRowProps" :property="meta.fval" v-model="model.fval">
                <input type="number" v-model="model.fval" />
            </FieldRow>
            <FieldRow v-bind="fieldRowProps" :property="meta.sval" v-model="model.sval">
                <input type="text" v-model="model.sval" />
            </FieldRow>
            <FieldRow v-bind="fieldRowProps" :property="meta.article" v-model="model.article">
                <RefEditor :dialog="articleChooseDialog" v-model="model.article" v-model:id="model.articleId" />
            </FieldRow>
            <FieldRow v-bind="fieldRowProps" :property="meta.parameter" v-model="model.parameter">
                <RefEditor :dialog="articleParameterTypeChooseDialog" v-model="model.parameter" v-model:id="model.parameterId" />
            </FieldRow>
        </FieldGroup>
    </div>
    <ArticleChooseDialog ref="articleChooseDialog" />
    <ArticleParameterTypeChooseDialog ref="articleParameterTypeChooseDialog" />
</template>

<style scoped lang="scss">
.entity-editor {
    padding: 0;
}
</style>
