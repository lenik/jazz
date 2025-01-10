<script lang="ts">
import { onMounted, provide, ref } from "vue";

import type { int, long } from "skel01-core/src/lang/basetype";
import { getDefaultFieldRowProps } from "skel01-dba/src/ui/lily/defaults";

import { IdEntity_TYPE } from "../../concrete/IdEntityTypeInfo";
import ArticleBackref from "./ArticleBackref";
import { _ArticleBackref_stuff_TYPE } from "./_ArticleBackref_stuff_TypeInfo";

export const title = "Editor view of: Article backref";
export interface Props {
}

</script>

<script setup lang="ts">
import FieldRow from "skel01-core/src/ui/FieldRow.vue";
import { FIELD_ROW_PROPS } from "skel01-core/src/ui/FieldRow.vue";
import RefEditor from "skel01-dba/src/ui/input/RefEditor.vue";
import FieldGroup from "skel01-dba/src/ui/lily/FieldGroup.vue";

import CoObjectFieldGroup from "../../concrete/CoObjectFieldGroup.vue";
import StructRowFieldGroup from "../../concrete/StructRowFieldGroup.vue";
import ExternalSiteChooseDialog from "../inet/ExternalSiteChooseDialog.vue";
import ArticleChooseDialog from "./ArticleChooseDialog.vue";

defineOptions({
    inheritAttrs: false
});

const model = defineModel<ArticleBackref>();

const props = withDefaults(defineProps<Props>(), {
});

const emit = defineEmits<{
    error: [message: string]
    change: [e: Event]
}>();

// property shortcuts

const meta = ArticleBackref.TYPE.property;
const fieldRowProps = getDefaultFieldRowProps({ labelWidth: '7rem' });
provide(FIELD_ROW_PROPS, fieldRowProps);

const rootElement = ref<HTMLElement>();
const articleChooseDialog = ref<InstanceType<typeof ArticleChooseDialog>>();
const externalSiteChooseDialog = ref<InstanceType<typeof ExternalSiteChooseDialog>>();
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
        <FieldGroup :type="_ArticleBackref_stuff_TYPE">
            <FieldRow :property="meta.key" v-model="model.key">
                <input type="text" v-model="model.key" />
            </FieldRow>
            <FieldRow :property="meta.value" v-model="model.value">
                <input type="number" v-model="model.value" />
            </FieldRow>
            <FieldRow :property="meta.article" v-model="model.article">
                <RefEditor :dialog="articleChooseDialog" v-model="model.article" v-model:id="model.articleId" />
            </FieldRow>
            <FieldRow :property="meta.site" v-model="model.site">
                <RefEditor :dialog="externalSiteChooseDialog" v-model="model.site" v-model:id="model.siteId" />
            </FieldRow>
        </FieldGroup>
    </div>
    <ArticleChooseDialog ref="articleChooseDialog" />
    <ExternalSiteChooseDialog ref="externalSiteChooseDialog" />
</template>

<style scoped lang="scss">
.entity-editor {
    padding: 0;
}
</style>
