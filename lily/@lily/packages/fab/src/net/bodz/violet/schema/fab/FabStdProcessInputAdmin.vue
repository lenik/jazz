<script lang="ts">
import { onMounted, ref } from "vue";

import { BIG_DECIMAL, INT } from "skel01-core/src/lang/baseinfo";
import OffsetDateTime from "skel01-core/src/lang/time/OffsetDateTime";
import { ArtifactModel_TYPE } from "lily-violet/src/net/bodz/violet/schema/art/ArtifactModelTypeInfo";
import { Artifact_TYPE } from "lily-violet/src/net/bodz/violet/schema/art/ArtifactTypeInfo";

import FabStdProcessInput from "./FabStdProcessInput";
import { FabStdProcess_TYPE } from "./FabStdProcessTypeInfo";

export const title = "Admin view of: Fab std process input";
export interface Props {
}

</script>

<script setup lang="ts">
import LilyAdmin from "skel01-dba/src/ui/lily/LilyAdmin.vue";

import FabStdProcessInputEditor from "./FabStdProcessInputEditor.vue";

const props = withDefaults(defineProps<Props>(), {
});

const admin = ref<InstanceType<typeof LilyAdmin>>();
const type = FabStdProcessInput.TYPE;
const selection = ref<any>({});

const typeMap = {
    "INT": INT,
    "OffsetDateTime": OffsetDateTime.TYPE,
    "FabStdProcess": FabStdProcess_TYPE,
    "ArtifactModel": ArtifactModel_TYPE,
    "Artifact": Artifact_TYPE,
    "BIG_DECIMAL": BIG_DECIMAL,
};

onMounted(() => {
});


</script>

<template>
    <LilyAdmin ref="admin" :type="type" :typeMap="typeMap" v-model="selection">
        <template #columns>
            <th data-type="INT" data-field="id">Id</th>
            <th data-type="OffsetDateTime" data-field="creationDate">Creation Date</th>
            <th data-type="OffsetDateTime" data-field="lastModified">Last Modified</th>
            <th data-type="INT" data-field="version">Version</th>
            <th data-type="FabStdProcess" data-format="label" data-field="process">Process</th>
            <th data-type="ArtifactModel" data-format="label" data-field="model">Model</th>
            <th data-type="Artifact" data-format="label" data-field="artifact">Artifact</th>
            <th data-type="BIG_DECIMAL" data-field="quantity">Quantity</th>
        </template>
        <template #preview>
            <FabStdProcessInputEditor class="editor" v-model="selection" />
        </template>
        <template #side-tools> Side Tools</template>
        <template #editor>
            <FabStdProcessInputEditor class="editor" v-model="selection" />
        </template>
    </LilyAdmin>
</template>

<style lang="scss"></style>

<style scoped lang="scss">
.lily-admin {
    padding: 0;
}
</style>
