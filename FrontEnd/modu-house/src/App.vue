<template>
  <v-main>
    <v-app-bar dark height="40px">
      <v-icon>home</v-icon>
      MODU
    </v-app-bar>

    <router-view />
    <v-btn
      fab
      dark
      large
      :to="{ name: 'QA' }"
      color="primary"
      fixed
      right
      bottom
      elevation="20"
    >
      <v-badge
        :value="hover"
        content="Ask"
        color="blue"
        left
        transition="slide-x-transition"
        light
      >
        <v-hover v-model="hover">
          <v-icon dark>feedback</v-icon>
        </v-hover>
      </v-badge>
    </v-btn>
  </v-main>
</template>

<script>
export default {
  name: "App",

  data: () => ({
    hover: false,
  }),
};
</script>
