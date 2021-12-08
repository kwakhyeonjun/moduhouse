package com.ssafy.vue.model.service;

import java.util.List;

import com.ssafy.vue.model.MemberDto;

public interface MemberService {

	public MemberDto login(MemberDto memberDto) throws Exception;
	public MemberDto userInfo(String userid) throws Exception;
	public boolean signup(MemberDto memberDto)throws Exception;
	public boolean deleteUser(String userid) throws Exception;
	public boolean updatePassword(MemberDto memberDto) throws Exception;
	public List<MemberDto> getAllUser() throws Exception;
}
