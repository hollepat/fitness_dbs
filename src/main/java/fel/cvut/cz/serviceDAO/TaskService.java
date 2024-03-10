package fel.cvut.cz.serviceDAO;

import fel.cvut.cz.dao.*;
import fel.cvut.cz.entity.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;

public class TaskService {

    @Autowired
    private MembershipService membershipService;        // dependency injection

    @PersistenceContext
    EntityManager em;

    public TaskService(EntityManager em) {
        this.em = em;
    }

    @Transactional
    public void deleteAllTables() {
        em.createNativeQuery("SELECT deleteAll();").executeUpdate();
    }

    public void myTransaction() throws SQLException {
        // transaction CP4
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        membershipService = new MembershipService(em);

        try {

            DAO dao = new DAO(em);

            // create Address
            Address a = new Address();
            a.setCity("Liberec");
            a.setPostalCode("234 63");
            a.setStreet("Pale");

            // create Employee
            EmployeeEntity e = new EmployeeEntity();
            e.setNationalid("323455/4341");
            e.setPhone("113 234 004");
            e.setFirstname("Marek");
            e.setLastname("Cihla");
            e.setAddress(a);
            e.setContractnumber(23);
            e.setSalary(new BigDecimal(10));
            e.setWorkposition("Janitor");

            dao.create(e);

            membershipService.addMember("100918/9375", "003 509 328", "Anežka", "Soukupová", "Liberec",
                    "Sadová", "458 98", "Basic", 10);
            // successfully commit
            transaction.commit();
        } catch (Exception e) {
            // failed rollback
            System.err.println("Transaction failed!");
            e.printStackTrace();
            transaction.rollback();
        }

    }

    // 5 examples of data usage
    public void createPersons() {

        // DAO
        PersonDAO personDAO = new PersonDAO(em);
        EmployeeDAO employeeDAO = new EmployeeDAO(em);
        CustomerDAO customerDAO = new CustomerDAO(em);
        TrainerDAO trainerDAO = new TrainerDAO(em);

        // create Address
        Address a = new Address();
        a.setCity("Liberec");
        a.setPostalCode("234 63");
        a.setStreet("Pale");

        // create Person
        PersonEntity p = new PersonEntity();
        p.setNationalid("323001/4003");
        p.setPhone("113 234 003");
        p.setFirstname("Jenda");
        p.setLastname("Cihla");
        p.setAddress(a);


        EmployeeEntity e = new EmployeeEntity();
        e.setNationalid("323455/4341");
        e.setPhone("113 234 004");
        e.setFirstname("Marek");
        e.setLastname("Cihla");
        e.setAddress(a);
        e.setContractnumber(23);
        e.setSalary(new BigDecimal(10));
        e.setWorkposition("Janitor");

        CustomerEntity cu = new CustomerEntity();
        cu.setNationalid("323400/4341");
        cu.setPhone("113 234 014");
        cu.setFirstname("Tonda");
        cu.setLastname("Cihla");
        cu.setAddress(a);

        TrainerEntity t = new TrainerEntity();
        t.setNationalid("323405/4341");
        t.setPhone("113 234 015");
        t.setFirstname("Milan");
        t.setLastname("Pekarek");
        t.setAddress(a);

        // persist and merge
        personDAO.create(p);
        employeeDAO.create(e);
        customerDAO.create(cu);
        trainerDAO.create(t);

        System.out.println(personDAO.read(p.getNationalid()).toString());

    }

    public void createTrainersLicense() {

        DAO dao = new DAO(em);

        // create Address
        Address a = new Address();
        a.setCity("Liberec");
        a.setPostalCode("234 63");
        a.setStreet("Pale");

        TrainerEntity t = new TrainerEntity();
        t.setNationalid("323405/4341");
        t.setPhone("113 234 015");
        t.setFirstname("Milan");
        t.setLastname("Pekarek");
        t.setAddress(a);

        dao.create(t);

        TrainerslicenseEntity tl = new TrainerslicenseEntity();
        tl.setTrainer(t);
        tl.setLicense("Yoga");

        dao.create(tl);

    }

