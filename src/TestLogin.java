import dao.UserDAO; public class TestLogin { public static void main(String[] args) { UserDAO dao = new UserDAO(); System.out.println(dao.checkLogin("9876543210", "password123")); } }  
