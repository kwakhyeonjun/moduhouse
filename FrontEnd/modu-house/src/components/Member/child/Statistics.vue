<template>
  <v-container clipped-left>
    <v-card-title><h2>현황 & 통계</h2></v-card-title>
    <v-row>
      <v-col md="6">
        <v-container>
          <v-card-title><h3>인기 게시글</h3></v-card-title>
          <popular-posts></popular-posts>
        </v-container>
      </v-col>
      <v-col md="6">
        <v-container>
          <v-card-title><h3>일일 방문자 수</h3></v-card-title>
          <chart></chart>
          <v-card-title><h3>신고 접수된 게시글</h3></v-card-title>
          <reported></reported>
        </v-container>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import PopularPosts from "@/components/Member/child/adminFcn/stats/PopularPosts.vue";
import Reported from "@/components/Member/child/adminFcn/stats/Reported.vue";
import Chart from "@/components/Member/child/adminFcn/stats/Chart.vue";

export default {
  Name: "Satatistics",
  components: {
    PopularPosts,
    Chart,
    Reported,
  },
};
</script>
PopularPosts

<style></style>
