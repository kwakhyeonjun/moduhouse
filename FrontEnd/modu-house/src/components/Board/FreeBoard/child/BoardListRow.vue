<template>
  <tr>
    <td v-if="type === 'detail'">{{ articleno }}</td>
    <td>
      <router-link
        class="link_title"
        style="text-decoration: none; color: inherit"
        :to="{
          name: 'FreeBoardView',
          params: { articleno: articleno },
        }"
      >
        {{ subject }}
      </router-link>
    </td>
    <td v-if="type === 'detail'">{{ userid }}</td>
    <td>
      <v-icon class="material-icons-outlined"> visibility </v-icon> {{ hit }}
    </td>
  </tr>
</template>

<script>
export default {
  name: "BoardListRow",

  props: {
    articleno: Number,
    userid: String,
    subject: String,
    hit: Number,
    regtime: String,
    type: { type: String },
  },
};
</script>

<style>
.link_title {
  color: white;
}
</style>
