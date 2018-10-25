package com.ftd.smartshare.server.entity;

public class File {

	private int id;
	private String filename;
	private byte[] file;
	//private String password;

	public File() {
	}

	public File(int id, String filename, byte[] file) {
		this.id = id;
		this.filename = filename;
		this.file = file;
	}
	
	public File(String filename, byte[] file) {
		this.filename = filename;
		this.file = file;
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

