package com.ssafy.vue.model.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.ssafy.util.PageNavigation;
import com.ssafy.vue.model.BoardDto;
import com.ssafy.vue.model.BoardParameterDto;
import com.ssafy.vue.model.CommentDto;

public interface BoardService {

	/*
	 *	Free Board 
	 */
	boolean writeArticleFreeBoard(BoardDto boardDto) throws Exception;

	List<BoardDto> listArticleFreeBoard(BoardParameterDto boardParameterDto) throws Exception;

	PageNavigation makePageNavigationFreeBoard(BoardParameterDto boardParameterDto) throws Exception;

	BoardDto getArticleFreeBoard(int articleno) throws Exception;

	void updateHitFreeBoard(int articleno) throws Exception;

	boolean modifyArticleFreeBoard(BoardDto boardDto) throws Exception;

	boolean deleteArticleFreeBoard(int articleno) throws Exception;

	List<CommentDto> getCommentFreeBoard(int articleno) throws Exception;

	boolean writeCommentFreeBoard(CommentDto commentDto) throws Exception;

	boolean modifyCommentFreeBoard(CommentDto commentDto) throws Exception;

	boolean deleteCommentFreeBoard(int commentno) throws Exception;

	/*
	 *	Data Board 
	 */
	boolean writeArticleDataBoard(BoardDto boardDto) throws Exception;

	List<BoardDto> listArticleDataBoard(BoardParameterDto boardParameterDto) throws Exception;

	PageNavigation makePageNavigationDataBoard(BoardParameterDto boardParameterDto) throws Exception;

	BoardDto getArticleDataBoard(int articleno) throws Exception;

	void updateHitDataBoard(int articleno) throws Exception;

	boolean modifyArticleDataBoard(BoardDto boardDto) throws Exception;

	boolean deleteArticleDataBoard(int articleno) throws Exception;

	List<CommentDto> getCommentDataBoard(int articleno) throws Exception;

	boolean writeCommentDataBoard(CommentDto commentDto) throws Exception;

	boolean modifyCommentDataBoard(CommentDto commentDto) throws Exception;

	boolean deleteCommentDataBoard(int commentno) throws Exception;

	/*
	 *	Share Board 
	 */
	boolean writeArticleShareBoard(BoardDto boardDto) throws Exception;

	List<BoardDto> listArticleShareBoard(BoardParameterDto boardParameterDto) throws Exception;

	PageNavigation makePageNavigationShareBoard(BoardParameterDto boardParameterDto) throws Exception;

	BoardDto getArticleShareBoard(int articleno) throws Exception;

	void updateHitShareBoard(int articleno) throws Exception;

	boolean modifyArticleShareBoard(BoardDto boardDto) throws Exception;

	boolean deleteArticleShareBoard(int articleno) throws Exception;

	List<CommentDto> getCommentShareBoard(int articleno) throws Exception;

	boolean writeCommentShareBoard(CommentDto commentDto) throws Exception;

	boolean modifyCommentShareBoard(CommentDto commentDto) throws Exception;

	boolean deleteCommentShareBoard(int commentno) throws Exception;

	/*
	 *	Area Board 
	 */
	boolean writeArticleAreaBoard(BoardDto boardDto) throws Exception;

	List<BoardDto> listArticleAreaBoard(BoardParameterDto boardParameterDto) throws Exception;

	PageNavigation makePageNavigationAreaBoard(BoardParameterDto boardParameterDto) throws Exception;

	BoardDto getArticleAreaBoard(int articleno) throws Exception;

	void updateHitAreaBoard(int articleno) throws Exception;

	boolean modifyArticleAreaBoard(BoardDto boardDto) throws Exception;

	boolean deleteArticleAreaBoard(int articleno) throws Exception;

	List<CommentDto> getCommentAreaBoard(int articleno) throws Exception;

	boolean writeCommentAreaBoard(CommentDto commentDto) throws Exception;

	boolean modifyCommentAreaBoard(CommentDto commentDto) throws Exception;

	boolean deleteCommentAreaBoard(int commentno) throws Exception;

}