/**
 * @Title: UserDataManager 
 * @Copyright 2010 -2013 CreativeWise 
 * @Package: com.framework.entity.user 
 * @Description: t_userdata的实体类 
 * @author: gaoguangchao 
 * @date: 2013-12-24 13:17:33 
 * @version V1.0  
 */ 
package com.framework.entity.user;



import java.util.Date;

import javax.persistence.Entity;

import com.orm.BaseEntity;


/**
 * @ClassName: UserDataManager 
 * @Description: t_userdata的实体类 
 * @author: gaoguangchao 
 * @date: 2013-12-24 13:17:33 
 */ 
 @Entity
public class UserDataManager extends BaseEntity{

	 private String UserId;

	    private String FullName;

	    private String ContactPhone;

	    private String OfficePhone;

	    private String FaxNumber;

	    private String EmailAddress;

	    private String ContactAddress;

	    private String ZipCode;

	    private String OrganId;

	    private String DetpId;

	    private byte[] UserLogo;

	    private String UserRemark;

	    private int Status;

		private String CreatePerson;

	    private Date CreateTime;

	    private String UpdatePerson;

	    private Date UpdateTime;

	    private String EnName;

	    private String PersonCode;

	    private String FirstName;

	    private String LastName;

	    private String CnameShortSpell;

	    private String CnameFullSpell;

	    private String CardNum;

	    private String CardCode;

	    private String Sex;

	    private Date Birthday;

	    private String Nation;

	    private String MarryCode;

	    private String PoliticCode;

	    private String DegreeCode;

	    private String EduCode;

	    private String PostLevel;

	    private String HomeTel;

	    private String HomeFax;

	    private String MSN;

	    private String QQ;

	    private String HomePage;

	    private String IsImpContact;

	    private String NativePiace;

	    private String Country;

	    private String ProvinceId;

	    private String CityId;

	    private int SeqNum;

	    private String RoomNum;

	    private int SecurityLevel;

	    private String OtherInfo;

	    private String Signature;

	    private String SignatureFileId;
	    
	    private String Account;
	    

		public String getAccount() {
			return Account;
		}

		public void setAccount(String Account) {
			this.Account = Account;
		}

		public String getUserId() {
			return UserId;
		}

		public void setUserId(String userId) {
			UserId = userId;
		}

		public String getFullName() {
			return FullName;
		}

		public void setFullName(String fullName) {
			FullName = fullName;
		}

		public String getContactPhone() {
			return ContactPhone;
		}

		public void setContactPhone(String contactPhone) {
			ContactPhone = contactPhone;
		}

		public String getOfficePhone() {
			return OfficePhone;
		}

		public void setOfficePhone(String officePhone) {
			OfficePhone = officePhone;
		}

		public String getFaxNumber() {
			return FaxNumber;
		}

		public void setFaxNumber(String faxNumber) {
			FaxNumber = faxNumber;
		}

		public String getEmailAddress() {
			return EmailAddress;
		}

		public void setEmailAddress(String emailAddress) {
			EmailAddress = emailAddress;
		}

		public String getContactAddress() {
			return ContactAddress;
		}

		public void setContactAddress(String contactAddress) {
			ContactAddress = contactAddress;
		}

		public String getZipCode() {
			return ZipCode;
		}

		public void setZipCode(String zipCode) {
			ZipCode = zipCode;
		}

		public String getOrganId() {
			return OrganId;
		}

		public void setOrganId(String organId) {
			OrganId = organId;
		}

		public String getDetpId() {
			return DetpId;
		}

		public void setDetpId(String detpId) {
			DetpId = detpId;
		}

		public byte[] getUserLogo() {
			return UserLogo;
		}

		public void setUserLogo(byte[] userLogo) {
			UserLogo = userLogo;
		}

		public String getUserRemark() {
			return UserRemark;
		}

		public void setUserRemark(String userRemark) {
			UserRemark = userRemark;
		}

		

		public String getCreatePerson() {
			return CreatePerson;
		}

		public void setCreatePerson(String createPerson) {
			CreatePerson = createPerson;
		}

		
		
		public Date getCreateTime() {
			return CreateTime;
		}

		public void setCreateTime(Date createTime) {
			CreateTime = createTime;
		}

		public void setStatus(int status) {
			Status = status;
		}

		public void setUpdateTime(Date updateTime) {
			UpdateTime = updateTime;
		}

		public String getUpdatePerson() {
			return UpdatePerson;
		}

		public void setUpdatePerson(String updatePerson) {
			UpdatePerson = updatePerson;
		}

		

		public String getEnName() {
			return EnName;
		}

