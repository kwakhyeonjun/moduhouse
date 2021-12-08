package com.ssafy.vue.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.vue.model.BoardDto;
import com.ssafy.vue.model.BoardParameterDto;
import com.ssafy.vue.model.CommentDto;
import com.ssafy.vue.model.service.BoardService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.models.auth.In;

//http://localhost:9999/vue/swagger-ui.html
//@CrossOrigin(origins = { "*" }, methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.POST} , maxAge = 6000)
@RestController
@RequestMapping("/board")
@Api("게시판 컨트롤러  API V1")
public class BoardController {

	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	private BoardService boardService;

	/*
	 *	FreeBoard 
	 */
	@ApiOperation(value = "자유게시판 글작성", notes = "새로운 게시글 정보를 입력한다. 그리고 DB입력 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PostMapping("/free")
	public ResponseEntity<String> writeArticleFreeBoard(@RequestBody @ApiParam(value = "게시글 정보.", required = true) BoardDto boardDto) throws Exception {
		logger.info("writeArticle - 호출");
		if (boardService.writeArticleFreeBoard(boardDto)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation(value = "자유게시판 글목록", notes = "모든 자유게시판 게시글의 정보를 반환한다.", response = List.class)
	@GetMapping("/free")
	public ResponseEntity<List<BoardDto>> listArticleFreeBoard(@ApiParam(value = "게시글을 얻기위한 부가정보.", required = true) BoardParameterDto boardParameterDto) throws Exception {
		logger.info("listArticle - 호출");
		return new ResponseEntity<List<BoardDto>>(boardService.listArticleFreeBoard(boardParameterDto), HttpStatus.OK);
	}
	
	@ApiOperation(value = "자유게시판 글보기", notes = "글번호에 해당하는 게시글의 정보를 반환한다.", response = BoardDto.class)
	@GetMapping("/free/{articleno}")
	public ResponseEntity<BoardDto> getArticleFreeBoard(@PathVariable("articleno") @ApiParam(value = "얻어올 글의 글번호.", required = true) int articleno) throws Exception {
		logger.info("getArticle - 호출 : " + articleno);
		boardService.updateHitFreeBoard(articleno);
		return new ResponseEntity<BoardDto>(boardService.getArticleFreeBoard(articleno), HttpStatus.OK);
	}
	
	@ApiOperation(value = "자유게시판 글수정", notes = "새로운 자유게시판의 게시글 정보를 입력한다. 그리고 DB수정 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PutMapping("/free")
	public ResponseEntity<String> modifyArticleFreeBoard(@RequestBody @ApiParam(value = "수정할 글정보.", required = true) BoardDto boardDto) throws Exception {
		logger.info("modifyArticle - 호출");
		
		if (boardService.modifyArticleFreeBoard(boardDto)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.OK);
	}
	
	@ApiOperation(value = "자유게시판 글삭제", notes = "글번호에 해당하는 자유게시판 게시글의 정보를 삭제한다. 그리고 DB삭제 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@DeleteMapping("/free/{articleno}")
	public ResponseEntity<String> deleteArticleFreeBoard(@PathVariable("articleno") @ApiParam(value = "살제할 글의 글번호.", required = true) int articleno) throws Exception {
		logger.info("deleteArticle - 호출");
		if (boardService.deleteArticleFreeBoard(articleno)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	/* 댓글 */
	
	@ApiOperation(value = "자유게시판 댓글리스트", notes = "글번호에 해당하는 댓글의 정보를 반환한다.", response = CommentDto.class)
	@GetMapping("/free/comment/{articleno}")
	public ResponseEntity<List<CommentDto>> getCommentFreeBoard(@PathVariable("articleno") @ApiParam(value = "글번호.", required = true) int articleno) throws Exception {
		logger.info("getCommentFreeBoard - 호출 : " + articleno);
		return new ResponseEntity<List<CommentDto>>(boardService.getCommentFreeBoard(articleno), HttpStatus.OK);
	}
	
	@ApiOperation(value = "자유게시판 댓글작성", notes = "글번호에 해당하는 게시글에 댓글을 작성한다.", response = CommentDto.class)
	@PostMapping("/free/comment")
	public ResponseEntity<String> writeCommentFreeBoard(@RequestBody @ApiParam(value = "게시글 정보.", required = true) CommentDto commentDto) throws Exception {
		logger.info("writeCommentFreeBoard - 호출 : " + commentDto.getArticleno());
		if(boardService.writeCommentFreeBoard(commentDto)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation(value = "자유게시판 댓글수정", notes = "글번호에 해당하는 게시글에 댓글을 수정한다.", response = CommentDto.class)
	@PutMapping("/free/comment/{articleno}")
	public ResponseEntity<String> modifyCommentFreeBoard(@PathVariable("articleno") @ApiParam(value = "글번호.", required = true) CommentDto commentDto) throws Exception {
		logger.info("modifyCommentFreeBoard - 호출 : " + commentDto.getArticleno());
		if(boardService.modifyCommentFreeBoard(commentDto)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation(value = "자유게시판 댓글 삭제", notes = "글번호에 해당하는 자유게시판의 댓글을 삭제한다. 그리고 DB삭제 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@DeleteMapping("/free/comment/{commentno}")
	public ResponseEntity<String> deleteCommentFreeBoard(@PathVariable("commentno") @ApiParam(value = "살제할 글의 글번호.", required = true) int commentno) throws Exception {
		logger.info("deleteCommentFreeBoard - 호출");
		if (boardService.deleteCommentFreeBoard(commentno)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	/*
	 *	DataBoard 
	 */
	@ApiOperation(value = "자료게시판 글작성", notes = "새로운 자료게시판 게시글 정보를 입력한다. 그리고 DB입력 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PostMapping("/data")
	public ResponseEntity<String> writeArticleDataBoard(@RequestBody @ApiParam(value = "게시글 정보.", required = true) BoardDto boardDto) throws Exception {
		logger.info("writeArticle - 호출");
		if (boardService.writeArticleDataBoard(boardDto)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation(value = "자료게시판 글목록", notes = "모든 자료게시판 게시글의 정보를 반환한다.", response = List.class)
	@GetMapping("/data")
	public ResponseEntity<List<BoardDto>> listArticleDataBoard(@ApiParam(value = "게시글을 얻기위한 부가정보.", required = true) BoardParameterDto boardParameterDto) throws Exception {
		logger.info("listArticle - 호출");
		return new ResponseEntity<List<BoardDto>>(boardService.listArticleDataBoard(boardParameterDto), HttpStatus.OK);
	}
	
	@ApiOperation(value = "자료게시판 글보기", notes = "글번호에 해당하는 자료게시판의 게시글의 정보를 반환한다.", response = BoardDto.class)
	@GetMapping("/data/{articleno}")
	public ResponseEntity<BoardDto> getArticleDataBoard(@PathVariable("articleno") @ApiParam(value = "얻어올 글의 글번호.", required = true) int articleno) throws Exception {
		logger.info("getArticle - 호출 : " + articleno);
		boardService.updateHitDataBoard(articleno);
		return new ResponseEntity<BoardDto>(boardService.getArticleDataBoard(articleno), HttpStatus.OK);
	}
	
	@ApiOperation(value = "자료게시판 글수정", notes = "자료게시판에 새로운 게시글 정보를 입력한다. 그리고 DB수정 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PutMapping("/data")
	public ResponseEntity<String> modifyArticleDataBoard(@RequestBody @ApiParam(value = "수정할 글정보.", required = true) BoardDto boardDto) throws Exception {
		logger.info("modifyArticle - 호출");
		
		if (boardService.modifyArticleDataBoard(boardDto)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.OK);
	}
	
	@ApiOperation(value = "게시판 글삭제", notes = "글번호에 해당하는 게시글의 정보를 삭제한다. 그리고 DB삭제 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@DeleteMapping("/data/{articleno}")
	public ResponseEntity<String> deleteArticleDataBoard(@PathVariable("articleno") @ApiParam(value = "살제할 글의 글번호.", required = true) int articleno) throws Exception {
		logger.info("deleteArticle - 호출");
		if (boardService.deleteArticleDataBoard(articleno)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
/* 댓글 */
	
	@ApiOperation(value = "자료게시판 댓글리스트", notes = "글번호에 해당하는 댓글의 정보를 반환한다.", response = CommentDto.class)
	@GetMapping("/data/comment/{articleno}")
	public ResponseEntity<List<CommentDto>> getCommentDataBoard(@PathVariable("articleno") @ApiParam(value = "글번호.", required = true) int articleno) throws Exception {
		logger.info("getCommentDataBoard - 호출 : " + articleno);
		return new ResponseEntity<List<CommentDto>>(boardService.getCommentDataBoard(articleno), HttpStatus.OK);
	}
	
	@ApiOperation(value = "자료게시판 댓글작성", notes = "글번호에 해당하는 게시글에 댓글을 작성한다.", response = CommentDto.class)
	@PostMapping("/data/comment")
	public ResponseEntity<String> writeCommentDataBoard(@RequestBody @ApiParam(value = "게시글 정보.", required = true) CommentDto commentDto) throws Exception {
		logger.info("writeCommentFreeBoard - 호출 : " + commentDto.getArticleno());
		if(boardService.writeCommentDataBoard(commentDto)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation(value = "자료게시판 댓글수정", notes = "글번호에 해당하는 게시글에 댓글을 수정한다.", response = CommentDto.class)
	@PutMapping("/data/comment/{articleno}")
	public ResponseEntity<String> modifyCommentDataBoard(@PathVariable("articleno") @ApiParam(value = "글번호.", required = true) CommentDto commentDto) throws Exception {
		logger.info("modifyCommentDataBoard - 호출 : " + commentDto.getArticleno());
		if(boardService.modifyCommentDataBoard(commentDto)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation(value = "자료게시판 댓글 삭제", notes = "글번호에 해당하는 자료게시판의 댓글을 삭제한다. 그리고 DB삭제 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@DeleteMapping("/data/comment/{commentno}")
	public ResponseEntity<String> deleteCommentDataBoard(@PathVariable("commentno") @ApiParam(value = "살제할 글의 글번호.", required = true) int commentno) throws Exception {
		logger.info("deleteCommentDataBoard - 호출");
		if (boardService.deleteCommentDataBoard(commentno)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	/*
	 *	AreaBoard 
	 */
	@ApiOperation(value = "지역게시판 글작성", notes = "새로운 게시글 정보를 입력한다. 그리고 DB입력 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PostMapping("/area")
	public ResponseEntity<String> writeArticleAreaBoard(@RequestBody @ApiParam(value = "게시글 정보.", required = true) BoardDto boardDto) throws Exception {
		logger.info("writeArticle - 호출");
		if (boardService.writeArticleAreaBoard(boardDto)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation(value = "지역게시판 글목록", notes = "지역 게시판의 모든 게시글의 정보를 반환한다.", response = List.class)
	@GetMapping("/area")
	public ResponseEntity<List<BoardDto>> listArticleAreaBoard(@ApiParam(value = "게시글을 얻기위한 부가정보.", required = true) BoardParameterDto boardParameterDto) throws Exception {
		logger.info("listArticle - 호출");
		return new ResponseEntity<List<BoardDto>>(boardService.listArticleAreaBoard(boardParameterDto), HttpStatus.OK);
	}
	
	@ApiOperation(value = "지역게시판 글보기", notes = "지역 게시판에서 글번호에 해당하는 게시글의 정보를 반환한다.", response = BoardDto.class)
	@GetMapping("/area/{articleno}")
	public ResponseEntity<BoardDto> getArticleAreaBoard(@PathVariable("articleno") @ApiParam(value = "얻어올 글의 글번호.", required = true) int articleno) throws Exception {
		logger.info("getArticle - 호출 : " + articleno);
		boardService.updateHitAreaBoard(articleno);
		return new ResponseEntity<BoardDto>(boardService.getArticleAreaBoard(articleno), HttpStatus.OK);
	}
	
	@ApiOperation(value = "지역게시판 글수정", notes = "새로운 게시글 정보를 입력한다. 그리고 DB수정 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PutMapping("/area")
	public ResponseEntity<String> modifyArticleAreaBoard(@RequestBody @ApiParam(value = "수정할 글정보.", required = true) BoardDto boardDto) throws Exception {
		logger.info("modifyArticle - 호출");
		
		if (boardService.modifyArticleAreaBoard(boardDto)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.OK);
	}
	
	@ApiOperation(value = "지역게시판 글삭제", notes = "지역게시판에서 글번호에 해당하는 게시글의 정보를 삭제한다. 그리고 DB삭제 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@DeleteMapping("/area/{articleno}")
	public ResponseEntity<String> deleteArticleAreaBoard(@PathVariable("articleno") @ApiParam(value = "살제할 글의 글번호.", required = true) int articleno) throws Exception {
		logger.info("deleteArticle - 호출");
		if (boardService.deleteArticleAreaBoard(articleno)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	
/* 댓글 */
	
	@ApiOperation(value = "지역게시판 댓글리스트", notes = "글번호에 해당하는 댓글의 정보를 반환한다.", response = CommentDto.class)
	@GetMapping("/area/comment/{articleno}")
	public ResponseEntity<List<CommentDto>> getCommentAreaBoard(@PathVariable("articleno") @ApiParam(value = "글번호.", required = true) int articleno) throws Exception {
		logger.info("getCommentAreaBoard - 호출 : " + articleno);
		return new ResponseEntity<List<CommentDto>>(boardService.getCommentAreaBoard(articleno), HttpStatus.OK);
	}
	
	@ApiOperation(value = "지역게시판 댓글작성", notes = "글번호에 해당하는 게시글에 댓글을 작성한다.", response = CommentDto.class)
	@PostMapping("/area/comment")
	public ResponseEntity<String> writeCommentAreaBoard(@RequestBody @ApiParam(value = "게시글 정보.", required = true) CommentDto commentDto) throws Exception {
		logger.info("writeCommentFreeBoard - 호출 : " + commentDto.getArticleno());
		if(boardService.writeCommentAreaBoard(commentDto)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation(value = "지역게시판 댓글수정", notes = "글번호에 해당하는 게시글에 댓글을 수정한다.", response = CommentDto.class)
	@PutMapping("/area/comment/{articleno}")
	public ResponseEntity<String> modifyCommentAreaBoard(@PathVariable("articleno") @ApiParam(value = "글번호.", required = true) CommentDto commentDto) throws Exception {
		logger.info("modifyCommentAreaBoard - 호출 : " + commentDto.getArticleno());
		if(boardService.modifyCommentAreaBoard(commentDto)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation(value = "지역게시판 댓글 삭제", notes = "글번호에 해당하는 지역게시판의 댓글을 삭제한다. 그리고 DB삭제 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@DeleteMapping("/area/comment/{commentno}")
	public ResponseEntity<String> deleteCommentAreaBoard(@PathVariable("commentno") @ApiParam(value = "살제할 글의 글번호.", required = true) int commentno) throws Exception {
		logger.info("deleteCommentAreaBoard - 호출");
		if (boardService.deleteCommentAreaBoard(commentno)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	/*
	 *	ShareBoard 
	 */
	@ApiOperation(value = "공유게시판 글작성", notes = "공유게시판에 새로운 게시글 정보를 입력한다. 그리고 DB입력 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PostMapping("/share")
	public ResponseEntity<String> writeArticleShareBoard(@RequestBody @ApiParam(value = "게시글 정보.", required = true) BoardDto boardDto) throws Exception {
		logger.info("writeArticle - 호출");
		if (boardService.writeArticleShareBoard(boardDto)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation(value = "공유게시판 글목록", notes = "공유게시판의 모든 게시글의 정보를 반환한다.", response = List.class)
	@GetMapping("/share")
	public ResponseEntity<List<BoardDto>> listArticleShareBoard(@ApiParam(value = "게시글을 얻기위한 부가정보.", required = true) BoardParameterDto boardParameterDto) throws Exception {
		logger.info("listArticle - 호출");
		return new ResponseEntity<List<BoardDto>>(boardService.listArticleShareBoard(boardParameterDto), HttpStatus.OK);
	}
	
	@ApiOperation(value = "공유게시판 글보기", notes = "공유게시판에서 글번호에 해당하는 게시글의 정보를 반환한다.", response = BoardDto.class)
	@GetMapping("/share/{articleno}")
	public ResponseEntity<BoardDto> getArticleShareBoard(@PathVariable("articleno") @ApiParam(value = "얻어올 글의 글번호.", required = true) int articleno) throws Exception {
		logger.info("getArticle - 호출 : " + articleno);
		boardService.updateHitShareBoard(articleno);
		return new ResponseEntity<BoardDto>(boardService.getArticleShareBoard(articleno), HttpStatus.OK);
	}
	
	@ApiOperation(value = "공유게시판 글수정", notes = "새로운 게시글 정보를 입력한다. 그리고 DB수정 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PutMapping("/share")
	public ResponseEntity<String> modifyArticleShareBoard(@RequestBody @ApiParam(value = "수정할 글정보.", required = true) BoardDto boardDto) throws Exception {
		logger.info("modifyArticle - 호출");
		
		if (boardService.modifyArticleShareBoard(boardDto)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.OK);
	}
	
	@ApiOperation(value = "공유게시판 글삭제", notes = "글번호에 해당하는 게시글의 정보를 삭제한다. 그리고 DB삭제 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@DeleteMapping("/share/{articleno}")
	public ResponseEntity<String> deleteArticleShareBoard(@PathVariable("articleno") @ApiParam(value = "살제할 글의 글번호.", required = true) int articleno) throws Exception {
		logger.info("deleteArticle - 호출");
		if (boardService.deleteArticleShareBoard(articleno)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
/* 댓글 */
	
	@ApiOperation(value = "공유게시판 댓글리스트", notes = "글번호에 해당하는 댓글의 정보를 반환한다.", response = CommentDto.class)
	@GetMapping("/share/comment/{articleno}")
	public ResponseEntity<List<CommentDto>> getCommentShareBoard(@PathVariable("articleno") @ApiParam(value = "글번호.", required = true) int articleno) throws Exception {
		logger.info("getCommentShareBoard - 호출 : " + articleno);
		return new ResponseEntity<List<CommentDto>>(boardService.getCommentShareBoard(articleno), HttpStatus.OK);
	}
	
	@ApiOperation(value = "곻유게시판 댓글작성", notes = "글번호에 해당하는 게시글에 댓글을 작성한다.", response = CommentDto.class)
	@PostMapping("/share/comment")
	public ResponseEntity<String> writeCommentShareBoard(@RequestBody @ApiParam(value = "게시글 정보.", required = true) CommentDto commentDto) throws Exception {
		logger.info("writeCommentShareBoard - 호출 : " + commentDto.getArticleno());
		if(boardService.writeCommentShareBoard(commentDto)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation(value = "공유게시판 댓글수정", notes = "글번호에 해당하는 게시글에 댓글을 수정한다.", response = CommentDto.class)
	@PutMapping("/share/comment/{articleno}")
	public ResponseEntity<String> modifyCommentShareBoard(@PathVariable("articleno") @ApiParam(value = "글번호.", required = true) CommentDto commentDto) throws Exception {
		logger.info("modifyCommentShareBoard - 호출 : " + commentDto.getArticleno());
		if(boardService.modifyCommentShareBoard(commentDto)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation(value = "공유게시판 댓글 삭제", notes = "글번호에 해당하는 공유게시판의 댓글을 삭제한다. 그리고 DB삭제 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@DeleteMapping("/share/comment/{commentno}")
	public ResponseEntity<String> deleteCommentShareBoard(@PathVariable("commentno") @ApiParam(value = "살제할 글의 글번호.", required = true) int commentno) throws Exception {
		logger.info("deleteCommentShareBoard - 호출");
		if (boardService.deleteCommentShareBoard(commentno)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
}