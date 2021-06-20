package jammy.datasource.pojo;

import javax.persistence.Table;

import lombok.Data;
import lombok.ToString;

@Data
@Table(name = "user_info")
@ToString
public class UserInfo {
	
	private int id;
	
	private String userName;
	
	private String loginId;
	
	private String pwd;
	
	private String phoneNo;
	
	private String idCardNo;
	
	private String identity;
	
	private boolean isActive;

}
