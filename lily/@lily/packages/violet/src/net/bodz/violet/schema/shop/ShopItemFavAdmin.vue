<script lang="ts">
import { onMounted, ref } from "vue";

import { LONG } from "skel01-core/src/lang/baseinfo";
import { User_TYPE } from "@lily/basic/src/net/bodz/lily/schema/account/UserTypeInfo";

import ShopItemFav from "./ShopItemFav";
import { ShopItem_TYPE } from "./ShopItemTypeInfo";

export const title = "Admin view of: Shop item fav";
export interface Props {
}

</script>

<script setup lang="ts">
import LilyAdmin from "skel01-dba/src/ui/lily/LilyAdmin.vue";

import ShopItemFavEditor from "./ShopItemFavEditor.vue";

const props = withDefaults(defineProps<Props>(), {
});

const admin = ref<InstanceType<typeof LilyAdmin>>();
const type = ShopItemFav.TYPE;
const selection = ref<any>({});

const typeMap = {
    "LONG": LONG,
    "ShopItem": ShopItem_TYPE,
    "User": User_TYPE,
};

onMounted(() => {
});


</script>

<template>
    <LilyAdmin ref="admin" :type="type" :typeMap="typeMap" v-model="selection">
        <template #columns>
            <th data-type="LONG" data-field="id">Id</th>
            <th data-type="ShopItem" data-format="label" data-field="shopItem">Shop Item</th>
            <th data-type="User" data-format="label" data-field="user">User</th>
        </template>
        <template #preview>
            <ShopItemFavEditor class="editor" v-model="selection" />
        </template>
        <template #side-tools> Side Tools</template>
        <template #editor>
            <ShopItemFavEditor class="editor" v-model="selection" />
        </template>
    </LilyAdmin>
</template>

<style lang="scss"></style>

<style scoped lang="scss">
.lily-admin {
    padding: 0;
}
</style>
