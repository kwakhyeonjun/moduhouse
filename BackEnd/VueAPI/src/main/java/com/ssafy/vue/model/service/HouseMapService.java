package com.ssafy.vue.model.service;

import java.util.List;

import com.ssafy.vue.model.HouseInfoDto;
import com.ssafy.vue.model.SidoGugunCodeDto;

public interface HouseMapService {

	List<SidoGugunCodeDto> getSido() throws Exception;
	List<SidoGugunCodeDto> getGugunInSido(String sido) throws Exception;
	List<HouseInfoDto> getDongInGugun(String gugun) throws Exception;
	List<HouseInfoDto> getAptInDong(String dong) throws Exception;
	List<HouseInfoDto> getAptByAptName(String aptName) throws Exception;
	List<HouseInfoDto> getDeals(int aptCode) throws Exception;
	
	List<HouseInfoDto> selectInterestList() throws Exception;
	List<HouseInfoDto> findInterest(HouseInfoDto houseInfoDto) throws Exception;
	int insertInterest(HouseInfoDto houseInfoDto) throws Exception;
	int updateInterest(int count) throws Exception;
	
}
