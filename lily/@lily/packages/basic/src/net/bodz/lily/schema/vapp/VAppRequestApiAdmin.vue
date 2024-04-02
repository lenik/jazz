<script lang="ts">
import { onMounted, ref } from "vue";

import { INT, LONG } from "@skeljs/core/src/lang/baseinfo";
import OffsetDateTime from "@skeljs/core/src/lang/time/OffsetDateTime";
import DefaultState from "@skeljs/core/src/net/bodz/bas/repr/state/DefaultState";

import { ApiType_TYPE } from "./ApiTypeTypeInfo";
import VAppRequestApi from "./VAppRequestApi";
import { VAppRequest_TYPE } from "./VAppRequestTypeInfo";

export const title = "Admin view of: V app request api";
export interface Props {
}

</script>

<script setup lang="ts">
import LilyAdmin from "@skeljs/dba/src/ui/lily/LilyAdmin.vue";

import VAppRequestApiEditor from "./VAppRequestApiEditor.vue";

const props = withDefaults(defineProps<Props>(), {
});

const admin = ref<InstanceType<typeof LilyAdmin>>();
const type = VAppRequestApi.TYPE;
const selection = ref<any>({});

const typeMap = {
    "LONG": LONG,
    "INT": INT,
    "DefaultState": DefaultState.TYPE,
    "OffsetDateTime": OffsetDateTime.TYPE,
    "VAppRequest": VAppRequest_TYPE,
    "ApiType": ApiType_TYPE,
};

onMounted(() => {
});


</script>

<template>
    <LilyAdmin ref="admin" :type="type" :typeMap="typeMap" v-model="selection">
        <template #columns>
            <th data-type="LONG" data-field="id">Id</th>
            <th data-type="INT" data-field="priority">Priority</th>
            <th data-type="INT" data-field="flags">Flags</th>
            <th data-type="DefaultState" data-field="state">State</th>
            <th data-type="OffsetDateTime" data-field="creationDate">Creation Date</th>
            <th data-type="OffsetDateTime" data-field="lastModified">Last Modified</th>
            <th data-type="INT" data-field="version">Version</th>
            <th data-type="VAppRequest" data-format="label" data-field="parent">Parent</th>
            <th data-type="ApiType" data-format="label" data-field="api">Api</th>
        </template>
        <template #preview>
            <VAppRequestApiEditor class="editor" v-model="selection" />
        </template>
        <template #side-tools> Side Tools</template>
        <template #editor>
            <VAppRequestApiEditor class="editor" v-model="selection" />
        </template>
    </LilyAdmin>
</template>

<style lang="scss"></style>

<style scoped lang="scss">
.lily-admin {
    padding: 0;
}
</style>
