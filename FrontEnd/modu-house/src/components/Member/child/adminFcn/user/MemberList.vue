<template>
  <v-container>
    <v-data-table
      v-model="selected"
      @input="enterSelect()"
      :headers="headers"
      :items="members"
      single-select
      item-key="userid"
      show-select
      class="elevation-1"
      :search="search"
      :sort-by.sync="sortBy"
      :sort-desc.sync="sortDesc"
    >
      <!-- 나중에 시간 나면 :single-select="single-select"로 바꾸고 multiple delete로..-->
      <template v-slot:top>
        <v-text-field
          v-model="search"
          label="아이디, 이름, 이메일 검색"
          class="mx-8"
        ></v-text-field>
      </template>
      <!--search ends here-->
      <template #item.email="{ value }">
        <a :href="`mailto:${value}`"> {{ value }} </a>
      </template>

      <template #item.status="{ value }">
        <v-row>
          <v-chip v-if="value === 'inactive'" color="grey" text-color="white">
            {{ value }}
          </v-chip>
          <v-chip
            v-else
            color="blue lighten-1"
            text-color="white"
            text-align="center"
          >
            ✓ active
          </v-chip>
        </v-row>
      </template>
    </v-data-table>
    <v-col><v-col md="8" /></v-col>
    <v-container>
      <v-row>
        <v-col md="6" />
        <v-btn @click="goodbye"> 회원 비활성화 </v-btn>
      </v-row>
    </v-container>
  </v-container>
</template>

<script>
import { deleteUser, userList } from "@/api/member";
export default {
  name: "MemberList",
  methods: {
    goodbye() {
      alert("회원을 비활성화 시켰습니다.");
      deleteUser(this.checked);
      location.reload();
    },
    //select 시 userid 가져오는거 확인
    enterSelect() {
      console.log(this.selected.map((e) => e.userid));
      this.checked = this.selected.map((e) => e.userid);
    },
  },

  data() {
    return {
      checked: "",
      search: "",
      page: "",
      sortBy: "status",
      sortDesc: false,
      headers: [
        {
          text: "회원 아이디",
          align: "start",
          value: "userid",
        },
        { text: "이름", value: "username" },
        { text: "이메일", value: "email" },
        { text: "가입 날짜", value: "joindate" },
        { text: "status", value: "status", sortable: true },
      ],

      members: [],
    };
  },
  created() {
    let param = {
      pg: 1,
      spp: 20,
      key: null,
      word: null,
    };

    userList(
      param,
      (response) => {
        this.members = response.data;
      },
      (error) => {
        console.log(error);
      }
    );
  },
};
</script>

<style></style>
