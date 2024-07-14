<script lang="ts">
import { onMounted, ref } from "vue";

import { DOUBLE, INT, STRING } from "@skeljs/core/src/lang/baseinfo";
import OffsetDateTime from "@skeljs/core/src/lang/time/OffsetDateTime";
import type { DialogSelectCallback } from "@skeljs/core/src/ui/types";

import { ArtifactTypeParam } from "./ArtifactTypeParam";
import { ArtifactType_TYPE } from "./ArtifactTypeTypeInfo";
import { StdParameter_TYPE } from "./StdParameterTypeInfo";

export const title = "Choose dialog for: Artifact type param";
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
    "OffsetDateTime": OffsetDateTime.TYPE,
    "ArtifactType": ArtifactType_TYPE,
    "StdParameter": StdParameter_TYPE,
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
    <EntityChooseDialog ref="entityChooseDialog" :type="ArtifactTypeParam.TYPE" :typeMap="typeMap" :modal="modal">
        <th data-type="INT" data-field="id">Id</th>
        <th data-type="OffsetDateTime" data-field="creationDate">Creation Date</th>
        <th data-type="OffsetDateTime" data-field="lastModified">Last Modified</th>
        <th data-type="INT" data-field="version">Version</th>
        <th data-type="ArtifactType" data-format="label" data-field="arttype">Arttype</th>
        <th data-type="StdParameter" data-format="label" data-field="parameter">Parameter</th>
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
