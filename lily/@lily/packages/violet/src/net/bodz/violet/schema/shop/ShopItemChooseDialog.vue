<script lang="ts">
import { onMounted, ref } from "vue";

import { JSON_VARIANT } from "skel01-core/src/lang/bas-info";
import { BIG_DECIMAL, INT, LONG, STRING } from "skel01-core/src/lang/baseinfo";
import OffsetDateTime from "skel01-core/src/lang/time/OffsetDateTime";
import DefaultState from "skel01-core/src/net/bodz/bas/repr/state/DefaultState";
import type { DialogSelectCallback } from "skel01-core/src/ui/types";

import { Artifact_TYPE } from "../art/ArtifactTypeInfo";
import { ShopItem } from "./ShopItem";
import { ShopItemCategory_TYPE } from "./ShopItemCategoryTypeInfo";
import { Shop_TYPE } from "./ShopTypeInfo";

export const title = "Choose dialog for: Shop item";
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
    "LONG": LONG,
    "STRING": STRING,
    "INT": INT,
    "DefaultState": DefaultState.TYPE,
    "OffsetDateTime": OffsetDateTime.TYPE,
    "JSON_VARIANT": JSON_VARIANT,
    "Shop": Shop_TYPE,
    "ShopItemCategory": ShopItemCategory_TYPE,
    "Artifact": Artifact_TYPE,
    "BIG_DECIMAL": BIG_DECIMAL,
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
    <EntityChooseDialog ref="entityChooseDialog" :type="ShopItem.TYPE" :typeMap="typeMap" :modal="modal">
        <th data-type="LONG" data-field="id">Id</th>
        <th data-type="STRING" data-field="label">Label</th>
        <th data-type="STRING" data-field="description">Description</th>
        <th data-type="STRING" data-field="icon">Icon</th>
        <th data-type="INT" data-field="priority">Priority</th>
        <th data-type="INT" data-field="flags">Flags</th>
        <th data-type="DefaultState" data-field="state">State</th>
        <th data-type="OffsetDateTime" data-field="beginTime">Begin Time</th>
        <th data-type="OffsetDateTime" data-field="endTime">End Time</th>
        <th data-type="INT" data-field="year">Year</th>
        <th data-type="OffsetDateTime" data-field="creationDate">Creation Date</th>
        <th data-type="OffsetDateTime" data-field="lastModified">Last Modified</th>
        <th data-type="INT" data-field="version">Version</th>
        <th class="hidden" data-type="JSON_VARIANT" data-field="properties">Properties</th>
        <th data-type="JSON_VARIANT" data-field="files">Files</th>
        <th data-type="Shop" data-format="label" data-field="shop">Shop</th>
        <th data-type="ShopItemCategory" data-format="label" data-field="category">Category</th>
        <th data-type="Artifact" data-format="label" data-field="artifact">Artifact</th>
        <th data-type="JSON_VARIANT" data-field="batch">Batch</th>
        <th data-type="BIG_DECIMAL" data-field="price">Price</th>
        <th data-type="BIG_DECIMAL" data-field="quantity">Quantity</th>
    </EntityChooseDialog>
</template>

<style scoped lang="scss">
.component-root {
    padding: 0;
}
</style>
