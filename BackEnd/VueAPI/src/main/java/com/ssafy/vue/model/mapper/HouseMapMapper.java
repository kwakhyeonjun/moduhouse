package com.ssafy.vue.model.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.vue.model.HouseInfoDto;
import com.ssafy.vue.model.SidoGugunCodeDto;

@Mapper
public interface HouseMapMapper {

	List<SidoGugunCodeDto> getSido() throws SQLException;
	List<SidoGugunCodeDto> getGugunInSido(String sido) throws SQLException;
	List<HouseInfoDto> getDongInGugun(String gugun) throws SQLException;
	List<HouseInfoDto> getAptInDong(String dong) throws SQLException;
	List<HouseInfoDto> getAptByAptName(String aptName) throws SQLException;
	List<HouseInfoDto> getDeals(int aptCode) throws SQLException;
	
	List<HouseInfoDto> selectInterestList() throws SQLException;
	List<HouseInfoDto> findInterest(HouseInfoDto houseInfoDto) throws SQLException;
	int insertInterest(HouseInfoDto houseInfoDto) throws SQLException;
	int updateInterest(int aptCode) throws SQLException;
	HouseInfoDto getAptByAptCode(int aptCode) throws SQLException;
}
