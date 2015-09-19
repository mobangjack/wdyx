/**
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
package com.wechat.api;

import java.util.ArrayList;
import java.util.List;

import com.json.JsonObject;
import com.wechat.util.http.ApiRequest;


/**
 * WechatServerIpApi</br>
 * 用于获取微信服务器IP地址
 * @author 帮杰
 *
 */
public class WechatServerIpApi {

	public static final String URL = "https://api.weixin.qq.com/cgi-bin/getcallbackip?access_token=ACCESS_TOKEN";
	
	/**
	 * 
	 * @param accessToken
	 * @return {
					"ip_list":["127.0.0.1","127.0.0.1"]
	 *			}
	 * @throws Exception 
	 */
	public static List<String> getWechatServerIpList(String accessToken) {
		String url = URL.replace("ACCESS_TOKEN", accessToken);
		JsonObject jsonObject = new ApiRequest(url).doGet().getResourceAsJsonObject();
		List<String> ipList = new ArrayList<String>();
		jsonObject.getJsonArray("ip_list").toList(ipList);
		return ipList;
	}
}
