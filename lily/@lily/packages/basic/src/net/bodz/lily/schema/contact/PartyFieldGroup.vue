<script lang="ts">
import { onMounted, ref } from "vue";
import EntityPropertyMap from "skel01-dba/src/net/bodz/lily/entity/EntityPropertyMap";
import { Party_TYPE } from "./PartyTypeInfo";
import Party from "./Party";

export interface Props {
    meta: EntityPropertyMap
}
</script>

<script setup lang="ts">
import FieldRow from "skel01-core/src/ui/FieldRow.vue";
import DateTime from "skel01-core/src/ui/input/DateTime.vue";
import RefEditor from "skel01-dba/src/ui/input/RefEditor.vue";
import FieldGroup from "skel01-dba/src/ui/lily/FieldGroup.vue";
import PartyCategoryChooseDialog from "./PartyCategoryChooseDialog.vue";

const model = defineModel<Party>({ required: true });

const props = withDefaults(defineProps<Props>(), {
});

const emit = defineEmits<{
    error: [message: string]
}>();

// property shortcuts

const rootElement = ref<HTMLElement>();
const partyCategoryChooseDialog = ref<InstanceType<typeof PartyCategoryChooseDialog>>();

// methods

defineExpose({});

onMounted(() => {
});
</script>

<template>
    <FieldGroup :type="Party_TYPE" ref="rootElement">
        <FieldRow :property="meta.birthday" v-model="model.birthday">
            <DateTime v-model="model.birthday" />
        </FieldRow>
        <FieldRow :property="meta.timeZone" v-model="model.timeZoneId">
            <input type="text" v-model="model.timeZoneId" />
        </FieldRow>
        <FieldRow :property="meta.customer" v-model="model.customer">
            <input type="checkbox" v-model="model.customer" />
        </FieldRow>
        <FieldRow :property="meta.supplier" v-model="model.supplier">
            <input type="checkbox" v-model="model.supplier" />
        </FieldRow>
        <FieldRow :property="meta.subject" v-model="model.subject">
            <input type="text" v-model="model.subject" />
        </FieldRow>
        <FieldRow :property="meta.category" v-model="model.category">
            <RefEditor :dialog="partyCategoryChooseDialog" v-model="model.category" v-model:id="model.categoryId" />
        </FieldRow>
    </FieldGroup>
    <PartyCategoryChooseDialog ref="partyCategoryChooseDialog" />
</template>

<style scoped lang="scss"></style>