    public void createInvoice() {
        DAO dao = new DAO(em);

        // create Address
        Address a = new Address();
        a.setCity("Liberec");
        a.setPostalCode("234 63");
        a.setStreet("Pale");

        // create employee
        EmployeeEntity e = new EmployeeEntity();
        e.setNationalid("323455/4341");
        e.setPhone("113 234 004");
        e.setFirstname("Marek");
        e.setLastname("Cihla");
        e.setAddress(a);
        e.setContractnumber(23);
        e.setSalary(new BigDecimal(10));
        e.setWorkposition("Janitor");

        // put employee to persistence context
        dao.create(e);

        // create invoice
        InvoiceEntity i = new InvoiceEntity();
        i.setEmployee(e);
        i.setCost(new BigDecimal(50));
        i.setDateofissue(Date.valueOf(LocalDate.now()));
        i.setInvoicenumber(5);

        // put invoice to persistence context
        dao.create(i);

    }

    public void createMembership() {

        DAO dao = new DAO(em);

        // create Address
        Address a = new Address();
        a.setCity("Liberec");
        a.setPostalCode("234 63");
        a.setStreet("Pale");

        // create customer
        CustomerEntity cu = new CustomerEntity();
        cu.setNationalid("323400/4341");
        cu.setPhone("113 234 014");
        cu.setFirstname("Tonda");
        cu.setLastname("Cihla");
        cu.setAddress(a);

        dao.create(cu);

        EmployeeEntity e = new EmployeeEntity();
        e.setNationalid("323455/4341");
        e.setPhone("113 234 004");
        e.setFirstname("Marek");
        e.setLastname("Cihla");
        e.setAddress(a);
        e.setContractnumber(23);
        e.setSalary(new BigDecimal(10));
        e.setWorkposition("Janitor");

        dao.create(e);

        InvoiceEntity i = new InvoiceEntity();
        i.setEmployee(e);
        i.setCost(new BigDecimal(50));
        i.setDateofissue(Date.valueOf(LocalDate.now()));
        i.setInvoicenumber(5);

        dao.create(i);

        // create membership
        MembershipEntity m = new MembershipEntity();
        m.setCustomer(cu);
        m.setInvoice(i);
        m.setCost(new BigDecimal(60));
        m.setType("Basic");
        m.setExpiredate(Date.valueOf("2024-09-09"));

        dao.create(m);

    }

    public void createSuperviseRelation() {

        DAO dao = new DAO(em);

        EmployeeEntity e1 = new EmployeeEntity();
        e1.setNationalid("323466/4341");
        e1.setPhone("113 234 006");
        e1.setFirstname("Marek");
        e1.setLastname("Prkno");
        // create Address
        Address a1 = new Address();
        a1.setCity("Liberec");
        a1.setPostalCode("234 63");
        a1.setStreet("Pale");
        e1.setAddress(a1);
        e1.setContractnumber(23);
        e1.setSalary(new BigDecimal(10));
        e1.setWorkposition("Janitor");

        dao.create(e1);

        EmployeeEntity e2 = new EmployeeEntity();
        e2.setNationalid("323222/4340");
        e2.setPhone("113 234 007");
        e2.setFirstname("Marek");
        e2.setLastname("Cihla");
        Address a2 = new Address();
        a2.setCity("Praha");
        a2.setPostalCode("014 63");
        a2.setStreet("Les");
        e2.setAddress(a2);
        e2.setContractnumber(1);
        e2.setSalary(new BigDecimal(1000));
        e2.setWorkposition("CEO");

        dao.create(e2);

        EmployeeEntity e3 = new EmployeeEntity();
        e3.setNationalid("323455/4330");
        e3.setPhone("113 234 029");
        e3.setFirstname("Janda");
        e3.setLastname("Landa");
        Address a3 = new Address();
        a3.setCity("Ostrava");
        a3.setPostalCode("236 63");
        a3.setStreet("Štefkova");
        e3.setAddress(a3);
        e3.setContractnumber(250);
        e3.setSalary(new BigDecimal(100));
        e3.setWorkposition("Receptionist");

        dao.create(e3);

        SuperviseEntity s1 = new SuperviseEntity();
        s1.setSupervisor(e2);
        s1.setSupervised(e1);

        dao.create(s1);

        SuperviseEntity s2 = new SuperviseEntity();
        s2.setSupervisor(e2);
        s2.setSupervised(e3);

        dao.create(s2);

        SuperviseEntity s3 = new SuperviseEntity();
        s3.setSupervised(e2);

        dao.create(s3);

    }

