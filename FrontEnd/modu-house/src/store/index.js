import Vue from "vue";
import Vuex from "vuex";
import createPersistedState from "vuex-persistedstate";

Vue.use(Vuex);
import memberStore from "@/store/modules/memberStore.js";
import mapStore from "@/store/modules/mapStore.js";
import subscriptionStore from "@/store/modules/subscriptionStore.js";

const store = new Vuex.Store({
  modules: {
    memberStore,
    mapStore,
    subscriptionStore,
  },
  plugins: [
    createPersistedState({
      storage: sessionStorage,
    }),
  ],
});
export default store;
