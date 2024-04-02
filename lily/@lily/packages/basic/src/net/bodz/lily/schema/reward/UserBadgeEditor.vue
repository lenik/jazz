<script lang="ts">
import { onMounted, provide, ref } from "vue";

import type { int } from "@skeljs/core/src/lang/basetype";
import { getDefaultFieldRowProps } from "@skeljs/dba/src/ui/lily/defaults";

import { IdEntity_TYPE } from "../../concrete/IdEntityTypeInfo";
import UserBadge from "./UserBadge";
import { _UserBadge_stuff_TYPE } from "./_UserBadge_stuff_TypeInfo";

export const title = "Editor view of: User badge";
export interface Props {
}

</script>

<script setup lang="ts">
import FieldRow from "@skeljs/core/src/ui/FieldRow.vue";
import { FIELD_ROW_PROPS } from "@skeljs/core/src/ui/FieldRow.vue";
import RefEditor from "@skeljs/dba/src/ui/input/RefEditor.vue";
import FieldGroup from "@skeljs/dba/src/ui/lily/FieldGroup.vue";

import StructRowFieldGroup from "../../concrete/StructRowFieldGroup.vue";
import UserChooseDialog from "../account/UserChooseDialog.vue";
import BadgeChooseDialog from "./BadgeChooseDialog.vue";

defineOptions({
    inheritAttrs: false
});

const model = defineModel<UserBadge>();

const props = withDefaults(defineProps<Props>(), {
});

const emit = defineEmits<{
    error: [message: string]
    change: [e: Event]
}>();

// property shortcuts

const meta = UserBadge.TYPE.property;
const fieldRowProps = getDefaultFieldRowProps({ labelWidth: '7rem' });
provide(FIELD_ROW_PROPS, fieldRowProps);

const rootElement = ref<HTMLElement>();
const userChooseDialog = ref<InstanceType<typeof UserChooseDialog>>();
const badgeChooseDialog = ref<InstanceType<typeof BadgeChooseDialog>>();
const valids = ref<any>({});

// methods

defineExpose({ update });

function update() {
}

onMounted(() => {
});

</script>

<template>
    <div class="entity-editor person-editor" ref="rootElement" v-if="model != null" v-bind="$attrs">
        <StructRowFieldGroup :meta="meta" v-model="model" />
        <FieldGroup :type="IdEntity_TYPE">
            <FieldRow :property="meta.id" v-model="model.id">
                <input type="number" v-model="model.id" disabled />
            </FieldRow>
        </FieldGroup>
        <FieldGroup :type="_UserBadge_stuff_TYPE">
            <FieldRow :property="meta.user" v-model="model.user">
                <RefEditor :dialog="userChooseDialog" v-model="model.user" v-model:id="model.userId" />
            </FieldRow>
            <FieldRow :property="meta.badge" v-model="model.badge">
                <RefEditor :dialog="badgeChooseDialog" v-model="model.badge" v-model:id="model.badgeId" />
            </FieldRow>
        </FieldGroup>
    </div>
    <UserChooseDialog ref="userChooseDialog" />
    <BadgeChooseDialog ref="badgeChooseDialog" />
</template>

<style scoped lang="scss">
.entity-editor {
    padding: 0;
}
</style>
