<template>
  <v-app>
    <v-list>
      <v-card color="dark" align="center" elevation="0">
        <v-list-item-title>
          <v-container>
            <div id="roadview" class="road-view"></div>
          </v-container>
        </v-list-item-title>
        <v-speed-dial
          v-model="dialShare"
          absolute
          right
          bottom
          direction="left"
          open-on-hover
        >
          <template v-slot:activator>
            <v-btn fab bottom small color="primary">
              <v-icon v-if="dialShare">mdi-close</v-icon>
              <v-icon v-else>mdi-share-variant</v-icon>
            </v-btn>
          </template>
          <v-btn
            dark
            fab
            bottom
            color="green"
            small
            :href="`https://www.instagram.com/`"
            target="_blank"
          >
            <v-icon>mdi-instagram</v-icon>
          </v-btn>
        </v-speed-dial>
      </v-card>
      <v-card height="82vh">
        <v-card-title>
          {{ houseinfo.aptName }}
        </v-card-title>
        <v-card-subtitle>
          <v-list-item-group>
            {{ houseinfo.sidoName }} {{ houseinfo.gugunName }}
            {{ houseinfo.dongName }} {{ houseinfo.jibun }}
            {{ houseinfo.floor }}
          </v-list-item-group>
          <v-list-item-group>
            {{ houseinfo.buildYear }}년 증축<br />
            최고 매매가 : {{ houseinfo.recentPrice }} 만원
          </v-list-item-group>
        </v-card-subtitle>
        <br />
        <v-divider></v-divider>
        <v-spacer></v-spacer>
        <v-container></v-container>

        <v-data-table
          :headers="headers"
          :items="houseDeals"
          :item-per-page="20"
          :hide-default-footer="true"
          :page.sync="page"
          @page-count="pageCount = $event"
          class="elevation-0"
        ></v-data-table>
        <v-divider></v-divider>
        <v-container></v-container>
        <v-pagination v-model="page" :length="pageCount"></v-pagination>
      </v-card>
    </v-list>
  </v-app>
</template>

<script>
import http from "@/util/http-common.js";

export default {
  name: "MapDetail",
  props: {
    houseinfo: Object,
  },
  data() {
    return {
      dialShare: false,
      page: 1,
      pageCount: 0,
      itemsPerPage: 10,
      headers: [
        { text: "년", value: "dealYear" },
        { text: "월", value: "dealMonth" },
        { text: "일", value: "dealDay" },
        { text: "면적", value: "area" },
        { text: "층", value: "floor" },
      ],
      houseDeals: [],
    };
  },
  mounted() {
    const script = document.createElement("script");
    /* global kakao */
    script.onload = () => kakao.maps.load(this.initMap);
    script.src =
      "//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=***API-Key***";
    document.head.appendChild(script);
    var roadviewContainer = document.getElementById("roadview"); //로드뷰를 표시할 div
    var roadview = new kakao.maps.Roadview(roadviewContainer); //로드뷰 객체
    var roadviewClient = new kakao.maps.RoadviewClient(); //좌표로부터 로드뷰 파노ID를 가져올 로드뷰 helper객체
    var position = new kakao.maps.LatLng(
      this.houseinfo.lat,
      this.houseinfo.lng
    );
    // 특정 위치의 좌표와 가까운 로드뷰의 panoId를 추출하여 로드뷰를 띄운다.
    roadviewClient.getNearestPanoId(position, 300, function (panoId) {
      roadview.setPanoId(panoId, position); //panoId와 중심좌표를 통해 로드뷰 실행
    });
    http.get(`/map/detail/${this.houseinfo.aptCode}`).then((response) => {
      this.houseDeals = response.data;
    });
  },
};
</script>

<style>
.road-view {
  height: 150px;
}
</style>
