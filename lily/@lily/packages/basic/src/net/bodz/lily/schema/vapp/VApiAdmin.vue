<script lang="ts">
import { onMounted, ref } from "vue";

import { JSON_VARIANT } from "@skeljs/core/src/lang/bas-info";
import { INT, LONG, STRING } from "@skeljs/core/src/lang/baseinfo";
import OffsetDateTime from "@skeljs/core/src/lang/time/OffsetDateTime";

import { ApiType_TYPE } from "./ApiTypeTypeInfo";
import VApi from "./VApi";
import { VApp_TYPE } from "./VAppTypeInfo";

export const title = "Admin view of: V api";
export interface Props {
}

</script>

<script setup lang="ts">
import LilyAdmin from "@skeljs/dba/src/ui/lily/LilyAdmin.vue";

import VApiEditor from "./VApiEditor.vue";

const props = withDefaults(defineProps<Props>(), {
});

const admin = ref<InstanceType<typeof LilyAdmin>>();
const type = VApi.TYPE;
const selection = ref<any>({});

const typeMap = {
    "LONG": LONG,
    "OffsetDateTime": OffsetDateTime.TYPE,
    "INT": INT,
    "JSON_VARIANT": JSON_VARIANT,
    "VApp": VApp_TYPE,
    "ApiType": ApiType_TYPE,
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
            <th data-type="JSON_VARIANT" data-field="properties">Properties</th>
            <th data-type="VApp" data-format="label" data-field="app">App</th>
            <th data-type="ApiType" data-format="label" data-field="api">Api</th>
            <th data-type="STRING" data-field="callback">Callback</th>
        </template>
        <template #preview>
            <VApiEditor class="editor" v-model="selection" />
        </template>
        <template #side-tools> Side Tools</template>
        <template #editor>
            <VApiEditor class="editor" v-model="selection" />
        </template>
    </LilyAdmin>
</template>

<style lang="scss"></style>

<style scoped lang="scss">
.lily-admin {
    padding: 0;
}
</style>
