package com.internousdev.sunflower.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.sunflower.dao.UserInfoDAO;
import com.internousdev.sunflower.util.InputChecker;
import com.opensymphony.xwork2.ActionSupport;

public class CreateUserConfirmAction extends ActionSupport implements SessionAware{
	private String familyName;
	private String firstName;
	private String familyNameKana;
	private String firstNameKana;
	private String sex;
	private String email;
	private String userId;
	private String password;
	private List<String> sexList = new ArrayList<String>();
	private Map<String, Object> session;

	public String execute(){

		List<String> familyNameErrorMessageList = new ArrayList<String>();
		List<String> firstNameErrorMessageList = new ArrayList<String>();
		List<String> familyNameKanaErrorMessageList = new ArrayList<String>();
		List<String> firstNameKanaErrorMessageList = new ArrayList<String>();
		List<String> emailErrorMessageList = new ArrayList<String>();
		List<String> userIdErrorMessageList = new ArrayList<String>();
		List<String> passwordErrorMessageList = new ArrayList<String>();

		//セッションタイムアウト
		if(session == null || session.isEmpty()){
			return "timeOut";
		}
		String result =ERROR;

		InputChecker inputChecker =new InputChecker();
		UserInfoDAO userInfoDAO = new UserInfoDAO();

		session.put("familyName",familyName);
		session.put("firstName",firstName);
		session.put("familyNameKana",familyNameKana);
		session.put("firstNameKana",firstNameKana);
		session.put("sex",sex);
		session.put("email",email);
		session.put("userId",userId);
		session.put("password",password);

		familyNameErrorMessageList = inputChecker.doCheck("姓",familyName,1, 16, true, true, true, false, false, false, false, false, false);
		firstNameErrorMessageList = inputChecker.doCheck("名", firstName, 1, 16, true, true, true, false, false, false, false, false, false);
		familyNameKanaErrorMessageList = inputChecker.doCheck("姓ふりがな",familyNameKana,1, 16, false, false, true, false, false, false, false, false, false);
		firstNameKanaErrorMessageList = inputChecker.doCheck("名ふりがな",firstNameKana,1, 16, false, false, true, false, false, false, false, false, false);
		emailErrorMessageList = inputChecker.doCheck("メールアドレス", email, 10, 32, true, false, false, true, true, false, false, false, false);
		userIdErrorMessageList = inputChecker.doCheck("ユーザID", userId, 1, 8, true, false, false, true, false, false, false, false, false);
		if(!userInfoDAO.isUniqueId(userId)){
			userIdErrorMessageList.add("使用できないユーザIDです。");
		}
		passwordErrorMessageList = inputChecker.doCheck("パスワード", password, 1, 16, true, false, false, true, false, false, false, false, false);

		if(familyNameErrorMessageList.size()==0
				&& firstNameErrorMessageList.size()==0
				&& familyNameKanaErrorMessageList.size()==0
				&& firstNameKanaErrorMessageList.size()==0
				&& emailErrorMessageList.size()==0
				&& userIdErrorMessageList.size()==0
				&& passwordErrorMessageList.size()==0) {

			session.put("savedUserInfo",true);

			result = SUCCESS;
		}else{
			session.put("familyNameErrorMessageList", familyNameErrorMessageList);
			session.put("firstNameErrorMessageList", firstNameErrorMessageList);
			session.put("familyNameKanaErrorMessageList", familyNameKanaErrorMessageList);
			session.put("firstNameKanaErrorMessageList", firstNameKanaErrorMessageList);
			session.put("emailErrorMessageList", emailErrorMessageList);
			session.put("userIdErrorMessageList", userIdErrorMessageList);
			session.put("passwordErrorMessageList", passwordErrorMessageList);
			result = ERROR;
		}

		if("0".equals(sex)){
			sex = "男性";
		}else{
			sex = "女性";
		}
		return result;
	}

	public String getFamilyName() {
		return familyName;
	}

	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getFamilyNameKana() {
		return familyNameKana;
	}

	public void setFamilyNameKana(String familyNameKana) {
		this.familyNameKana = familyNameKana;
	}

	public String getFirstNameKana() {
		return firstNameKana;
	}

	public void setFirstNameKana(String firstNameKana) {
		this.firstNameKana = firstNameKana;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	public List<String> getSexList() {
		return sexList;
	}

	public void setSexList(List<String> sexList) {
		this.sexList = sexList;
	}

	public Map<String, Object> getSession() {
		return session;
	}
	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

}



