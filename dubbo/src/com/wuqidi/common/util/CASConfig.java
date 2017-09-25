package com.wuqidi.common.util;


/**
 * 存放单点登录服务端配置信息
 *
 */
public class CASConfig {
	/** 单点登录服务端协议类型 */
	private String casHttpType;
	/** 单点登录服务名称 */
	private String casServerName;
	/** 单点登录服务端IP */
	private String casServerIp;
	/** 单点登录服务端端口 */
	private String casServerPort;
	/** 单点登录客户端名称 */
	private String casClientServerName;
	/** 单点登录客户端IP */
	private String casClientIp;
	/** 单点登录客户端端口 */
	private String casClientPort;
	/** 数据有效性（1=有效，0=无效） */
	private String isValid;
	public String getCasHttpType() {
		return casHttpType;
	}
	public void setCasHttpType(String casHttpType) {
		this.casHttpType = casHttpType;
	}
	public String getCasServerName() {
		return casServerName;
	}
	public void setCasServerName(String casServerName) {
		this.casServerName = casServerName;
	}
	public String getCasServerIp() {
		return casServerIp;
	}
	public void setCasServerIp(String casServerIp) {
		this.casServerIp = casServerIp;
	}
	public String getCasServerPort() {
		return casServerPort;
	}
	public void setCasServerPort(String casServerPort) {
		this.casServerPort = casServerPort;
	}
	public String getIsValid() {
		return isValid;
	}
	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}
	public String getCasClientServerName() {
		return casClientServerName;
	}
	public void setCasClientServerName(String casClientServerName) {
		this.casClientServerName = casClientServerName;
	}
	public String getCasClientIp() {
		return casClientIp;
	}
	public void setCasClientIp(String casClientIp) {
		this.casClientIp = casClientIp;
	}
	public String getCasClientPort() {
		return casClientPort;
	}
	public void setCasClientPort(String casClientPort) {
		this.casClientPort = casClientPort;
	}
	public String getCasServerUrlPrefix() {
		return this.casHttpType+"://"+this.casServerIp+":"+this.casServerPort+"/"+this.casServerName;
	}
	public String getCasService() {
		return this.casHttpType+"://"+this.casClientIp+":"+this.casClientPort+"/"+this.casClientServerName+"/cas";
	}
	public String getLoginUrl() {
		return this.casHttpType+"://"+this.casServerIp+":"+this.casServerPort+"/"+this.casServerName+"/login?service=" + 
				this.casHttpType+"://"+this.casClientIp+":"+this.casClientPort+"/"+this.casClientServerName+"/cas";
	}
	public String getRedirectUrl() {
		return this.casHttpType+"://"+this.casServerIp+":"+this.casServerPort+"/"+this.casServerName+"/logout?service=" + 
				this.casHttpType+"://"+this.casClientIp+":"+this.casClientPort+"/"+this.casClientServerName;
	}
}