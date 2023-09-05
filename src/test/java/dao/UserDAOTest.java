package dao;

import model.Address;
import model.User;
import model.ZipCode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserDAOTest {

    UserDAO dao = UserDAO.getInstance();
    AddressDAO addressDAO = AddressDAO.getInstance();

    @Test
    void createUser() {
        int id = dao.createUser("Sander", "van der Veen", "0612345678", "test@mail123.com");
        User user = dao.readUserById(id);
        assertEquals("Sander", user.getName());
        assertEquals("van der Veen", user.getSurname());
        assertEquals("0612345678", user.getPhoneNumber());
        assertEquals("test@mail123.com", user.getEmail());
    }

    @Test
    void findUserById() {
        int id = dao.createUser("Tobias", "Nielsen", "98765432", "tobias@nielsen.dk");
        User user = dao.readUserById(id);
        assertEquals("Tobias", user.getName());
        assertEquals("Nielsen", user.getSurname());
        assertEquals("98765432", user.getPhoneNumber());
        assertEquals("tobias@nielsen.dk", user.getEmail());
    }

    @Test
    void updateUser() {
        User user = new User("Bastian", "Nielsen", "98765432", "tobias@nielsen.dk");
        ZipCode zip = addressDAO.readZipCode(2620);
        Address address = new Address("Albertslundvej", "81", zip);
        addressDAO.createAddress(address);
        int id = dao.createUser(user);
        user.setAddress(address);
        dao.updateUser(user);
        assertEquals(address.getStreet(), user.getAddress().getStreet());
        assertEquals(address.getNumber(), user.getAddress().getNumber());
        assertEquals(address.getZip().getZip(), user.getAddress().getZip().getZip());
    }

    @Test
    void deleteUser() {
       int id = dao.createUser("Christian", "Stennicke", "12344321", "chr@sten.dk");
       dao.deleteUser(id);
       assertNull(dao.readUserById(id));
    }

    @Test
    void getAllPhoneNumbersFromUserById() {
        //A user only has one phone number, so this method is not needed.
    }

    @Test
    void getAllInformationOnUserByPhoneNumber() {
        int id = dao.createUser("Christian", "Stennicke", "87654321", "qwerty@qwerty.com");
        User user = dao.getAllInformationOnUserByPhoneNumber("87654321");
        assertEquals("Christian", user.getName());
        assertEquals("Stennicke", user.getSurname());
        assertEquals("87654321", user.getPhoneNumber());
        assertEquals("qwerty@qwerty.com", user.getEmail());
    }
}