    public void createClassEvent() {

        DAO dao = new DAO(em);

        // create Customer
        CustomerEntity cu = new CustomerEntity();
        cu.setNationalid("323400/4341");
        cu.setPhone("113 234 014");
        cu.setFirstname("Tonda");
        cu.setLastname("Cihla");
        Address a1 = new Address();
        a1.setCity("Liberec");
        a1.setPostalCode("234 63");
        a1.setStreet("Pale");
        cu.setAddress(a1);

        dao.create(cu);

        // crate Trainer
        TrainerEntity t = new TrainerEntity();
        t.setNationalid("323405/4341");
        t.setPhone("113 234 015");
        t.setFirstname("Milan");
        t.setLastname("Pekarek");
        Address a3 = new Address();
        a3.setCity("Ostrava");
        a3.setPostalCode("236 63");
        a3.setStreet("Štefkova");
        t.setAddress(a3);

        dao.create(t);

        // create WorkoutClass
        WorkoutclassEntity w = new WorkoutclassEntity();
        w.setId(1);
        w.setName("Power Yoga");
        w.setDay(Date.valueOf("2024-05-08"));
        w.setTime(Time.valueOf("17:00:00"));
        w.setTeacher(t);
        w.setCapacity(15);

        dao.create(w);

        // create TakeClass

        TakeclassEntity tc = new TakeclassEntity();
        tc.setId(w);
        tc.setCustomer(cu);
        tc.setPrice(new BigDecimal(25));

        dao.create(tc);
    }

    public void readWorkoutClass() {

        DAO dao = new DAO(em);
        WorkoutClassDAO workoutClassDAO = new WorkoutClassDAO(em);

        // crate Trainer
        TrainerEntity t = new TrainerEntity();
        t.setNationalid("323405/4341");
        t.setPhone("113 234 015");
        t.setFirstname("Milan");
        t.setLastname("Pekarek");
        Address a3 = new Address();
        a3.setCity("Ostrava");
        a3.setPostalCode("236 63");
        a3.setStreet("Štefkova");
        t.setAddress(a3);

        dao.create(t);

        // create WorkoutClass
        WorkoutclassEntity w1 = new WorkoutclassEntity();
        w1.setId(1);
        w1.setName("Power Yoga");
        w1.setDay(Date.valueOf("2024-05-08"));
        w1.setTime(Time.valueOf("17:00:00"));
        w1.setTeacher(t);
        w1.setCapacity(15);

        workoutClassDAO.create(w1);

        // create WorkoutClass
        WorkoutclassEntity w2 = new WorkoutclassEntity();
        w2.setId(2);
        w2.setName("Spinning");
        w2.setDay(Date.valueOf("2023-05-08"));
        w2.setTime(Time.valueOf("15:00:00"));
        w2.setTeacher(t);
        w2.setCapacity(20);

        workoutClassDAO.create(w2);

        // create WorkoutClass
        WorkoutclassEntity w3 = new WorkoutclassEntity();
        w3.setId(3);
        w3.setName("Power Yoga");
        w3.setDay(Date.valueOf("2024-06-08"));
        w3.setTime(Time.valueOf("12:00:00"));
        w3.setTeacher(t);
        w3.setCapacity(15);

        workoutClassDAO.create(w3);


        for (WorkoutclassEntity w : workoutClassDAO.getAll()) {
            System.out.println(w.toString());
        }

    }
}
