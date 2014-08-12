public class Files {
	String md5, filePath;

	public String getMd5() {
		return md5;
	}

	public void setMd5(String md5) {
		this.md5 = md5;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Files) {
			String checkMD5 = ((Files) obj).getMd5();
			return this.md5.equals(checkMD5);
		}
		return false;
	}

}
