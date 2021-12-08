package com.ssafy.vue.model.service;

import java.util.List;

import com.ssafy.util.PageNavigation;
import com.ssafy.vue.model.QnADto;
import com.ssafy.vue.model.QnAParameterDto;

public interface QnAService {
	public boolean writeArticle(QnADto qnaDto) throws Exception;
	public List<QnADto> listArticle(QnAParameterDto qnaParameterDto) throws Exception;
	public PageNavigation makePageNavigation(QnAParameterDto qnaParameterDto) throws Exception;
	
	public QnADto getArticle(int articleno) throws Exception;
	public void updateHit(int articleno) throws Exception;
	public boolean modifyArticle(QnADto qnaDto) throws Exception;
	public boolean deleteArticle(int articleno) throws Exception;
	
	public boolean writeAnswer(int articleno) throws Exception;
}
