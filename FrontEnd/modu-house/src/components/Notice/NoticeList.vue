<template>
  <v-app>
    <h2>공지사항</h2>
    <v-divider></v-divider>
    <!-- local board list 를 불러온다 
    <board-list-util type="notice"></board-list-util>
    -->
    <v-container style="max-width: 1200px; position: relative">
      <v-data-table
        :headers="headers"
        :items="boards"
        item-key="articleno"
        :search="search"
        :page.sync="page"
        :items-per-page="itemsPerPage"
        hide-default-footer
        @page-count="pageCount = $event"
      >
        <!--  @click="$router.push(`/notice/${item.articleno}`)"-->
        <template v-slot:item.subject="{ item }">
          <a
            @click="$router.push(`/notice/${item.articleno}`)"
            style="text-decoration: none; color: inherit"
          >
            {{ item.subject }}
          </a>
        </template>
      </v-data-table>
      <br />
      <div class="text-center pt-2">
        <v-pagination
          v-model="page"
          :length="pageCount"
          color="blue lighten-1"
        ></v-pagination>
      </div>
      <template>
        <v-row>
          <v-col md="9"></v-col>
          <v-text-field
            v-model="search"
            label="🔍게시글 번호, 아이디, 제목으로 검색"
          ></v-text-field>
        </v-row>
      </template>
    </v-container>
  </v-app>
</template>

<script>
//import NoticeListRow from "./child/NoticeListRow.vue";
//import { listNotice } from "@/api/notice";
//import BoardListUtil from "@/components/Board/BoardList/BoardListUtil.vue";

export default {
  name: "NoticeList",
  data() {
    return {
      target_URL: "",
      page: 1,
      pageCount: 0,
      itemsPerPage: 15,
      checked: "",
      search: "",
      headers: [
        { text: "번호", value: "articleno", width: "5px", align: "center" },
        { text: "제목", value: "subject", width: "90%", align: "center" },
        { text: "내용", value: "content", align: " d-none" },
      ],

      boards: [
        {
          articleno: 1,
          subject: "이용약관",
          content: "내용1",
        },
        {
          articleno: 2,
          subject: "개인정보 처리방침",
          content: "내용2",
        },
      ],
    };
  },
};
</script>

<style></style>
