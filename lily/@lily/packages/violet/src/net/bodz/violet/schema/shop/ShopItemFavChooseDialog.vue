<script lang="ts">
import { onMounted, ref } from "vue";

import { LONG } from "@skeljs/core/src/lang/baseinfo";
import type { DialogSelectCallback } from "@skeljs/core/src/ui/types";
import { User_TYPE } from "@lily/basic/src/net/bodz/lily/schema/account/UserTypeInfo";

import { ShopItemFav } from "./ShopItemFav";
import { ShopItem_TYPE } from "./ShopItemTypeInfo";

export const title = "Choose dialog for: Shop item fav";
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
    "LONG": LONG,
    "ShopItem": ShopItem_TYPE,
    "User": User_TYPE,
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
    <EntityChooseDialog ref="entityChooseDialog" :type="ShopItemFav.TYPE" :typeMap="typeMap" :modal="modal">
        <th data-type="LONG" data-field="id">Id</th>
        <th data-type="ShopItem" data-format="label" data-field="shopItem">Shop Item</th>
        <th data-type="User" data-format="label" data-field="user">User</th>
    </EntityChooseDialog>
</template>

<style scoped lang="scss">
.component-root {
    padding: 0;
}
</style>
