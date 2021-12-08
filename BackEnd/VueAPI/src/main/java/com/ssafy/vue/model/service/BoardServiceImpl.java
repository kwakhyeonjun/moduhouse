package com.ssafy.vue.model.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.util.PageNavigation;
import com.ssafy.vue.model.BoardDto;
import com.ssafy.vue.model.BoardParameterDto;
import com.ssafy.vue.model.CommentDto;
import com.ssafy.vue.model.mapper.DataBoardMapper;
import com.ssafy.vue.model.mapper.FreeBoardMapper;
import com.ssafy.vue.model.mapper.LocalBoardMapper;
import com.ssafy.vue.model.mapper.ShareBoardMapper;

@Service
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	private SqlSession sqlSession;

	
	/*
	 *	Free Board 
	 */
	@Override
	public boolean writeArticleFreeBoard(BoardDto boardDto) throws Exception {
		if(boardDto.getSubject() == null || boardDto.getContent() == null) {
			throw new Exception();
		}
		return sqlSession.getMapper(FreeBoardMapper.class).writeArticle(boardDto) == 1;
	}

	@Override
	public List<BoardDto> listArticleFreeBoard(BoardParameterDto boardParameterDto) throws Exception {
		int start = boardParameterDto.getPg() == 0 ? 0 : (boardParameterDto.getPg() - 1) * boardParameterDto.getSpp();
		boardParameterDto.setStart(start);
		return sqlSession.getMapper(FreeBoardMapper.class).listArticle(boardParameterDto);
	}

	@Override
	public PageNavigation makePageNavigationFreeBoard(BoardParameterDto boardParameterDto) throws Exception {
		int naviSize = 5;
		PageNavigation pageNavigation = new PageNavigation();
		pageNavigation.setCurrentPage(boardParameterDto.getPg());
		pageNavigation.setNaviSize(naviSize);
		int totalCount = sqlSession.getMapper(FreeBoardMapper.class).getTotalCount(boardParameterDto);//총글갯수  269
		pageNavigation.setTotalCount(totalCount);  
		int totalPageCount = (totalCount - 1) / boardParameterDto.getSpp() + 1;//27
		pageNavigation.setTotalPageCount(totalPageCount);
		boolean startRange = boardParameterDto.getPg() <= naviSize;
		pageNavigation.setStartRange(startRange);
		boolean endRange = (totalPageCount - 1) / naviSize * naviSize < boardParameterDto.getPg();
		pageNavigation.setEndRange(endRange);
		pageNavigation.makeNavigator();
		return pageNavigation;
	}

	@Override
	public BoardDto getArticleFreeBoard(int articleno) throws Exception {
		return sqlSession.getMapper(FreeBoardMapper.class).getArticle(articleno);
	}
	
	@Override
	public void updateHitFreeBoard(int articleno) throws Exception {
		sqlSession.getMapper(FreeBoardMapper.class).updateHit(articleno);
	}

	@Override
	@Transactional
	public boolean modifyArticleFreeBoard(BoardDto boardDto) throws Exception {
		return sqlSession.getMapper(FreeBoardMapper.class).modifyArticle(boardDto) == 1;
	}

	@Override
	@Transactional
	public boolean deleteArticleFreeBoard(int articleno) throws Exception {
		return sqlSession.getMapper(FreeBoardMapper.class).deleteArticle(articleno) == 1;
	}
	
	@Override
	public List<CommentDto> getCommentFreeBoard(int articleno) throws Exception {
		return sqlSession.getMapper(FreeBoardMapper.class).getComment(articleno);
	}
	
	@Override
	public boolean writeCommentFreeBoard(CommentDto commentDto) throws Exception {
		return sqlSession.getMapper(FreeBoardMapper.class).writeComment(commentDto) == 1;
	}
	
	@Override
	public boolean modifyCommentFreeBoard(CommentDto commentDto) throws Exception {
		return sqlSession.getMapper(FreeBoardMapper.class).modifyComment(commentDto) == 1;
	}
	
	@Override
	@Transactional
	public boolean deleteCommentFreeBoard(int commentno) throws Exception {
		return sqlSession.getMapper(FreeBoardMapper.class).deleteComment(commentno) == 1;
	}
	
	/*
	 *	Data Board 
	 */
	@Override
	public boolean writeArticleDataBoard(BoardDto boardDto) throws Exception {
		if(boardDto.getSubject() == null || boardDto.getContent() == null) {
			throw new Exception();
		}
		return sqlSession.getMapper(DataBoardMapper.class).writeArticle(boardDto) == 1;
	}

	@Override
	public List<BoardDto> listArticleDataBoard(BoardParameterDto boardParameterDto) throws Exception {
		int start = boardParameterDto.getPg() == 0 ? 0 : (boardParameterDto.getPg() - 1) * boardParameterDto.getSpp();
		boardParameterDto.setStart(start);
		return sqlSession.getMapper(DataBoardMapper.class).listArticle(boardParameterDto);
	}

	@Override
	public PageNavigation makePageNavigationDataBoard(BoardParameterDto boardParameterDto) throws Exception {
		int naviSize = 5;
		PageNavigation pageNavigation = new PageNavigation();
		pageNavigation.setCurrentPage(boardParameterDto.getPg());
		pageNavigation.setNaviSize(naviSize);
		int totalCount = sqlSession.getMapper(DataBoardMapper.class).getTotalCount(boardParameterDto);//총글갯수  269
		pageNavigation.setTotalCount(totalCount);  
		int totalPageCount = (totalCount - 1) / boardParameterDto.getSpp() + 1;//27
		pageNavigation.setTotalPageCount(totalPageCount);
		boolean startRange = boardParameterDto.getPg() <= naviSize;
		pageNavigation.setStartRange(startRange);
		boolean endRange = (totalPageCount - 1) / naviSize * naviSize < boardParameterDto.getPg();
		pageNavigation.setEndRange(endRange);
		pageNavigation.makeNavigator();
		return pageNavigation;
	}

	@Override
	public BoardDto getArticleDataBoard(int articleno) throws Exception {
		return sqlSession.getMapper(DataBoardMapper.class).getArticle(articleno);
	}
	
	@Override
	public void updateHitDataBoard(int articleno) throws Exception {
		sqlSession.getMapper(DataBoardMapper.class).updateHit(articleno);
	}

	@Override
	@Transactional
	public boolean modifyArticleDataBoard(BoardDto boardDto) throws Exception {
		return sqlSession.getMapper(DataBoardMapper.class).modifyArticle(boardDto) == 1;
	}

	@Override
	@Transactional
	public boolean deleteArticleDataBoard(int articleno) throws Exception {
		return sqlSession.getMapper(DataBoardMapper.class).deleteArticle(articleno) == 1;
	}
	
	@Override
	public List<CommentDto> getCommentDataBoard(int articleno) throws Exception {
		return sqlSession.getMapper(DataBoardMapper.class).getComment(articleno);
	}
	
	@Override
	public boolean writeCommentDataBoard(CommentDto commentDto) throws Exception {
		return sqlSession.getMapper(DataBoardMapper.class).writeComment(commentDto) == 1;
	}
	
	@Override
	public boolean modifyCommentDataBoard(CommentDto commentDto) throws Exception {
		return sqlSession.getMapper(DataBoardMapper.class).modifyComment(commentDto) == 1;
	}
	
	@Override
	@Transactional
	public boolean deleteCommentDataBoard(int commentno) throws Exception {
		return sqlSession.getMapper(DataBoardMapper.class).deleteComment(commentno) == 1;
	}
	
	/*
	 *	Share Board 
	 */
	@Override
	public boolean writeArticleShareBoard(BoardDto boardDto) throws Exception {
		if(boardDto.getSubject() == null || boardDto.getContent() == null) {
			throw new Exception();
		}
		return sqlSession.getMapper(ShareBoardMapper.class).writeArticle(boardDto) == 1;
	}

	@Override
	public List<BoardDto> listArticleShareBoard(BoardParameterDto boardParameterDto) throws Exception {
		int start = boardParameterDto.getPg() == 0 ? 0 : (boardParameterDto.getPg() - 1) * boardParameterDto.getSpp();
		boardParameterDto.setStart(start);
		return sqlSession.getMapper(ShareBoardMapper.class).listArticle(boardParameterDto);
	}

	@Override
	public PageNavigation makePageNavigationShareBoard(BoardParameterDto boardParameterDto) throws Exception {
		int naviSize = 5;
		PageNavigation pageNavigation = new PageNavigation();
		pageNavigation.setCurrentPage(boardParameterDto.getPg());
		pageNavigation.setNaviSize(naviSize);
		int totalCount = sqlSession.getMapper(ShareBoardMapper.class).getTotalCount(boardParameterDto);//총글갯수  269
		pageNavigation.setTotalCount(totalCount);  
		int totalPageCount = (totalCount - 1) / boardParameterDto.getSpp() + 1;//27
		pageNavigation.setTotalPageCount(totalPageCount);
		boolean startRange = boardParameterDto.getPg() <= naviSize;
		pageNavigation.setStartRange(startRange);
		boolean endRange = (totalPageCount - 1) / naviSize * naviSize < boardParameterDto.getPg();
		pageNavigation.setEndRange(endRange);
		pageNavigation.makeNavigator();
		return pageNavigation;
	}

	@Override
	public BoardDto getArticleShareBoard(int articleno) throws Exception {
		return sqlSession.getMapper(ShareBoardMapper.class).getArticle(articleno);
	}
	
	@Override
	public void updateHitShareBoard(int articleno) throws Exception {
		sqlSession.getMapper(ShareBoardMapper.class).updateHit(articleno);
	}

	@Override
	@Transactional
	public boolean modifyArticleShareBoard(BoardDto boardDto) throws Exception {
		return sqlSession.getMapper(ShareBoardMapper.class).modifyArticle(boardDto) == 1;
	}

	@Override
	@Transactional
	public boolean deleteArticleShareBoard(int articleno) throws Exception {
		return sqlSession.getMapper(ShareBoardMapper.class).deleteArticle(articleno) == 1;
	}
	
	@Override
	public List<CommentDto> getCommentShareBoard(int articleno) throws Exception {
		return sqlSession.getMapper(ShareBoardMapper.class).getComment(articleno);
	}
	
	@Override
	public boolean writeCommentShareBoard(CommentDto commentDto) throws Exception {
		return sqlSession.getMapper(ShareBoardMapper.class).writeComment(commentDto) == 1;
	}
	
	@Override
	public boolean modifyCommentShareBoard(CommentDto commentDto) throws Exception {
		return sqlSession.getMapper(ShareBoardMapper.class).modifyComment(commentDto) == 1;
	}
	
	@Override
	@Transactional
	public boolean deleteCommentShareBoard(int commentno) throws Exception {
		return sqlSession.getMapper(ShareBoardMapper.class).deleteComment(commentno) == 1;
	}
	
	/*
	 *	Area Board 
	 */
	@Override
	public boolean writeArticleAreaBoard(BoardDto boardDto) throws Exception {
		if(boardDto.getSubject() == null || boardDto.getContent() == null) {
			throw new Exception();
		}
		return sqlSession.getMapper(LocalBoardMapper.class).writeArticle(boardDto) == 1;
	}

	@Override
	public List<BoardDto> listArticleAreaBoard(BoardParameterDto boardParameterDto) throws Exception {
		int start = boardParameterDto.getPg() == 0 ? 0 : (boardParameterDto.getPg() - 1) * boardParameterDto.getSpp();
		boardParameterDto.setStart(start);
		return sqlSession.getMapper(LocalBoardMapper.class).listArticle(boardParameterDto);
	}

	@Override
	public PageNavigation makePageNavigationAreaBoard(BoardParameterDto boardParameterDto) throws Exception {
		int naviSize = 5;
		PageNavigation pageNavigation = new PageNavigation();
		pageNavigation.setCurrentPage(boardParameterDto.getPg());
		pageNavigation.setNaviSize(naviSize);
		int totalCount = sqlSession.getMapper(LocalBoardMapper.class).getTotalCount(boardParameterDto);//총글갯수  269
		pageNavigation.setTotalCount(totalCount);  
		int totalPageCount = (totalCount - 1) / boardParameterDto.getSpp() + 1;//27
		pageNavigation.setTotalPageCount(totalPageCount);
		boolean startRange = boardParameterDto.getPg() <= naviSize;
		pageNavigation.setStartRange(startRange);
		boolean endRange = (totalPageCount - 1) / naviSize * naviSize < boardParameterDto.getPg();
		pageNavigation.setEndRange(endRange);
		pageNavigation.makeNavigator();
		return pageNavigation;
	}

	@Override
	public BoardDto getArticleAreaBoard(int articleno) throws Exception {
		return sqlSession.getMapper(LocalBoardMapper.class).getArticle(articleno);
	}
	
	@Override
	public void updateHitAreaBoard(int articleno) throws Exception {
		sqlSession.getMapper(LocalBoardMapper.class).updateHit(articleno);
	}

	@Override
	@Transactional
	public boolean modifyArticleAreaBoard(BoardDto boardDto) throws Exception {
		return sqlSession.getMapper(LocalBoardMapper.class).modifyArticle(boardDto) == 1;
	}

	@Override
	@Transactional
	public boolean deleteArticleAreaBoard(int articleno) throws Exception {
		return sqlSession.getMapper(LocalBoardMapper.class).deleteArticle(articleno) == 1;
	}
	
	@Override
	public List<CommentDto> getCommentAreaBoard(int articleno) throws Exception {
		return sqlSession.getMapper(LocalBoardMapper.class).getComment(articleno);
	}
	
	@Override
	public boolean writeCommentAreaBoard(CommentDto commentDto) throws Exception {
		return sqlSession.getMapper(LocalBoardMapper.class).writeComment(commentDto) == 1;
	}
	
	@Override
	public boolean modifyCommentAreaBoard(CommentDto commentDto) throws Exception {
		return sqlSession.getMapper(LocalBoardMapper.class).modifyComment(commentDto) == 1;
	}
	
	@Override
	@Transactional
	public boolean deleteCommentAreaBoard(int commentno) throws Exception {
		return sqlSession.getMapper(LocalBoardMapper.class).deleteComment(commentno) == 1;
	}
	
	
}