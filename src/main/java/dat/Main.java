package dat;


import dao.UserDAO;

public class Main {
    public static void main(String[] args) {
        System.out.println("test");
        UserDAO userDAO = new UserDAO();
        System.out.println(userDAO.getAllHobbiesAndAmountOfInterested());

    }

}