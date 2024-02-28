<script lang="ts">
import { onMounted, ref } from "vue";

import { DOUBLE, INT, STRING } from "@skeljs/core/src/lang/baseinfo";
import ZonedDateTime from "@skeljs/core/src/lang/time/ZonedDateTime";
import type { DialogSelectCallback } from "@skeljs/core/src/ui/types";

import Article from "./Article";
import { ArticleParameter } from "./ArticleParameter";
import ArticleParameterType from "./ArticleParameterType";

export const title = "Choose dialog for: Article parameter";
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
    "ArticleParameterType": ArticleParameterType.TYPE,
    "DOUBLE": DOUBLE,
    "STRING": STRING,
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
    <EntityChooseDialog ref="entityChooseDialog" :type="ArticleParameter.TYPE" :typeMap="typeMap" :modal="modal">
        <th data-type="INT" data-field="id">Id</th>
        <th data-type="ZonedDateTime" data-field="creationDate">Creation Date</th>
        <th data-type="ZonedDateTime" data-field="lastModifiedDate">Last Modified Date</th>
        <th data-type="INT" data-field="version">Version</th>
        <th data-type="Article" data-format="label" data-field="article">Article</th>
        <th data-type="ArticleParameterType" data-format="label" data-field="parameter">Parameter</th>
        <th data-type="INT" data-field="ival">Ival</th>
        <th data-type="DOUBLE" data-field="fval">Fval</th>
        <th data-type="STRING" data-field="sval">Sval</th>
    </EntityChooseDialog>
</template>

<style scoped lang="scss">
.component-root {
    padding: 0;
}
</style>
