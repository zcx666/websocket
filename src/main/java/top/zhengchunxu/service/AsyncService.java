package top.zhengchunxu.service;

public interface AsyncService {

	public void generateReport();
	
	public void setString(String key,Object value,Long timeOut); 
	
	public Object getString(String key);
	
	public void deleKey(String key);
}
