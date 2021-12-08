<template>
  <v-app>
    폐-기
    <v-list-item v-for="(item, index) in lists" :key="index"></v-list-item>
  </v-app>
</template>

<script>
import http from "@/util/http-common.js";

export default {
  name: "ListSubscription",
  data() {
    return {
      lists: null,
    };
  },
  created() {
    http.get("/subs/list").then((response) => {
      console.log(response.data);
      this.lists = response;
    });
  },
};
</script>

<style></style>