		public void setEnName(String enName) {
			EnName = enName;
		}

		public String getPersonCode() {
			return PersonCode;
		}

		public void setPersonCode(String personCode) {
			PersonCode = personCode;
		}

		public String getFirstName() {
			return FirstName;
		}

		public void setFirstName(String firstName) {
			FirstName = firstName;
		}

		public String getLastName() {
			return LastName;
		}

		public void setLastName(String lastName) {
			LastName = lastName;
		}

		public String getCnameShortSpell() {
			return CnameShortSpell;
		}

		public void setCnameShortSpell(String cnameShortSpell) {
			CnameShortSpell = cnameShortSpell;
		}

		public String getCnameFullSpell() {
			return CnameFullSpell;
		}

		public void setCnameFullSpell(String cnameFullSpell) {
			CnameFullSpell = cnameFullSpell;
		}

		public String getCardNum() {
			return CardNum;
		}

		public void setCardNum(String cardNum) {
			CardNum = cardNum;
		}

		public String getCardCode() {
			return CardCode;
		}

		public void setCardCode(String cardCode) {
			CardCode = cardCode;
		}

		public String getSex() {
			return Sex;
		}

		public void setSex(String sex) {
			Sex = sex;
		}

		public Date getBirthday() {
			return Birthday;
		}

		public void setBirthday(Date birthday) {
			Birthday = birthday;
		}

		public String getNation() {
			return Nation;
		}

		public void setNation(String nation) {
			Nation = nation;
		}

		public String getMarryCode() {
			return MarryCode;
		}

		public void setMarryCode(String marryCode) {
			MarryCode = marryCode;
		}

		public String getPoliticCode() {
			return PoliticCode;
		}

		public void setPoliticCode(String politicCode) {
			PoliticCode = politicCode;
		}

		public String getDegreeCode() {
			return DegreeCode;
		}

		public void setDegreeCode(String degreeCode) {
			DegreeCode = degreeCode;
		}

		public String getEduCode() {
			return EduCode;
		}

		public void setEduCode(String eduCode) {
			EduCode = eduCode;
		}

		public String getPostLevel() {
			return PostLevel;
		}

		public void setPostLevel(String postLevel) {
			PostLevel = postLevel;
		}

		public String getHomeTel() {
			return HomeTel;
		}

		public void setHomeTel(String homeTel) {
			HomeTel = homeTel;
		}

		public String getHomeFax() {
			return HomeFax;
		}

		public void setHomeFax(String homeFax) {
			HomeFax = homeFax;
		}

		public String getMSN() {
			return MSN;
		}

		public void setMSN(String mSN) {
			MSN = mSN;
		}

		public String getQQ() {
			return QQ;
		}

		public void setQQ(String qQ) {
			QQ = qQ;
		}

		public String getHomePage() {
			return HomePage;
		}

		public void setHomePage(String homePage) {
			HomePage = homePage;
		}

		public String getIsImpContact() {
			return IsImpContact;
		}

		public void setIsImpContact(String isImpContact) {
			IsImpContact = isImpContact;
		}

		public String getNativePiace() {
			return NativePiace;
		}

		public void setNativePiace(String nativePiace) {
			NativePiace = nativePiace;
		}

		public String getCountry() {
			return Country;
		}

		public void setCountry(String country) {
			Country = country;
		}

		public String getProvinceId() {
			return ProvinceId;
		}

		public void setProvinceId(String provinceId) {
			ProvinceId = provinceId;
		}

		public String getCityId() {
			return CityId;
		}

		public void setCityId(String cityId) {
			CityId = cityId;
		}

		public int getSeqNum() {
			return SeqNum;
		}

		public void setSeqNum(int seqNum) {
			SeqNum = seqNum;
		}

		public String getRoomNum() {
			return RoomNum;
		}

		public void setRoomNum(String roomNum) {
			RoomNum = roomNum;
		}

		public int getSecurityLevel() {
			return SecurityLevel;
		}

		public void setSecurityLevel(int securityLevel) {
			SecurityLevel = securityLevel;
		}

		public String getOtherInfo() {
			return OtherInfo;
		}

		public void setOtherInfo(String otherInfo) {
			OtherInfo = otherInfo;
		}

		public String getSignature() {
			return Signature;
		}

		public void setSignature(String signature) {
			Signature = signature;
		}

		public String getSignatureFileId() {
			return SignatureFileId;
		}

		public void setSignatureFileId(String signatureFileId) {
			SignatureFileId = signatureFileId;
		}

		public int getStatus() {
			return Status;
		}

		public Date getUpdateTime() {
			return UpdateTime;
		}

		

}

