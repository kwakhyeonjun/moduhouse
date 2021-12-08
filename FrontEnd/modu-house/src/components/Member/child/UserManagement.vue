<template>
  <v-container clipped-left>
    <v-card-title><h2>회원 정보 관리</h2></v-card-title>
    <!-- 

    id 누르면 작성 게시글페이지로 이동할 수 있게 하고, 시간 없으면 dummy data
    input 할ㅈ.ㅣ..
    <member-list></member-list>
    자꾸 두번씩 누르게 하넹..
    라우터 사용 & 따로 부르면
    테이블 때문일까..
    -->
    <router-view></router-view>
  </v-container>
</template>

<script>
export default {
  name: "UserManagement",
};
</script>

<style></style>
