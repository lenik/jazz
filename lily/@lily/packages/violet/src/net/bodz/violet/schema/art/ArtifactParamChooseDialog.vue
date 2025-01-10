<script lang="ts">
import { onMounted, ref } from "vue";

import { DOUBLE, INT, STRING } from "skel01-core/src/lang/baseinfo";
import OffsetDateTime from "skel01-core/src/lang/time/OffsetDateTime";
import type { DialogSelectCallback } from "skel01-core/src/ui/types";

import { ArtifactParam } from "./ArtifactParam";
import { Artifact_TYPE } from "./ArtifactTypeInfo";
import { StdParameter_TYPE } from "./StdParameterTypeInfo";

export const title = "Choose dialog for: Artifact param";
export interface Props {
    modal?: boolean | string
}

</script>

<script setup lang="ts">
import EntityChooseDialog from "skel01-dba/src/ui/lily/EntityChooseDialog.vue";

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
    "Artifact": Artifact_TYPE,
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
    <EntityChooseDialog ref="entityChooseDialog" :type="ArtifactParam.TYPE" :typeMap="typeMap" :modal="modal">
        <th data-type="INT" data-field="id">Id</th>
        <th data-type="OffsetDateTime" data-field="creationDate">Creation Date</th>
        <th data-type="OffsetDateTime" data-field="lastModified">Last Modified</th>
        <th data-type="INT" data-field="version">Version</th>
        <th data-type="Artifact" data-format="label" data-field="artifact">Artifact</th>
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
