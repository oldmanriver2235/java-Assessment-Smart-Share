package com.ftd.smartshare.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ResponseDto {
	
	@XmlElement
	private String success;
	@XmlElement
	private String error;
	@XmlElement
	private String name;
	@XmlElement
	private byte[] file;
	
	public ResponseDto() {
	}
		
		
		public ResponseDto(String success, String name, byte[] file) {
		super();
		this.success = success;
		this.name = name;
		this.file = file;
	}


		public ResponseDto(String success, String error) {
			this.success = success;
			this.error = error;
	}


		public String getSuccess() {
			return success;
		}


		public void setSuccess(String success) {
			this.success = success;
		}


		public String getError() {
			return error;
		}


		public void setError(String error) {
			this.error = error;
		}


		public String getName() {
			return name;
		}


		public void setName(String name) {
			this.name = name;
		}


		public byte[] getFile() {
			return file;
		}


		public void setFile(byte[] file) {
			this.file = file;
		}

}
