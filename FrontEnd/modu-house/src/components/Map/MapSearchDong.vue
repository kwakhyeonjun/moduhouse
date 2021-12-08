<template>
  <div id="resultBox">
    <v-container>
      <v-row>
        <v-col cols="4">
          <v-select
            v-model="sido"
            :items="sidos"
            label="시/도"
            solo
            @change="setGugunList"
            height="50px"
          ></v-select>
        </v-col>
        <v-col cols="4">
          <v-select
            v-model="gugun"
            :items="guguns"
            label="구/군"
            solo
            @change="setDongList"
            height="50px"
          ></v-select>
        </v-col>
        <v-col cols="4">
          <v-select
            v-model="dong"
            :items="dongs"
            label="동"
            solo
            @change="setAptList"
            height="50px"
          ></v-select>
        </v-col>
      </v-row>
    </v-container>

    <!-- if dark theme doesnt apply on this code : change div tag to v-/vuetify codes -->
    <div id="result" v-for="(house, index) in houseinfos" :key="index">
      <v-list-item-subtitle>
        <div :id="house.aptCode" class="road-view"></div>
      </v-list-item-subtitle>
      <v-list-item-content @click="getDetail(house.aptCode)">
        <v-list-item-title>{{ house.aptName }}</v-list-item-title>
        <v-list-item-subtitle
          >{{ house.sidoName }} {{ house.gugunName }} {{ house.dongName }}
        </v-list-item-subtitle>
        <v-list-item-subtitle
          >거래가 : {{ house.recentPrice }}만원</v-list-item-subtitle
        >
      </v-list-item-content>
      <v-divider></v-divider>
    </div>
  </div>
</template>

<script>
import http from "@/util/http-common.js";

export default {
  name: "MapSearchDong",
  data() {
    return {
      sidos: [],
      guguns: [],
      dongs: [],
      sido: "",
      gugun: "",
      dong: "",
      houseinfos: [],
    };
  },
  mounted() {
    const script = document.createElement("script");
    /* global kakao */
    script.onload = () => kakao.maps.load(this.initMap);
    script.src =
      "//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=***API-Key***";
    document.head.appendChild(script);
    http.get("/map/sido").then((response) => {
      response.data.forEach((sido) => {
        this.sidos.push({ value: sido.sidoCode, text: sido.sidoName });
      });
    });
  },
  methods: {
    setGugunList() {
      this.guguns = [];
      const params = { sido: this.sido };
      http.get("/map/gugun", { params }).then((response) => {
        response.data.forEach((gugun) => {
          this.guguns.push({ value: gugun.gugunCode, text: gugun.gugunName });
        });
      });
    },
    setDongList() {
      this.dongs = [];
      const params = { gugun: this.gugun };
      http.get("/map/dong", { params }).then((response) => {
        console.log(response);
        response.data.forEach((dong) => {
          this.dongs.push({ value: dong.dongCode, text: dong.dongName });
        });
      });
    },
    setAptList() {
      const params = { dong: this.dong };
      http.get("/map/apt", { params }).then(({ data }) => {
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

<style></style>
