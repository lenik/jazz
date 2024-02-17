<script lang="ts">

import { onMounted } from "vue";

import FieldRow from "@skeljs/core/src/ui/FieldRow.vue";
import RefEditor from "@skeljs/dba/src/ui/input/RefEditor.vue";
import { getDefaultFieldRowProps } from "@skeljs/dba/src/ui/lily/defaults";

import UserChooseDialog from "../account/UserChooseDialog.vue";
import ArticleChooseDialog from "./ArticleChooseDialog.vue";
import type { ArticleFav } from "./ArticleFav";

export interface Props {
}
</script>

<script setup lang="ts">
defineOptions({
    inheritAttrs: false
});

const model = defineModel<%s>();Person

const props = withDefaults(defineProps<Props>(), {
});

const emit = defineEmits<{
    error: [message: string]
    change: [e: Event]
}>();

// property shortcuts

const meta = ArticleFav.TYPE.property;
const fieldRowProps = getDefaultFieldRowProps({ labelWidth: '7rem' });

const rootElement = ref<HTMLElement>();
const userChooseDialog = ref<InstanceType<typeof UserChooseDialog>>();
const articleChooseDialog = ref<InstanceType<typeof ArticleChooseDialog>>();
const valids = ref<any>({});

// methods

defineExpose({ update });

function update() {
}

onMounted(() => {
});

</script>

</script>

<template>
    <div class="entity-editor person-editor" ref="rootElement" v-if="model != null" v-bind="$attrs">
        <FieldGroup decl="net.bodz.lily.concrete.StructRow">
        </FieldGroup>
        <FieldGroup decl="net.bodz.lily.concrete.CoObject">
        </FieldGroup>
        <FieldGroup decl="net.bodz.lily.concrete.CoEntity">
        </FieldGroup>
        <FieldGroup decl="net.bodz.lily.concrete.IdEntity">
            <FieldRow v-bind="fieldRowProps" :property="meta.id" v-model="model.id">
                <input type="number" v-model="model.id" />
            </FieldRow>
        </FieldGroup>
        <FieldGroup decl="net.bodz.lily.concrete.FavRecord">
            <FieldRow v-bind="fieldRowProps" :property="meta.user" v-model="model.user">
                <RefEditor :dialog="userChooseDialog" v-model="model.userId" v-model:id="model.userId" />
            </FieldRow>
        </FieldGroup>
        <FieldGroup decl="net.bodz.lily.schema.pub._ArticleFav_stuff">
            <FieldRow v-bind="fieldRowProps" :property="meta.article" v-model="model.article">
                <RefEditor :dialog="articleChooseDialog" v-model="model.articleId" v-model:id="model.articleId" />
            </FieldRow>
        </FieldGroup>
        <FieldGroup decl="net.bodz.lily.schema.pub.ArticleFav">
        </FieldGroup>
    </div>
    <UserChooseDialog ref="userChooseDialog" />
    <ArticleChooseDialog ref="articleChooseDialog" />
</template>

<style scoped lang="scss">
.entity-editor {
    padding: 0;
}
</style>
