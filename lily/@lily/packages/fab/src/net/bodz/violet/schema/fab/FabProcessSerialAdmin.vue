<script lang="ts">
import { onMounted, ref } from "vue";

import { INT, LONG, STRING } from "@skeljs/core/src/lang/baseinfo";
import OffsetDateTime from "@skeljs/core/src/lang/time/OffsetDateTime";

import FabProcessSerial from "./FabProcessSerial";
import { FabProcess_TYPE } from "./FabProcessTypeInfo";

export const title = "Admin view of: Fab process serial";
export interface Props {
}

</script>

<script setup lang="ts">
import LilyAdmin from "@skeljs/dba/src/ui/lily/LilyAdmin.vue";

import FabProcessSerialEditor from "./FabProcessSerialEditor.vue";

const props = withDefaults(defineProps<Props>(), {
});

const admin = ref<InstanceType<typeof LilyAdmin>>();
const type = FabProcessSerial.TYPE;
const selection = ref<any>({});

const typeMap = {
    "LONG": LONG,
    "OffsetDateTime": OffsetDateTime.TYPE,
    "INT": INT,
    "FabProcess": FabProcess_TYPE,
    "STRING": STRING,
};

onMounted(() => {
});


</script>

<template>
    <LilyAdmin ref="admin" :type="type" :typeMap="typeMap" v-model="selection">
        <template #columns>
            <th data-type="LONG" data-field="id">Id</th>
            <th data-type="OffsetDateTime" data-field="creationDate">Creation Date</th>
            <th data-type="OffsetDateTime" data-field="lastModified">Last Modified</th>
            <th data-type="INT" data-field="version">Version</th>
            <th data-type="FabProcess" data-format="label" data-field="process">Process</th>
            <th data-type="STRING" data-field="serial">Serial</th>
        </template>
        <template #preview>
            <FabProcessSerialEditor class="editor" v-model="selection" />
        </template>
        <template #side-tools> Side Tools</template>
        <template #editor>
            <FabProcessSerialEditor class="editor" v-model="selection" />
        </template>
    </LilyAdmin>
</template>

<style lang="scss"></style>

<style scoped lang="scss">
.lily-admin {
    padding: 0;
}
</style>
