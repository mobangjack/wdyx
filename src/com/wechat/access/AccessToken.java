﻿/**
 * Copyright (c) 2011-2015, Mobangjack 莫帮杰 (mobangjack@foxmail.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.wechat.access;

import java.util.Date;

/**
 * 全局属性：AccessToken的封装
 * @author 帮杰
 *
 */
public class AccessToken {

	private String token;
	private Date getIn;
	private Integer expiresIn;

	public AccessToken(){}
	
	public AccessToken(String token,Integer expiresIn) {
		this.token = token;
		this.getIn = new Date();
		this.expiresIn = expiresIn;
	}
	
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Date getGetIn() {
		return getIn;
	}

	public void setGetIn(Date getIn) {
		this.getIn = getIn;
	}
	
	public int getExpiresIn() {
		return expiresIn;
	}

	public void setExpiresIn(int expiresIn) {
		this.expiresIn = expiresIn;
	}

	@Override
	public String toString() {
		return token;
	}

	public boolean isExpired() {
		Date now = new Date();
		Date lastGetIn = this.getGetIn();
		int expiredIn = (this.getExpiresIn()-200)*1000;
		return (now.getTime()>lastGetIn.getTime()+expiredIn);
	}
	
}
