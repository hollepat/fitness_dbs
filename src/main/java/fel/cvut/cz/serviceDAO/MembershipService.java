package fel.cvut.cz.serviceDAO;

import fel.cvut.cz.dao.EmployeeDAO;
import fel.cvut.cz.dao.DAO;
import fel.cvut.cz.entity.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;

public class MembershipService {
    @PersistenceContext
    private EntityManager em;

    public MembershipService(EntityManager em) {
        this.em = em;
    }

    @Transactional
    public boolean addMember(String nationalId, String phone, String firstName, String lastName, String city,
                             String street, String postalCode, String type, Integer price) {
        try {

            // DAO API
            DAO dao = new DAO(em);
            EmployeeDAO employeeDAO = new EmployeeDAO(em);

            // choose first Employee
            EmployeeEntity e = employeeDAO.getAll().get(0);

            // create Invoice
            InvoiceEntity i = new InvoiceEntity();
            i.setEmployee(e);
            i.setCost(new BigDecimal(price));
            i.setDateofissue(Date.valueOf(LocalDate.now()));
            i.setInvoicenumber(5);

            // put invoice to persistence context
            dao.create(i);

            // create Address
            Address a = new Address();
            a.setCity(city);
            a.setPostalCode(postalCode);
            a.setStreet(street);

            // create customer
            CustomerEntity cu = new CustomerEntity();
            cu.setNationalid(nationalId);
            cu.setPhone(phone);
            cu.setFirstname(firstName);
            cu.setLastname(lastName);
            cu.setAddress(a);

            dao.create(cu);

            // create Membership
            MembershipEntity m = new MembershipEntity();
            m.setCustomer(cu);
            m.setInvoice(i);
            m.setCost(new BigDecimal(price));
            m.setType(type);
            m.setExpiredate(Date.valueOf("2024-09-09"));

            dao.create(m);

            return true;
        } catch (Exception e) {
            // Handle any exceptions
            System.err.println("Transaction function failed!");
            return false;
        }
    }
}
