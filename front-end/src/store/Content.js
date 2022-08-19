import {request, requestImage} from "@/api";

const Content ={
  namespaced: true,

  state:{
    contents: null,
    contentDetail: null,
    contentComment: null,
    contentCommentSub: null,
  },
  getters : {

  },

  mutations : {
    contentList(state, payload){
      state.contents = payload;
      if(!payload){
        state.contents = null;
      }
    },
    contentDetailList(state, payload){
      state.contentDetail = payload;
      if(!payload){
        state.contentDetail = null;
      }
    },
    contentCommentList(state, payload){
      state.contentComment = payload;
      if(!payload){
        state.contentComment = null;
      }
    },
    contentCommentSubList(state, payload){
      state.contentCommentSub = payload;
      if(!payload){
        state.contentCommentSub = null;
      }
    }
  },
  actions : {
    // 게시글 불러오기
    async contentList({commit}){
    const Check = await request('post', '/content/list');
    await commit("contentList", Check.contentList);
    },

    // 게시글 자세히 보기(게시글 내용 불러오기)
    async contentDetailLoad({commit}, payload){
      const Check = await request('get', `/content/detail/${payload}`)
      await commit("contentDetailList", Check.contentList);
    },

    // 게시글 댓글 불러오기
    async contentCommentLoad({commit}, payload){
      const Check = await request('get', `/content/comment/${payload}`)
      await commit("contentCommentList", Check.contentCommentList)

    },

    // 게시글 대댓글 불러오기
    async contentCommentSubLoad({commit}, payload){
      const Check = await request('get', `/content/commentsub/${payload}`)
      await commit("contentCommentSubList", Check.contentCommentSubList)

    },
    // 게시글 작성
    async contentInsert({commit}, payload){
      await requestImage('post', '/content/insert', payload)
    },
    // 게시글 삭제
    async contentDelete({commit}, payload){
      await request('get', `/content/delete/${payload}`)
    },

    // 게시글 수정
    async contentUpdate({commit}, payload){
      await request('post', '/content/update', payload)
    },

    // 게시글 댓글 작성
    async contentCommentCreate({commit}, payload){
      await request('post', '/content/comment/create', payload)
    },

    // 게시글 대댓글 작성
    async contentSubCommentCreate({commit}, payload){
      await request('post', '/content/subcomment/create', payload)
    },

    // 댓글 삭제
    async commentDelete({commit}, payload){
      await request('get', `/content/comment/delete/${payload}`)
    },

    // 대댓글 삭제
    async subCommentDelete({commit}, payload){
      await request('get', `/content/subcomment/delete/${payload}`)
    },

    // 댓글
    async commentUpdate({commit}, payload){
      await request('post', '/content/comment/update', payload)
    },

    // 대댓글 수정
    async subCommentUpdate({commit}, payload){
      await request('post', '/content/subcomment/update', payload)
    },

  }
}

export default Content;
