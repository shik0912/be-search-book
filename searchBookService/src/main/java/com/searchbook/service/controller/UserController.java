package com.searchbook.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.searchbook.service.entity.UserEntity;
import com.searchbook.service.repository.UserRepository;
import com.searchbook.service.util.SecurityUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(description = "계정정보 API", tags="UserController")
@RestController
public class UserController {
	@Autowired
	private UserRepository userRepository;
	
	@ApiOperation("회원가입")
	@RequestMapping(value="/user/signup", method=RequestMethod.POST)
    private Boolean Post(@RequestBody UserEntity userEntity) {
		Boolean result = false;
		try {
			if (userRepository.findById(userEntity.getId()) == null) {
				SecurityUtil securityUtil = new SecurityUtil();
				String cypherText = securityUtil.encryptMD5(userEntity.getPassword());
				userRepository.save(new UserEntity(userEntity.getId(), cypherText));
				
				result = true;
			}
		} catch (Exception e) {
			result = false;
		}
		
		return result;
    }
	
	@ApiOperation("로그인")
	@RequestMapping(value="/user/login", method=RequestMethod.POST)
	private Boolean GetOne(@RequestBody UserEntity userEntity) {
		Boolean result = false;
		UserEntity getUserEntity = null;
		try {
			getUserEntity = userRepository.findById(userEntity.getId());
			SecurityUtil securityUtil = new SecurityUtil();
			String cypherText = securityUtil.encryptMD5(userEntity.getPassword());
			String getCypherText = getUserEntity.getPassword();
			
			result = cypherText.equals(getCypherText);
		} catch (Exception e) {
			result = false;
		}
		
		return result;
    }
}
