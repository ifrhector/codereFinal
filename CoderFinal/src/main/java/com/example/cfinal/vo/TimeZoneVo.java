package com.example.cfinal.vo;

public class TimeZoneVo {

	private String status;
	private String message;
	private String countryCode;
	private String countryName;
	private String regionName;
	private String cityName;
	private String zoneName;
	private String abbreviation;
	private String gmtOffset;
	private String dst;
	private String zoneStart;
	private String zoneEnd;
	private String nextAbbreviation;
	private String timestamp;
	private String formatted;

	public TimeZoneVo() {
		super();
	}

	public TimeZoneVo(String status, String message, String countryCode, String countryName, String regionName,
			String cityName, String zoneName, String abbreviation, String gmtOffset, String dst, String zoneStart,
			String zoneEnd, String nextAbbreviation, String timestamp, String formatted) {
		super();
		this.status = status;
		this.message = message;
		this.countryCode = countryCode;
		this.countryName = countryName;
		this.regionName = regionName;
		this.cityName = cityName;
		this.zoneName = zoneName;
		this.abbreviation = abbreviation;
		this.gmtOffset = gmtOffset;
		this.dst = dst;
		this.zoneStart = zoneStart;
		this.zoneEnd = zoneEnd;
		this.nextAbbreviation = nextAbbreviation;
		this.timestamp = timestamp;
		this.formatted = formatted;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public String getRegionName() {
		return regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getZoneName() {
		return zoneName;
	}

	public void setZoneName(String zoneName) {
		this.zoneName = zoneName;
	}

	public String getAbbreviation() {
		return abbreviation;
	}

	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
	}

	public String getGmtOffset() {
		return gmtOffset;
	}

	public void setGmtOffset(String gmtOffset) {
		this.gmtOffset = gmtOffset;
	}

	public String getDst() {
		return dst;
	}

	public void setDst(String dst) {
		this.dst = dst;
	}

	public String getZoneStart() {
		return zoneStart;
	}

	public void setZoneStart(String zoneStart) {
		this.zoneStart = zoneStart;
	}

	public String getZoneEnd() {
		return zoneEnd;
	}

	public void setZoneEnd(String zoneEnd) {
		this.zoneEnd = zoneEnd;
	}

	public String getNextAbbreviation() {
		return nextAbbreviation;
	}

	public void setNextAbbreviation(String nextAbbreviation) {
		this.nextAbbreviation = nextAbbreviation;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getFormatted() {
		return formatted;
	}

	public void setFormatted(String formatted) {
		this.formatted = formatted;
	}

}
