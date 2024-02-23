<script lang="ts">
import { onMounted, ref } from "vue";

import type { double, integer } from "@skeljs/core/src/lang/type";

import Uom from "./Uom";

export const title = "Admin view of: Uom";
export interface Props {
}

</script>

<script setup lang="ts">
import LilyAdmin from "@skeljs/dba/src/ui/lily/LilyAdmin.vue";

import UomEditor from "./UomEditor.vue";

const props = withDefaults(defineProps<Props>(), {
});

const admin = ref<InstanceType<typeof LilyAdmin>>();
const type = Uom.TYPE;
const selection = ref<any>({});
onMounted(() => {
});


</script>

<template>
    <LilyAdmin ref="admin" :type="type" v-model="selection">
        <template #columns>
            <th data-type="integer" data-field="id">Id</th>
            <th data-type="string" data-field="code">Code</th>
            <th data-type="string" data-field="label">Label</th>
            <th data-type="string" data-field="description">Description</th>
            <th data-type="string" data-field="icon">Icon</th>
            <th data-type="any" data-field="properties">Properties</th>
            <th data-type="string" data-field="prop">Prop</th>
            <th data-type="string" data-format="label" data-field="std">Std</th>
            <th data-type="double" data-field="scale">Scale</th>
        </template>
        <template #preview>
            <UomEditor class="editor" v-model="selection" />
        </template>
        <template #side-tools> Side Tools</template>
        <template #editor>
            <UomEditor class="editor" v-model="selection" />
        </template>
    </LilyAdmin>
</template>

<style lang="scss"></style>

<style scoped lang="scss">
.lily-admin {
    padding: 0;
}
</style>
