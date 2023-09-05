package dao;

import config.HibernateConfig;
import jakarta.persistence.EntityManagerFactory;
import model.Address;
import model.ZipCode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AddressDAOTest {
    AddressDAO dao = AddressDAO.getInstance();

    @Test
    void createAddress() {
        int id = dao.createAddress("Testvej", "1", dao.readZipCode(2625));
        Address address = dao.readAddress(id);
        ZipCode zip = new ZipCode(2625, "Vallensbæk", "Hovedstaden", "Vallensbæk");

        assertEquals("Testvej", address.getStreet());
        assertEquals("1", address.getNumber());
        assertEquals(zip.getZip(), address.getZip().getZip());
    }

    @Test
    void readAddress() {
        int id = dao.createAddress("Testvej", "2", dao.readZipCode(2625));
        Address address = dao.readAddress(id);
        assertEquals("Testvej", address.getStreet());
        assertEquals("2", address.getNumber());
    }

    @Test
    void updateAddress() {
        int id = dao.createAddress("Testvej", "3", dao.readZipCode(2625));
        Address address = dao.readAddress(id);

        assertEquals("Testvej", address.getStreet());
        assertEquals("3", address.getNumber());

        address.setStreet("Riftvej");
        address.setNumber("69");
        address.setZip(dao.readZipCode(4000));
        dao.updateAddress(address);
        address = dao.readAddress(id);
        assertEquals("Riftvej", address.getStreet());
        assertEquals("69", address.getNumber());
        assertEquals(4000, address.getZip().getZip());
    }

    @Test
    void deleteAddress() {
        int id = dao.createAddress("Testvej", "4", dao.readZipCode(2625));
        Address address = dao.readAddress(id);
        dao.deleteAddress(address);
        assertNull(dao.readAddress(id));
    }
}