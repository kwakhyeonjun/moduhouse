<template>
  <v-card align="center">
    <v-footer :padless="padless">
      <v-card flat tile width="100%" class="lighten-1 text-center">
        <v-card-text>
          <v-btn v-for="icon in icons" :key="icon.name" class="mx-4" icon>
            <v-icon size="24px" color="#a8e0ff" @click="moveLink(icon.url)">
              {{ icon.name }}
            </v-icon>
          </v-btn>
        </v-card-text>
        <v-card-text class="pt-0">
          <a @click="$router.push(`/notice/1`)"> 이용약관 | </a>
          <a @click="$router.push(`/notice/2`)"> 개인정보처리방침 </a>
          <br />
          본 사이트의 콘텐츠는 저작권법의 보호를 받는 바 무단 전재, 복사, 배포
          등을 금합니다. <br />Copyright © SAMSUNG All Rights Reserved
        </v-card-text>
        <v-divider></v-divider>

        <v-card-text>
          {{ new Date().getFullYear() }} — <strong>SSAFY 6</strong>
        </v-card-text>
      </v-card>
    </v-footer>
  </v-card>
</template>

<script>
export default {
  data: () => ({
    icons: [
      {
        name: "mdi-email",
        url: "https://www.google.com/intl/ko/gmail/about/#",
      },
      { name: "mdi-facebook", url: "https://www.facebook.com/" },
      { name: "mdi-twitter", url: "https://twitter.com/" },
      { name: "mdi-linkedin", url: "https://kr.linkedin.com/" },
    ],
    padless: true,
    variant: "fixed",
    index: "",
  }),
  methods: {
    moveLink(url) {
      window.open(url);
    },
  },
};
</script>

<style>
a {
  text-decoration: none;
}
</style>
