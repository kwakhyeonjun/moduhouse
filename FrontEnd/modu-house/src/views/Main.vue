<template>
  <v-app id="main">
    <nav-bar />
    <!--네비바-->
    <!-- 어차피 아무것도 없어서 지웠어여 죄송해여 흑-->
    <v-app>
      <router-view />
      <!--

        -->
      <footer-bar></footer-bar>
    </v-app>
  </v-app>
</template>

<script>
//import navibar
import NavBar from "@/components/layout/NavBar.vue";
import FooterBar from "@/components/layout/FooterBar.vue";

export default {
  name: "Main",
  components: {
    NavBar,
    FooterBar,
  },
};
</script>

<style>
.main-container {
  margin: 2%;
}
.main-right-container {
  margin: 25% 10%;
}
.v-application--wrap {
  max-width: 100% !important;
  min-height: 90vh !important;
}
</style>
