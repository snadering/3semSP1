package dao;

import dto.HobbyAndInterest;
import jakarta.persistence.EntityManager;
import model.Hobby;
import model.User;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HobbyDAOTest {
    HobbyDAO dao = HobbyDAO.getInstance();
    UserDAO userDAO = UserDAO.getInstance();
    @Test
    void readHobbyById() {
        Hobby hobby = dao.readHobbyById(3);
        assertEquals("Skuespil", hobby.getName());
    }

    @Test
    void allUsersWithGivenHobby() {
        User u1 = new User("Sander", "Hansen", "12345678", "123@123.dk");
        User u2 = new User("Tobias", "Hansen", "4567813", "qwert@bgfd.dk");
        User u3 = new User("Mads", "Hansen", "4567813", "poiu@sdfg.dk");
        u1.addHobby(dao.readHobbyById(3));
        u2.addHobby(dao.readHobbyById(3));
        u3.addHobby(dao.readHobbyById(3));
        userDAO.createUser(u1);
        userDAO.createUser(u2);
        userDAO.createUser(u3);
        List<User> users = dao.allUsersWithGivenHobby(3);
        assertTrue(users.size() >= 3);
    }

    @Test
    void numberOfUsersWithGivenHobby() {
        User u1 = new User("Jens", "Andersen", "12345678", "123@123.dk");
        User u2 = new User("Oliver", "Jensen", "4567813", "qwert@bgfd.dk");
        User u3 = new User("William", "Nielsen", "4567813", "poiu@sdfg.dk");
        u1.addHobby(dao.readHobbyById(4));
        u2.addHobby(dao.readHobbyById(4));
        u3.addHobby(dao.readHobbyById(4));
        userDAO.createUser(u1);
        userDAO.createUser(u2);
        userDAO.createUser(u3);
        int amount = dao.numberOfUsersWithGivenHobby(4);
        assertTrue(amount >= 3);
    }

    @Test
    void getAllHobbiesAndAmountOfInterested() {
        User u1 = new User("Jens", "Andersen", "12345678", "123@123.dk");
        User u2 = new User("Oliver", "Jensen", "4567813", "qwert@bgfd.dk");
        User u3 = new User("William", "Nielsen", "4567813", "poiu@sdfg.dk");
        u1.addHobby(dao.readHobbyById(7));
        u2.addHobby(dao.readHobbyById(5));
        u3.addHobby(dao.readHobbyById(5));
        userDAO.createUser(u1);
        userDAO.createUser(u2);
        userDAO.createUser(u3);
        List<HobbyAndInterest> list = dao.getAllHobbiesAndAmountOfInterested();
        assertNotNull(list);
        assertTrue(list.size() >= 2);
        for (HobbyAndInterest hai : list) {
            if (hai.getHobby().equals("Animation")) {
               assertTrue(hai.getInterest() >= 2);
            }
        }
    }
}