<template>
    <v-card class="mx-auto pa-12 pb-8" style="margin-top: 10%;" elevation="12" max-width="800" rounded="lg">
        <p class="text-h2">Hi
        <p class="font-weight-bold d-inline">{{ user.name }} {{ user.surname }}</p>!</p>
        <p class="text-h5 font-italic">Welcome to your productivity hub</p>
        <Transition enter-active-class="animate__animated animate__zoomIn" appear>
            <img class="mt-5" style="max-height: 225px" src="/worknest-logo.png"></img>
        </Transition>
    </v-card>
</template>

<script>
/**
 * The HomeView component is a welcome card for authenticated users.
 * It displays a personalized greeting with the user's name and surname, and a motivational message.
 * The component fetches the user's data from a backend API upon mounting.
 *
 * 
 * Data properties:
 * @vue-data {Object} user - An object to store the currently authenticated user's data.
 *
 * Lifecycle hooks:
 * @vue-lifecycle-hook {Function} beforeMount - Called before the component is mounted.
 *      It makes an API call to retrieve the user's data based on the userId stored in local storage.
 * 
 * @subcategory views
 */
export default {
    data() {
        return {
            // Data property to store the current user's information.
            user: {}
        }
    },
    beforeMount() {
        /**
         * Lifecycle hook to fetch the user's data before the component mounts.
         * It sends a request to the backend API using the userId from local storage.
         * The response data is then assigned to the 'user' data property.
         */
        this.$ApiService.find_user_by_id(localStorage.getItem('userId')).then((res) => {
            this.user = res.data;
        });
    },
}
</script>
