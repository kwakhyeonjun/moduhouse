package com.ssafy.vue.model.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.vue.model.HouseInfoDto;
import com.ssafy.vue.model.SidoGugunCodeDto;
import com.ssafy.vue.model.mapper.HouseMapMapper;

@Service
public class HouseMapServiceImpl implements HouseMapService {
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	public List<SidoGugunCodeDto> getSido() throws Exception {
		return sqlSession.getMapper(HouseMapMapper.class).getSido();
	}

	@Override
	public List<SidoGugunCodeDto> getGugunInSido(String sido) throws Exception {
		return sqlSession.getMapper(HouseMapMapper.class).getGugunInSido(sido);
	}

	@Override
	public List<HouseInfoDto> getDongInGugun(String gugun) throws Exception {
		return sqlSession.getMapper(HouseMapMapper.class).getDongInGugun(gugun);
	}

	@Override
	public List<HouseInfoDto> getAptInDong(String dong) throws Exception {
		return sqlSession.getMapper(HouseMapMapper.class).getAptInDong(dong);
	}

	@Override
	public List<HouseInfoDto> getAptByAptName(String aptName) throws Exception {
		return sqlSession.getMapper(HouseMapMapper.class).getAptByAptName("%" + aptName + "%");
	}

	@Override
	public List<HouseInfoDto> getDeals(int aptCode) throws Exception {
		HouseInfoDto houseinfoDto = sqlSession.getMapper(HouseMapMapper.class).getAptByAptCode(aptCode);
		System.out.println(houseinfoDto);
		List<HouseInfoDto> ishouses = sqlSession.getMapper(HouseMapMapper.class).findInterest(houseinfoDto);
		System.out.println(ishouses);
		if(sqlSession.getMapper(HouseMapMapper.class).findInterest(houseinfoDto).size() != 0) {
			System.out.println("있음");
			sqlSession.getMapper(HouseMapMapper.class).updateInterest(houseinfoDto.getAptCode());
		}else {
			System.out.println("없음");
			sqlSession.getMapper(HouseMapMapper.class).insertInterest(houseinfoDto);
		}
		return sqlSession.getMapper(HouseMapMapper.class).getDeals(aptCode);
	}

	@Override
	public List<HouseInfoDto> selectInterestList() throws Exception {
		return sqlSession.getMapper(HouseMapMapper.class).selectInterestList();
	}

	@Override
	public List<HouseInfoDto> findInterest(HouseInfoDto houseInfoDto) throws Exception {
		return sqlSession.getMapper(HouseMapMapper.class).findInterest(houseInfoDto);
	}

	@Override
	public int insertInterest(HouseInfoDto houseInfoDto) throws Exception {
		return sqlSession.getMapper(HouseMapMapper.class).insertInterest(houseInfoDto);
	}

	@Override
	public int updateInterest(int aptCode) throws Exception {
		return sqlSession.getMapper(HouseMapMapper.class).updateInterest(aptCode);
	}	

}
