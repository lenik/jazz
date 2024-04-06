<script lang="ts">
import { onMounted, ref } from 'vue';
import { Person_TYPE } from './PersonTypeInfo';
import Person from './Person';

export const title = '[Test] Person Listing';

export interface Props {
}

</script>

<script setup lang="ts">
import ListingAdmin from '@skeljs/dba/src/ui/lily/ListingAdmin.vue';

const props = withDefaults(defineProps<Props>(), {
});

// const admin = ref<InstanceType<typeof LilyAdmin>>();
const type = Person_TYPE;

onMounted(() => {
});

</script>

<template>
    <ListingAdmin ref="admin" :type="type" :page-size="20">

        <template v-slot="{ o, id, label, description, gender, birthday, father, mother, ssn, dln }">
            <span> [{{ id }}] </span>
            <span> {{ label }} </span>
            <!-- <span v-if="description != null"> ({{ description }}) </span> -->
            <span class="detail">
                <span> {{ gender }} </span>
                <span> {{ birthday }} </span>
                <span v-if="father != null"> father: {{ father }} </span>
                <span v-if="mother != null"> mother: {{ mother }} </span>
                <span v-if="ssn != null"> SSN={{ ssn }} </span>
                <span v-if="dln! + null"> DLN={{ dln }} </span>
            </span>
        </template>

        <template #import-item="{ o, id, label, birthday }">
            <span> [{{ id }}] </span>
            <span> {{ label }} </span>
            <span> ({{ birthday }}) </span>
        </template>
    </ListingAdmin>
</template>

<style scoped lang="scss">
li {
    .detail {
        margin-left: 1em;
        color: gray;

        span {
            &:not(:first-child) {
                &:before {
                    content: ", ";
                }
            }
        }
    }
}
</style>
