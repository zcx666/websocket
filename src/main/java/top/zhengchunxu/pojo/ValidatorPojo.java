package top.zhengchunxu.pojo;

import java.util.Date;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Email;
import javax.validation.constraints.Future;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;


public class ValidatorPojo {

	//非空判断
	@NotNull(message="id不能为空")
	private Long id;
	
	@NotNull
	@Future(message="需要一个将来日期")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date date;
	
	@NotNull
	@DecimalMax(value="1000.00")
	@DecimalMin(value="0.1")
	private Double doubleValue=null;
	
	@NotNull
	@Min(value=1,message="最小值为1")
	@Max(value=88,message="最大值为88")
	private Integer integer;
	
	@NotNull
	@Range(min=1,max=888,message="范围为1至888")
	private Long range;
	
	//邮箱验证
	@Email(message="邮箱格式错误")
	private String email;
	
	@Size(min=20,max=30,message="字符串长度要求20到30之间")
	private String size;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Double getDoubleValue() {
		return doubleValue;
	}

	public void setDoubleValue(Double doubleValue) {
		this.doubleValue = doubleValue;
	}

	public Integer getInteger() {
		return integer;
	}

	public void setInteger(Integer integer) {
		this.integer = integer;
	}

	public Long getRange() {
		return range;
	}

	public void setRange(Long range) {
		this.range = range;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	@Override
	public String toString() {
		return "ValidatorPojo [id=" + id + ", date=" + date + ", doubleValue=" + doubleValue + ", integer=" + integer
				+ ", range=" + range + ", email=" + email + ", size=" + size + "]";
	}
	
	
	
}
