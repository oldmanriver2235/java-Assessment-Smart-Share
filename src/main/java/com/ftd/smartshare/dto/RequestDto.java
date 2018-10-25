package com.ftd.smartshare.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class RequestDto {
	
	@XmlElement
	private String type;

	@XmlElement
	private String filename;

	@XmlElement
	private byte[] file;
	
	@XmlElement
	private String password;

	public RequestDto() {
		super();
	}

	public RequestDto(String type, String filename, byte[] file) {
		super();
		this.type = type;
		this.filename = filename;
		this.file = file;
	}
	
	public RequestDto(String type, String filename, String password) {
		this.type = type;
		this.filename = filename;
		this.password = password;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public byte[] getFile() {
		return file;
	}

	public void setFile(byte[] file) {
		this.file = file;
	}

}
