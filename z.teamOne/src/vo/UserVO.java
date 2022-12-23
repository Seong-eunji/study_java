package vo;

public class UserVO {
   private String userPhone;
   private String userId;
   private String userPassword;
   private String userName;
   
   public UserVO() {;}

   public String getUserPhone() {
      return userPhone;
   }
   
   public void setUserPhone(String userPhone) {
      this.userPhone = userPhone;
   }
   
   public String getUserId() {
      return userId;
   }

   public void setUserId(String userId) {
      this.userId = userId;
   }

   public String getUserPassword() {
      return userPassword;
   }

   public void setUserPassword(String userPassword) {
      this.userPassword = userPassword;
   }

   public String getUserName() {
      return userName;
   }

   public void setUserName(String userName) {
      this.userName = userName;
   }


   @Override
   public String toString() {
      String str = "<회원정보>\n" 
    		  	+ "[아이디]" + userId + "\n"
    		  	+ "[핸드폰번호]" + userPhone + "\n"
    		  	+ "[이름]" + userName;
      
      return str;
   }
}