<script lang="ts">
import { onMounted, ref } from "vue";

import { BIG_DECIMAL, INT } from "skel01-core/src/lang/baseinfo";
import OffsetDateTime from "skel01-core/src/lang/time/OffsetDateTime";
import DefaultState from "skel01-core/src/net/bodz/bas/repr/state/DefaultState";

import { ApiType_TYPE } from "./ApiTypeTypeInfo";
import VApiCredit from "./VApiCredit";
import { VApp_TYPE } from "./VAppTypeInfo";

export const title = "Admin view of: V api credit";
export interface Props {
}

</script>

<script setup lang="ts">
import LilyAdmin from "skel01-dba/src/ui/lily/LilyAdmin.vue";

import VApiCreditEditor from "./VApiCreditEditor.vue";

const props = withDefaults(defineProps<Props>(), {
});

const admin = ref<InstanceType<typeof LilyAdmin>>();
const type = VApiCredit.TYPE;
const selection = ref<any>({});

const typeMap = {
    "INT": INT,
    "DefaultState": DefaultState.TYPE,
    "OffsetDateTime": OffsetDateTime.TYPE,
    "VApp": VApp_TYPE,
    "ApiType": ApiType_TYPE,
    "BIG_DECIMAL": BIG_DECIMAL,
};

onMounted(() => {
});


</script>

<template>
    <LilyAdmin ref="admin" :type="type" :typeMap="typeMap" v-model="selection">
        <template #columns>
            <th data-type="INT" data-field="id">Id</th>
            <th data-type="INT" data-field="priority">Priority</th>
            <th data-type="INT" data-field="flags">Flags</th>
            <th data-type="DefaultState" data-field="state">State</th>
            <th data-type="OffsetDateTime" data-field="creationDate">Creation Date</th>
            <th data-type="OffsetDateTime" data-field="lastModified">Last Modified</th>
            <th data-type="INT" data-field="version">Version</th>
            <th data-type="VApp" data-format="label" data-field="app">App</th>
            <th data-type="ApiType" data-format="label" data-field="api">Api</th>
            <th data-type="BIG_DECIMAL" data-field="credit">Credit</th>
        </template>
        <template #preview>
            <VApiCreditEditor class="editor" v-model="selection" />
        </template>
        <template #side-tools> Side Tools</template>
        <template #editor>
            <VApiCreditEditor class="editor" v-model="selection" />
        </template>
    </LilyAdmin>
</template>

<style lang="scss"></style>

<style scoped lang="scss">
.lily-admin {
    padding: 0;
}
</style>
