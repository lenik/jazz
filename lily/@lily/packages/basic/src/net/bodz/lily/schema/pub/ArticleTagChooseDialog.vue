<script lang="ts">
import { onMounted, ref } from "vue";

import { INT } from "@skeljs/core/src/lang/baseinfo";
import ZonedDateTime from "@skeljs/core/src/lang/time/ZonedDateTime";
import type { DialogSelectCallback } from "@skeljs/core/src/ui/types";

import Article from "./Article";
import { ArticleTag } from "./ArticleTag";
import ArticleTagType from "./ArticleTagType";

export const title = "Choose dialog for: Article tag";
export interface Props {
    modal?: boolean | string
}

</script>

<script setup lang="ts">
import EntityChooseDialog from "@skeljs/dba/src/ui/lily/EntityChooseDialog.vue";

const model = defineModel();

const props = withDefaults(defineProps<Props>(), {
    modal: true
});

const emit = defineEmits<{
    error: [message: string]
}>();

// property shortcuts

const typeMap = {
    "INT": INT,
    "ZonedDateTime": ZonedDateTime.TYPE,
    "Article": Article.TYPE,
    "ArticleTagType": ArticleTagType.TYPE,
};

const entityChooseDialog = ref<undefined | InstanceType<typeof EntityChooseDialog>>();
defineExpose({ open });

function open(callback?: DialogSelectCallback) {
    entityChooseDialog.value?.open(callback);
}

onMounted(() => {
});

</script>

<template>
    <EntityChooseDialog ref="entityChooseDialog" :type="ArticleTag.TYPE" :typeMap="typeMap" :modal="modal">
        <th data-type="INT" data-field="id">Id</th>
        <th data-type="ZonedDateTime" data-field="creationDate">Creation Date</th>
        <th data-type="ZonedDateTime" data-field="lastModifiedDate">Last Modified Date</th>
        <th data-type="INT" data-field="version">Version</th>
        <th data-type="Article" data-format="label" data-field="article">Article</th>
        <th data-type="ArticleTagType" data-format="label" data-field="tag">Tag</th>
    </EntityChooseDialog>
</template>

<style scoped lang="scss">
.component-root {
    padding: 0;
}
</style>
