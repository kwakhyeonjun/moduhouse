<template>
  <div id="resultBox">
    <v-row>
      <v-container>
        <v-col cols="12" sm="11">
          <v-text-field
            height="50px"
            full-width
            solo
            label="지역 | 아파트 검색"
            prepend-icon="mdi-map-marker"
            v-model="searchWord"
            v-on:keyup.13="search()"
            :color="click ? '#a8e0ff' : '#64B5F6'"
          ></v-text-field>
        </v-col>
      </v-container>
    </v-row>
    <v-col cols="12" sm="6"></v-col>

    <v-list>
      <!-- if dark theme doesnt apply on this code : change div tag to v-/vuetify codes -->
      <div id="result" v-for="(house, index) in houseinfos" :key="index">
        <v-list-item-subtitle>
          <div :id="house.aptCode" class="road-view"></div>
        </v-list-item-subtitle>
        <v-list-item-content @click="getDetail(house.aptCode)">
          <v-list-item-title>
            {{ house.aptName }}
          </v-list-item-title>
          <v-list-item-subtitle
            >{{ house.sidoName }} {{ house.gugunName }} {{ house.dongName }}
          </v-list-item-subtitle>
          <v-list-item-subtitle
            >거래가 : {{ house.recentPrice }}만원</v-list-item-subtitle
          >
        </v-list-item-content>
        <v-divider></v-divider>
      </div>
    </v-list>
  </div>
</template>

<script>
import http from "@/util/http-common.js";
import { mapGetters, mapState } from "vuex";

export default {
  name: "MapSearchName",
  data() {
    return {
      searchWord: "",
      active: false,
      houseinfos: [],
    };
  },
  created() {
    const script = document.createElement("script");
    /* global kakao */
    script.onload = () => kakao.maps.load(this.initMap);
    script.src =
      "//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=***API-Key***";
    document.head.appendChild(script);
  },
  computed: {
    ...mapState(["mainSearchWord"]),
    ...mapGetters(["getWord"]),
  },
  methods: {
    search() {
      console.log(this.getWord);
      const aptName = this.searchWord;
      http.post("/map", { aptName }).then(({ data }) => {
        this.houseinfos = data;
        this.$emit("houseUpdate", data);
        setTimeout(() => {
          this.houseinfos.forEach((house) => {
            var roadviewContainer = document.getElementById(house.aptCode); //로드뷰를 표시할 div
            var roadview = new kakao.maps.Roadview(roadviewContainer); //로드뷰 객체
            var roadviewClient = new kakao.maps.RoadviewClient(); //좌표로부터 로드뷰 파노ID를 가져올 로드뷰 helper객체
            var position = new kakao.maps.LatLng(house.lat, house.lng);
            // 특정 위치의 좌표와 가까운 로드뷰의 panoId를 추출하여 로드뷰를 띄운다.
            roadviewClient.getNearestPanoId(position, 300, function (panoId) {
              roadview.setPanoId(panoId, position); //panoId와 중심좌표를 통해 로드뷰 실행
            });
          });
        }, 1000);
      });
    },
    getDetail(aptCode) {
      var houseDetail;
      this.houseinfos.forEach((house) => {
        if (house.aptCode == aptCode) {
          houseDetail = house;
        }
      });
      this.$emit("houseDetail", houseDetail);
      this.$router.push({ name: "MapDetail" });
    },
  },
};
</script>

<style>
html {
  overflow-y: auto;
}
.v-text-field {
  padding-top: 6px;
}
#result {
  margin: 5%;
}
#resultBox {
  box-sizing: border-box;
  height: 100vh;
  overflow: auto;
}
.v-text-field {
  padding-top: 10px;
  width: 300px;
}
.road-view {
  height: 150px;
}
</style>
