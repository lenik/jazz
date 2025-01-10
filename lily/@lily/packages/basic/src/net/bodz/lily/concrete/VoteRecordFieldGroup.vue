<script lang="ts">
import { onMounted, ref } from "vue";
import EntityPropertyMap from "skel01-dba/src/net/bodz/lily/entity/EntityPropertyMap";
import { VoteRecord_TYPE } from "./VoteRecordTypeInfo";
import VoteRecord from "./VoteRecord";

export interface Props {
    meta: EntityPropertyMap
}
</script>

<script setup lang="ts">
import FieldRow from "skel01-core/src/ui/FieldRow.vue";
import RefEditor from "skel01-dba/src/ui/input/RefEditor.vue";
import FieldGroup from "skel01-dba/src/ui/lily/FieldGroup.vue";
import UserChooseDialog from "../schema/account/UserChooseDialog.vue";

const model = defineModel<VoteRecord<any>>({ required: true });

const props = withDefaults(defineProps<Props>(), {
});

const emit = defineEmits<{
    error: [message: string]
}>();

// property shortcuts

const rootElement = ref<HTMLElement>();
const userChooseDialog = ref<InstanceType<typeof UserChooseDialog>>();

// methods

defineExpose({});

onMounted(() => {
});
</script>

<template>
    <FieldGroup :type="VoteRecord_TYPE" ref="rootElement">
        <FieldRow :property="meta.user" v-model="model.user">
            <RefEditor :dialog="userChooseDialog" v-model="model.user" v-model:id="model.userId" />
        </FieldRow>
    </FieldGroup>
    <UserChooseDialog ref="userChooseDialog" />
</template>

<style scoped lang="scss"></style>
