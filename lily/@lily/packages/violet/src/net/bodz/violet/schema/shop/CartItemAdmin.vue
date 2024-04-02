<script lang="ts">
import { onMounted, ref } from "vue";

import { BIG_DECIMAL, INT, LONG, STRING } from "@skeljs/core/src/lang/baseinfo";
import OffsetDateTime from "@skeljs/core/src/lang/time/OffsetDateTime";
import DefaultState from "@skeljs/core/src/net/bodz/bas/repr/state/DefaultState";
import { Group_TYPE } from "@lily/basic/src/net/bodz/lily/schema/account/GroupTypeInfo";
import { User_TYPE } from "@lily/basic/src/net/bodz/lily/schema/account/UserTypeInfo";

import CartItem from "./CartItem";
import { ShopItem_TYPE } from "./ShopItemTypeInfo";

export const title = "Admin view of: Cart item";
export interface Props {
}

</script>

<script setup lang="ts">
import LilyAdmin from "@skeljs/dba/src/ui/lily/LilyAdmin.vue";

import CartItemEditor from "./CartItemEditor.vue";

const props = withDefaults(defineProps<Props>(), {
});

const admin = ref<InstanceType<typeof LilyAdmin>>();
const type = CartItem.TYPE;
const selection = ref<any>({});

const typeMap = {
    "LONG": LONG,
    "User": User_TYPE,
    "Group": Group_TYPE,
    "INT": INT,
    "STRING": STRING,
    "DefaultState": DefaultState.TYPE,
    "OffsetDateTime": OffsetDateTime.TYPE,
    "ShopItem": ShopItem_TYPE,
    "BIG_DECIMAL": BIG_DECIMAL,
};

onMounted(() => {
});


</script>

<template>
    <LilyAdmin ref="admin" :type="type" :typeMap="typeMap" v-model="selection">
        <template #columns>
            <th data-type="LONG" data-field="id">Id</th>
            <th data-type="User" data-format="label" data-field="ownerUser">Owner User</th>
            <th data-type="Group" data-format="label" data-field="ownerGroup">Owner Group</th>
            <th data-type="INT" data-field="accessMode">Access Mode</th>
            <th data-type="INT" data-field="acl">Acl</th>
            <th data-type="STRING" data-field="label">Label</th>
            <th data-type="STRING" data-field="description">Description</th>
            <th data-type="STRING" data-field="icon">Icon</th>
            <th data-type="INT" data-field="priority">Priority</th>
            <th data-type="INT" data-field="flags">Flags</th>
            <th data-type="DefaultState" data-field="state">State</th>
            <th data-type="OffsetDateTime" data-field="creationDate">Creation Date</th>
            <th data-type="OffsetDateTime" data-field="lastModified">Last Modified</th>
            <th data-type="INT" data-field="version">Version</th>
            <th data-type="ShopItem" data-format="label" data-field="shopItem">Shop Item</th>
            <th data-type="BIG_DECIMAL" data-field="price">Price</th>
            <th data-type="BIG_DECIMAL" data-field="quantity">Quantity</th>
        </template>
        <template #preview>
            <CartItemEditor class="editor" v-model="selection" />
        </template>
        <template #side-tools> Side Tools</template>
        <template #editor>
            <CartItemEditor class="editor" v-model="selection" />
        </template>
    </LilyAdmin>
</template>

<style lang="scss"></style>

<style scoped lang="scss">
.lily-admin {
    padding: 0;
}
</style>
