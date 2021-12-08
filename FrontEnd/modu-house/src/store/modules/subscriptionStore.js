import http from "@/util/http-common.js";

const subscriptionStore = {
  namespaced: true,
  state: {
    subscriptions: ["dho", "anj"],
  },
  mutations: {
    SET_SUBSCRIPTIONS_LIST: (state, subscriptions) => {
      state.subscriptions = subscriptions;
    },
  },
  action: {
    getSubscriptions({ commit }) {
      console.log("getSubscriptions");
      http.get("/subs").then((response) => {
        console.log(response);
        commit("SET_SUBSCRIPTIONS_LIST", response.data);
      });
    },
  },
};

export default subscriptionStore;
